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


# --------------------------------------------------4------------------------------------------------------------------
# Для каждой группы блокнотов с одинаковым номером модели добавить запись в таблицу PC со следующими характеристиками:
# код: минимальный код блокнота в группе +20;
# модель: номер модели блокнота +1000;
# скорость: максимальная скорость блокнота в группе;
# ram: максимальный объем ram блокнота в группе *2;
# hd: максимальный объем hd блокнота в группе *2;
# cd: значение по умолчанию;
# цена: максимальная цена блокнота в группе, уменьшенная в 1,5 раза.
# Замечание. Считать номер модели числом.

# Справка по теме:
# Оператор INSERT
# Предложение GROUP BY
USE sql_ex_computer;

INSERT INTO Product (maker, model, type) SELECT 'A', model + 1000, 'PC' FROM Laptop GROUP BY model;
INSERT INTO PC (code, model, speed, ram, hd, cd, price)
  SELECT
    MIN(code) + 20,
    model + 1000,
    MAX(speed),
    MAX(ram) * 2,
    MAX(hd) * 2,
    '12x',
    MAX(price) / 1.5
  FROM Laptop
  GROUP BY model;


# --------------------------------------------------12------------------------------------------------------------------
# Добавьте один дюйм к размеру экрана каждого блокнота,
# выпущенного производителями E и B, и уменьшите его цену на $100.
# Справка по теме:
# Оператор UPDATE
UPDATE Laptop l
SET
  screen = screen + 1,
  price  = price - 100
WHERE l.model IN (SELECT model
                  FROM Product
                  WHERE maker = 'E' OR maker = 'B');


# --------------------------------------------------14------------------------------------------------------------------
# Удалите классы, имеющие в базе данных менее трех кораблей (учесть корабли из Outcomes).

# Справка по теме:
# Оператор DELETE
USE sql_ex_ships;
DELETE FROM Classes
WHERE class NOT IN (
  SELECT t.klas
  FROM
    (SELECT
       class        klas,
       count(class) no
     FROM Ships
     GROUP BY class

     UNION ALL

     SELECT
       ship klas,
       count(ship) no
     FROM Outcomes
     WHERE ship NOT IN (SELECT s1.name
                        FROM Ships s1)
     GROUP BY ship) t
  GROUP BY t.klas
  HAVING sum(t.no) >= 3);




