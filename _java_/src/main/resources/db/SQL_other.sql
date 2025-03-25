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


# -------------------------------------------Ранжирующие, аналитические, агрегатные (для Oracle)----------------------------------------------------------
# ROW_NUMBER
SELECT ROW_NUMBER() OVER(ORDER BY ORDER_TYPE) as num, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;
SELECT ROW_NUMBER() OVER(ORDER BY ORDER_ID) as num, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;
SELECT ROW_NUMBER() OVER(PARTITION BY (ORDER_TYPE) ORDER BY ORDER_ID) as num, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;

# RANK
SELECT RANK() OVER(ORDER BY ORDER_TYPE) as num, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;
SELECT DENSE_RANK() OVER(ORDER BY ORDER_TYPE) as num, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;

# NTILE
SELECT NTILE(4) OVER(ORDER BY ORDER_TYPE) as num, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;
SELECT NTILE(4) OVER(PARTITION BY  (ORDER_TYPE) ORDER BY ORDER_TYPE) as num, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;
-- выводим все 4ые группы:
SELECT * FROM (SELECT NTILE(4) OVER(PARTITION BY  (ORDER_TYPE) ORDER BY ORDER_TYPE) as num, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE)
WHERE num=4;

# LAG
SELECT LAG(NOTICE_ID) OVER (ORDER BY NOTICE_ID) as LAG, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;
SELECT LEAD(NOTICE_ID) OVER (ORDER BY NOTICE_ID) as LAG, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;
-- группировка по ORDER_TYPE, внутри каждой группы сортировка по NOTICE_ID. LAG в начале новой группы берет не значение из предыдущей, а null:
SELECT LAG(NOTICE_ID) OVER (PARTITION BY (ORDER_TYPE) ORDER BY NOTICE_ID) as LAG, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;

#COUNT
UPDATE Product p SET p.maker = NULL WHERE p.type = 'Laptop';
SELECT count(      *       ) FROM Product;#выдаст кол-во строк
SELECT count(         maker) FROM Product;#выдаст кол-во не-NULL значений данного столбца
SELECT count(DISTINCT maker) FROM Product;#выдаст кол-во уникальных не-NULL значений данного столбца


# -------------------------------------------DDL, DML, NULL, Рекурсия----------------------------------------------------------
USE sql_ex_computer;

#DDL
CREATE TABLE Product
(
  maker VARCHAR(10) NOT NULL,
  model VARCHAR(50) NOT NULL
    PRIMARY KEY,
  type  VARCHAR(50) NOT NULL
);

ALTER TABLE Product
  MODIFY COLUMN maker VARCHAR(10) NULL;

DROP TABLE Product;

#UPDATE + JOIN
UPDATE PC pc
  JOIN Product prod ON prod.model=pc.model
SET pc.cd = '!!!!'
WHERE prod.maker = 'A';
#или без джойна:
UPDATE PC pc SET pc.cd = 'EEE'
WHERE pc.model IN (SELECT model FROM Product WHERE maker = 'E');

#DELETE
DELETE FROM Product WHERE cast(model as DECIMAL(16))>2000;

#Сравнение с NULL
#Операторы сравнения дают в результате величину 1 (истина, TRUE), 0 (ложь, FALSE) или NULL.
SELECT 1 < 2; -- вернет 1
SELECT 45 <=> NULL; -- вернет 0
SELECT NULL <=> NULL; -- вернет 1
SELECT NULL = NULL; -- вернет 0
SELECT 678 = NULL; -- вернет NULL
SELECT 23 < NULL; -- вернет NULL

SELECT 0 OR NULL; -- вернет NULL
SELECT FALSE OR NULL; -- вернет NULL
SELECT TRUE OR NULL; -- вернет 1 !!!
SELECT -346 OR NULL; -- вернет 1 !!!

SELECT TRUE AND NULL; -- вернет NULL
SELECT 43 AND NULL; -- вернет NULL
SELECT FALSE AND NULL; -- вернет 0 !!!
SELECT 0 AND NULL; -- вернет 0 !!!

UPDATE Printer p SET p.price = NULL WHERE p.code = 1;
#Если в секции WHERE оказался NULL - то это всегда ложь
SELECT * FROM Printer WHERE Printer.price <> 270;
SELECT * FROM Printer WHERE Printer.price <=> 270;
SELECT * FROM Printer WHERE Printer.price = 270;
SELECT * FROM Printer WHERE Printer.price > 0;
SELECT * FROM Printer WHERE Printer.price IS NULL ;


#Рекурсия - вывод алфавита (для MSSQL)
WITH Letters AS(
SELECT ASCII('A') code, CHAR(ASCII('A')) letter
UNION ALL
SELECT code+1, CHAR(code+1) FROM Letters
WHERE code+1 <= ASCII('Z')
)
SELECT letter FROM Letters;

#Рекурсия - вывод алфавита (для Oracle)
WITH Letters(code,letter) AS(
SELECT ASCII('A') code, CHR(ASCII('A')) letter from DUAL
UNION ALL
SELECT code+1, CHR(code+1) FROM Letters
WHERE code+1 <= ASCII('Z')
)
SELECT letter FROM Letters;

-------- рекурсия для PostgreSQL ----------
CREATE TABLE geo
(
    id        int not null primary key,
    parent_id int references geo (id),
    name      varchar(1000)
);

INSERT INTO geo
    (id, parent_id, name)
VALUES (1, null, 'Планета Земля'),
       (2, 1, 'Континент Евразия'),
       (3, 1, 'Континент Северная Америка'),
       (4, 2, 'Европа'),
       (5, 4, 'Россия'),
       (6, 4, 'Германия'),
       (7, 5, 'Москва'),
       (8, 5, 'Санкт-Петербург'),
       (9, 6, 'Берлин');

EXPLAIN
WITH RECURSIVE r AS (
    SELECT id, parent_id, name
    FROM geo
    WHERE parent_id = 4

    UNION

    SELECT geo.id, geo.parent_id, geo.name
    FROM geo
             JOIN r ON geo.parent_id = r.id
)
SELECT *
FROM r;

#Пример группировки по месяцам из дат (для postrgesql)
SELECT EXTRACT(MONTH FROM create_date), SUM(product_group_code) FROM task
GROUP BY EXTRACT(MONTH FROM create_date);

#Пример группировки по месяцам из дат (для postrgesql) + создание алиаса
SELECT MONTH, SUM(product_group_code) FROM task
JOIN LATERAL EXTRACT(MONTH FROM create_date) MONTH ON TRUE
GROUP BY MONTH
HAVING SUM(product_group_code) > 5