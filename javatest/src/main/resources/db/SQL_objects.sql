# БД та же, что и для AptechLibrary.
# URL: jdbc:mysql://192.168.56.200:3306
# Login: root
# Password: 4
# Схемы:
#    sql_ex_aero
#    sql_ex_computer
#    sql_ex_inc_out
#    sql_ex_painting
#    sql_ex_ships


# ------------------------------------------------Procedures + Cursors--------------------------------------------------
DELIMITER //
CREATE PROCEDURE `p2`()
LANGUAGE SQL
DETERMINISTIC
  SQL SECURITY DEFINER
  COMMENT 'A procedure'
  BEGIN
    SELECT 'Hello World !';
  END
//
CALL p2();

DELIMITER //
CREATE PROCEDURE TestProc(OUT t INTEGER(11))
NOT DETERMINISTIC
  SQL SECURITY INVOKER
  COMMENT ''
  BEGIN
    SELECT price INTO 't' FROM Printer LIMIT 0, 1;
  END
//
CALL TestProc(@a);
SELECT @a;

USE aplib;
DELIMITER //
-- n число записей на страницу
-- p номер страницы, по умолчанию - первая
CREATE PROCEDURE paging(IN n INTEGER, IN p INTEGER)
  BEGIN
    DECLARE first INT DEFAULT 0;
    DECLARE total INT;

    SELECT COUNT(*) INTO total FROM book;

    IF p = 0 OR p IS NULL OR total/n < p
    THEN
      SET p = 1;
    END IF;

    IF n > total
    THEN
      SET n = total;
      SET p = 1;
    END IF;

    SET first = (n*p)-n;
    SELECT * FROM book LIMIT first, n;
  END
//
CALL paging(2, 1);

USE sql_ex_computer;
DELIMITER //
CREATE PROCEDURE `proc_CURSOR`(OUT param1 INT, OUT max_hd DOUBLE)
  BEGIN
    DECLARE col1 DECIMAL(12,2);
    DECLARE col2 DOUBLE;
    DECLARE sum DECIMAL(12,2);
    DECLARE stop TINYINT;

    DECLARE cur1 CURSOR FOR SELECT price, hd FROM PC;/*Объявление курсора и его заполнение */
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET stop = 1;/*Что делать, когда больше нет записей*/

    OPEN cur1;/*Открыть курсор*/

    SET stop = 0;
    SET sum = 0;

    WHILE stop = 0 DO
      FETCH cur1 INTO col1, col2;/*Назначить значение переменных, равными текущим значениям столбцов*/

      IF max_hd IS NULL
      THEN SET max_hd = col2;
      END IF;

      IF col2 > max_hd
      THEN SET max_hd = col2;
      END IF;

      SET sum = sum + col1;
    END WHILE;

    CLOSE cur1; /*Закрыть курсор*/

    SET param1 = sum;
  END//

CALL proc_CURSOR(@sum, @max_hd);
SELECT @sum, @max_hd;



# ------------------------------------------------Triggers---------------------------------------------------------------
USE sql_ex_computer;

DELIMITER //
 CREATE TRIGGER test_insertPC_trigger BEFORE INSERT ON PC
 FOR EACH ROW
 BEGIN
  SET NEW.cd = LEFT(NEW.cd,3);
  SET NEW.price = NEW.price/65;
 END//
INSERT INTO Product VALUES ('NEW', '6324','PC');
INSERT INTO PC VALUES (13, '6324', 500, 64, 40, '50xdfkgj', 85000);

DELIMITER //
CREATE TRIGGER test_updatePC_trigger BEFORE UPDATE ON PC
  FOR EACH ROW
  BEGIN
    SET NEW.cd = LEFT(NEW.cd,3);
    SET NEW.price = NEW.price/65;
  END//
UPDATE PC SET cd = '50xdfkgj', price = 85000 WHERE code = 13;

DELIMITER //
CREATE TRIGGER test_deletePC_trigger1 BEFORE DELETE ON PC
  FOR EACH ROW
  BEGIN
    INSERT INTO Product VALUES ('trig', '9999','PC');-- просто вставляем какую-то ерунду при удалении
  END//

CREATE TRIGGER test_deletePC_trigger2 AFTER DELETE ON PC
  FOR EACH ROW
  BEGIN
    DELETE FROM Product WHERE model = OLD.model;-- вместо каскада
  END//
DELETE FROM PC WHERE code = 13;

-- триггер обновляющий суммарные просмотры книг у авторов при обновлении книги
-- можно было использовать вместо логики в джава-методе BookService.update
USE aplib;
DELIMITER //
CREATE TRIGGER update_book_trigger BEFORE UPDATE ON book
  FOR EACH ROW
  BEGIN
    IF OLD.author_id != NEW.author_id THEN
    -- обновляем просмотры старых авторов обновляемой книги
    UPDATE author SET views = views - (SELECT b.views FROM book b WHERE b.id = OLD.id) WHERE id = OLD.author_id;
    -- обновляем просмотры новых авторов обновляемой книги
    UPDATE author SET views = views + (SELECT b.views FROM book b WHERE b.id = OLD.id) WHERE id = NEW.author_id;
    END IF;
  END//

UPDATE book SET author_id=140 WHERE id=48;




# ----------------------------------------Пользователи и права доступа--------------------------------------------------
# Авторизация в MySQL:
# sudo mysql --user=root --password=4
# Для выхода:
# exit
CREATE USER 'owner' IDENTIFIED BY '4';
CREATE USER 'aplib_owner' IDENTIFIED BY '4';
CREATE USER 'user_only_select' IDENTIFIED BY '4';
CREATE USER 'user_dml_select' IDENTIFIED BY '12345';

#GRANT [тип прав] ON [имя базы данных].[имя таблицы] TO ‘non-root’@'localhost’;
GRANT ALL PRIVILEGES ON *.* TO 'owner';
GRANT GRANT OPTION ON *.* TO 'owner';
GRANT ALL PRIVILEGES ON aplib.* TO 'aplib_owner';
GRANT SELECT ON *.* TO 'user_only_select';
GRANT SELECT, INSERT, UPDATE, DELETE ON *.* TO 'user_dml_select';
FLUSH PRIVILEGES;

# user_dml_select до REVOKE
USE sql_ex_computer;
SELECT * FROM PC WHERE PC.cd ='12x' ORDER BY PC.cd; # Ok
INSERT INTO Product VALUES ('B', '9999', 'PC'); # Ok
ALTER TABLE PC CHANGE model model_1 VARCHAR(50) NOT NULL; # - ALTER command denied to user 'user_dml_select'

# REVOKE
REVOKE SELECT ON *.* FROM 'user_dml_select';
FLUSH PRIVILEGES;

# user_dml_select после REVOKE
INSERT INTO Product VALUES ('B', '19991', 'PC'); # - ok
SELECT * FROM PC WHERE PC.cd ='12x' ORDER BY PC.cd; # - SELECT command denied to user 'user_dml_select'

SELECT User,Host FROM mysql.user;
SHOW GRANTS FOR user_dml_select;
SHOW GRANTS FOR 'owner';