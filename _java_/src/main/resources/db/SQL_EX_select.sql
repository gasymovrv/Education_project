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


# --------------------------------------------------24------------------------------------------------------------------
# Перечислите номера моделей любых типов, имеющих самую высокую цену по всей имеющейся в базе данных продукции.

# Справка по теме:
# Объединение
# Получение итоговых значений
# Подзапросы
# CTE
/*
Вариант для Oracle:
WITH t1(model, price) as (
  SELECT model, price FROM PC
  UNION
  SELECT model, price FROM Laptop
  UNION
  SELECT model, price FROM Printer
)
SELECT model FROM t1
........
 */
USE sql_ex_computer;
SELECT model FROM (
 SELECT model, price FROM PC
 UNION
 SELECT model, price FROM Laptop
 UNION
 SELECT model, price FROM Printer
) t1
WHERE price = (
 SELECT MAX(price) FROM (
  SELECT price FROM PC
  UNION
  SELECT price FROM Laptop
  UNION
  SELECT price FROM Printer
  ) t2
 );

# --------------------------------------------------25------------------------------------------------------------------
# Найдите производителей принтеров, которые производят ПК с наименьшим объемом RAM и с самым быстрым процессором среди всех ПК, имеющих наименьший объем RAM. Вывести: Maker

# Справка по теме:
# Явные операции соединения
# Подзапросы
# Получение итоговых значений
# Предикат IN
USE sql_ex_computer;
SELECT DISTINCT maker FROM Product
WHERE model IN (
  SELECT model FROM PC
  WHERE ram = (
    SELECT MIN(ram) FROM PC
  )
  AND speed = (
    SELECT MAX(speed) FROM PC WHERE ram = (
      SELECT MIN(ram) FROM PC
    )
  )
)
AND
maker IN (
  SELECT maker FROM Product WHERE type = 'printer'
);

# ---------------------------------------------------26-----------------------------------------------------------------
# Найдите среднюю цену ПК и ПК-блокнотов, выпущенных производителем A (латинская буква). Вывести: одна общая средняя цена.

# Справка по теме:
# Явные операции соединения
# Подзапросы
# Объединение
# Получение итоговых значений
USE sql_ex_computer;
SELECT AVG(price)
FROM (
       SELECT p.price
       FROM PC p
         JOIN Product prod ON p.model = prod.model
       WHERE prod.maker = 'A'
       UNION ALL
       SELECT l.price
       FROM Laptop l
         JOIN Product prod ON l.model = prod.model
       WHERE prod.maker = 'A'
     ) t1;

# ---------------------------------------------------27-----------------------------------------------------------------
# Найдите средний размер диска ПК каждого из тех производителей, которые выпускают и принтеры. Вывести: maker, средний размер HD.

# Справка по теме:
# Получение итоговых значений
# Явные операции соединения
# Предикат IN
USE sql_ex_computer;
SELECT
  maker,
  AVG(hd)
FROM (
       SELECT
         prod.maker,
         p.hd
       FROM PC p
         JOIN Product prod ON p.model = prod.model
       WHERE prod.maker in (SELECT prod2.maker FROM Product prod2 WHERE prod2.type = 'Printer')
     ) t1
GROUP BY maker;

# ---------------------------------------------------28-----------------------------------------------------------------
# Используя таблицу Product, определить количество производителей, выпускающих по одной модели.

# Справка по теме:
# Предложение GROUP BY
# Предложение HAVING
# Подзапросы
USE sql_ex_computer;
SELECT COUNT(maker)
FROM Product
WHERE maker IN (SELECT
                  maker
                FROM Product
                GROUP BY maker
                HAVING COUNT(model) = 1);

# ---------------------------------------------------29-----------------------------------------------------------------
# В предположении, что приход и расход денег на каждом пункте приема фиксируется не чаще одного раза в день [т.е. первичный ключ (пункт, дата)],
# написать запрос с выходными данными (пункт, дата, приход, расход).
# Использовать таблицы Income_o и Outcome_o.

# Справка по теме:
# Явные операции соединения
# Объединение
# Оператор CASE
USE sql_ex_inc_out;
SELECT
  i.point,
  i.date,
  CASE
  WHEN i.inc IS NULL
    THEN '-'
  ELSE CAST(i.inc AS CHAR(20))
  END 'inc',
  CASE
  WHEN o.`out` IS NULL
    THEN '-'
  ELSE CAST(o.`out` AS CHAR(20))
  END 'out'
FROM Income_o i
  LEFT JOIN Outcome_o o ON i.point = o.point AND i.date = o.date
UNION
SELECT
  o.point,
  o.date,
  CASE
  WHEN i.inc IS NULL
    THEN '-'
  ELSE CAST(i.inc AS CHAR(20))
  END,
  CASE
  WHEN o.`out` IS NULL
    THEN '-'
  ELSE CAST(o.`out` AS CHAR(20))
  END
FROM Outcome_o o
  LEFT JOIN Income_o i ON o.point = i.point AND o.date = i.date;

# ---------------------------------------------------30-----------------------------------------------------------------
# В предположении, что приход и расход денег на каждом пункте приема фиксируется произвольное число раз (первичным ключом в таблицах является столбец code),
# требуется получить таблицу, в которой каждому пункту за каждую дату выполнения операций будет соответствовать одна строка.
# Вывод: point, date, суммарный расход пункта за день (out), суммарный приход пункта за день (inc).
# Отсутствующие значения считать неопределенными (NULL).

# Справка по теме:
# Предложение GROUP BY
# Внешние соединения
# Объединение
USE sql_ex_inc_out;
#Вариант с вью
/*
CREATE VIEW view_income AS
  SELECT
    i.point,
    i.date,
    SUM(i.inc) sum_inc
  FROM Income i
  GROUP BY i.point, i.date;

CREATE VIEW view_outcome AS
  SELECT
    o.point,
    o.date,
    SUM(o.out) sum_out
  FROM Outcome o
  GROUP BY o.point, o.date;

SELECT vi.point, vi.date, vo.sum_out, vi.sum_inc FROM view_income vi
  LEFT JOIN view_outcome vo ON vi.point = vo.point AND vi.date = vo.date
UNION
SELECT vo.point, vo.date, vo.sum_out, vi.sum_inc FROM view_outcome vo
  LEFT JOIN view_income vi ON vi.point = vo.point AND vi.date = vo.date
*/
#Вариант с CTE
SELECT
  vi.point,
  vi.date,
  vo.sum_out,
  vi.sum_inc
FROM (SELECT
        i.point,
        i.date,
        SUM(i.inc) sum_inc
      FROM Income i
      GROUP BY i.point, i.date) vi
  LEFT JOIN (SELECT
               o.point,
               o.date,
               SUM(o.out) sum_out
             FROM Outcome o
             GROUP BY o.point, o.date) vo ON vi.point = vo.point AND vi.date = vo.date
UNION
SELECT
  vo.point,
  vo.date,
  vo.sum_out,
  vi.sum_inc
FROM (SELECT
        o.point,
        o.date,
        SUM(o.out) sum_out
      FROM Outcome o
      GROUP BY o.point, o.date) vo
  LEFT JOIN (SELECT
               i.point,
               i.date,
               SUM(i.inc) sum_inc
             FROM Income i
             GROUP BY i.point, i.date) vi ON vi.point = vo.point AND vi.date = vo.date;

# ---------------------------------------------------31-----------------------------------------------------------------
# Для классов кораблей, калибр орудий которых не менее 16 дюймов, укажите класс и страну.

# Справка по теме:
# Простой оператор SELECT
USE sql_ex_ships;
SELECT
  class,
  country
FROM Classes
WHERE bore >= 16;

# ---------------------------------------------------32-----------------------------------------------------------------
# Одной из характеристик корабля является половина куба калибра его главных орудий (mw).
# С точностью до 2 десятичных знаков определите среднее значение mw для кораблей каждой страны, у которой есть корабли в базе данных.

# Справка по теме:
# Явные операции соединения
# Предложение GROUP BY
# Объединение
# Переименование столбцов и вычисления в результирующем наборе
# Преобразование типов
USE sql_ex_ships;
SELECT
  country,
  CAST(AVG(POW(bore, 3) / 2) AS DECIMAL(10, 2)) weight
FROM
  (SELECT
     country,
     bore,
    name
   FROM Classes c, Ships s
   WHERE s.class = c.class
   UNION
   SELECT
     country,
     bore,
     ship
   FROM Classes c, Outcomes o
   WHERE o.ship = c.class
         AND o.ship NOT IN (SELECT DISTINCT `name` FROM Ships)) t1
GROUP BY country;

# ---------------------------------------------------33-----------------------------------------------------------------
# Укажите корабли, потопленные в сражениях в Северной Атлантике (North Atlantic). Вывод: ship.

# Справка по теме:
# Простой оператор SELECT
# Предикаты (часть 1)
USE sql_ex_ships;
SELECT ship FROM Outcomes WHERE battle = 'North Atlantic' AND result='sunk';

# ---------------------------------------------------34-----------------------------------------------------------------
# По Вашингтонскому международному договору от начала 1922 г. запрещалось строить линейные корабли водоизмещением более 35 тыс.тонн.
# Укажите корабли, нарушившие этот договор (учитывать только корабли c известным годом спуска на воду).
# Вывести названия кораблей.

# Справка по теме:
#  Использование в запросе нескольких источников записей
USE sql_ex_ships;
SELECT DISTINCT s.name FROM Classes c, Ships s WHERE c.type = 'bb' AND c.displacement > 35000 AND s.launched IS NOT NULL AND s.launched >= 1922 AND c.class = s.class;

# ---------------------------------------------------35-----------------------------------------------------------------
# В таблице Product найти модели, которые состоят только из цифр или только из латинских букв (A-Z, без учета регистра).
# Вывод: номер модели, тип модели.

# Справка по теме:
# Предикат LIKE
USE sql_ex_computer;
#MSSQL
# SELECT
#   model,
#   type
# FROM Product
# WHERE upper(model) NOT LIKE '%[^A-Z]%'
#       OR model NOT LIKE '%[^0-9]%';

#MySQL
SELECT
  model,
  type
FROM Product
WHERE upper(model) REGEXP '^[A-Z]+$'
      OR model REGEXP '^[0-9]+$';

# ---------------------------------------------------36-----------------------------------------------------------------
# Перечислите названия головных кораблей, имеющихся в базе данных (учесть корабли в Outcomes).
# Справка по теме:
# Объединение
# Явные операции соединения
USE sql_ex_ships;
SELECT `name`
FROM Ships
WHERE class = name
UNION
SELECT ship AS name
FROM Classes, Outcomes
WHERE Classes.class = Outcomes.ship;

# ---------------------------------------------------37-----------------------------------------------------------------
# Найдите классы, в которые входит только один корабль из базы данных (учесть также корабли в Outcomes).
# Справка по теме:
# Объединение
# Явные операции соединения
# Предложение HAVING
USE sql_ex_ships;
SELECT c.class
FROM Classes c
  LEFT JOIN (
              SELECT
                class,
                name
              FROM Ships
              UNION
              SELECT
                ship,
                ship
              FROM Outcomes
            ) s ON s.class = c.class
GROUP BY c.class
HAVING COUNT(s.name) = 1;

# ---------------------------------------------------38-----------------------------------------------------------------
# Найдите страны, имевшие когда-либо классы обычных боевых кораблей ('bb') и имевшие когда-либо классы крейсеров ('bc').
# Справка по теме:
# Пересечение и разность
USE sql_ex_ships;
SELECT country
FROM Classes
GROUP BY country
HAVING COUNT(DISTINCT type) = 2;

# ---------------------------------------------------39-----------------------------------------------------------------
# Найдите корабли, `сохранившиеся для будущих сражений`;
# т.е. выведенные из строя в одной битве (damaged), они участвовали в другой, произошедшей позже.

# Справка по теме:
# Явные операции соединения
# Предикат EXISTS
USE sql_ex_ships;
SELECT DISTINCT a.ship
FROM (SELECT
        o.ship,
        b.name,
        b.date,
        o.result
      FROM Outcomes o
        LEFT JOIN Battles b ON o.battle = b.name) a
WHERE UPPER(a.ship) IN
      (SELECT UPPER(ship)
       FROM (SELECT
               o.ship,
               b.name,
               b.date,
               o.result
             FROM Outcomes o
               LEFT JOIN Battles b ON o.battle = b.name) b
       WHERE b.date < a.date AND b.result = 'damaged');

# ---------------------------------------------------40-----------------------------------------------------------------
# Найдите класс, имя и страну для кораблей из таблицы Ships, имеющих не менее 10 орудий.
# Справка по теме:
# Простой оператор SELECT
# Явные операции соединения
USE sql_ex_ships;
SELECT s.class, s.name, c.country
FROM Ships s
  LEFT JOIN Classes c ON s.class = c.class
WHERE c.numGuns >= 10;

# ---------------------------------------------------41-----------------------------------------------------------------
# Для ПК с максимальным кодом из таблицы PC вывести все его характеристики (кроме кода) в два столбца:
# - название характеристики (имя соответствующего столбца в таблице PC);
# - значение характеристики

# Справка по теме:
# Объединение данных из двух столбцов в один
# Преобразoвание типов
# Операторы PIVOT и UNPIVOT
USE sql_ex_computer;
#MSSQL
SELECT
  fields,
  A
FROM
  (
    SELECT
      cast(model AS NVARCHAR(10)) AS model,
      cast(speed AS NVARCHAR(10)) AS speed,
      cast(ram AS NVARCHAR(10))   AS ram,
      cast(hd AS NVARCHAR(10))    AS hd,
      cast(cd AS NVARCHAR(10))    AS cd,
      cast(price AS NVARCHAR(10)) AS price
    FROM PC
    WHERE code = (SELECT max(code)
                  FROM PC)
  ) AS t

unpivot
(
A FOR FIELDS IN (model, speed, ram, hd, cd, price)
) AS unpvt;

# ---------------------------------------------------43-----------------------------------------------------------------
# Найдите названия кораблей, потопленных в сражениях, и название сражения, в котором они были потоплены.
# Справка по теме:
# Простой оператор SELECT
USE sql_ex_ships;
SELECT name
FROM Battles
WHERE year(date) NOT IN
      (SELECT launched
       FROM Ships
       WHERE launched IS NOT NULL);

# ---------------------------------------------------44-----------------------------------------------------------------
# Найдите названия всех кораблей в базе данных, начинающихся с буквы R.
# Справка по теме:
# Объединение
# Предикат LIKE
USE sql_ex_ships;
SELECT name FROM Ships WHERE name LIKE 'R%'
UNION
SELECT Ship FROM Outcomes WHERE Ship LIKE 'R%';

# ---------------------------------------------------45-----------------------------------------------------------------
# Найдите названия всех кораблей в базе данных, состоящие из трех и более слов (например, King George V).
# Считать, что слова в названиях разделяются единичными пробелами, и нет концевых пробелов.
# Справка по теме:
# Объединение
# Предикат LIKE
USE sql_ex_ships;
SELECT name FROM Ships WHERE name LIKE '% % %'
UNION
SELECT ship FROM Outcomes WHERE ship LIKE '% % %';

# ---------------------------------------------------46-----------------------------------------------------------------
# Для каждого корабля, участвовавшего в сражении при Гвадалканале (Guadalcanal), вывести название, водоизмещение и число орудий.
# Справка по теме:
# Внешние соединения
USE sql_ex_ships;
SELECT
  o.ship,
  displacement,
  numGuns
FROM
  (SELECT
     name AS ship,
     displacement,
     numGuns
   FROM Ships s
     JOIN Classes c ON c.class = s.class
   UNION
   SELECT
     class AS ship,
     displacement,
     numGuns
   FROM Classes c) AS a
  RIGHT JOIN Outcomes o
    ON o.ship = a.ship
WHERE battle = 'Guadalcanal';

# ---------------------------------------------------47-----------------------------------------------------------------
# Пронумеровать строки из таблицы Product в следующем порядке: имя производителя в порядке убывания числа производимых им моделей (при одинаковом числе моделей имя производителя в алфавитном порядке по возрастанию), номер модели (по возрастанию).
# Вывод: номер в соответствии с заданным порядком, имя производителя (maker), модель (model)
# Справка по теме:
# Нумерация строк результатов запроса
USE sql_ex_computer;
#MSSQL
SELECT
  count(*) OVER (ORDER BY c.countM DESC, c.maker, c.model) no,
  c.maker,
  c.model
FROM (SELECT
        count(*) OVER(partition BY maker) countM,
        maker,
        model
      FROM product) c;

#MySQL - просто пример нумерции строк, но не решение этой задачи
SELECT
  @i:=@i+1 num,
  c.maker,
  c.model
 FROM Product c,(SELECT @i:=0) X;

# ---------------------------------------------------48-----------------------------------------------------------------
# Найдите классы кораблей, в которых хотя бы один корабль был потоплен в сражении.
# Справка по теме:
# Явные операции соединения
# Объединение
USE sql_ex_ships;
SELECT cl.class
FROM Classes cl
  LEFT JOIN Ships s ON s.class = cl.class
WHERE cl.class IN (SELECT ship
                   FROM Outcomes
                   WHERE result = 'sunk') OR
      s.name IN (SELECT ship
                 FROM Outcomes
                 WHERE result = 'sunk')
GROUP BY cl.class;

# ---------------------------------------------------49-----------------------------------------------------------------
# Найдите названия кораблей с орудиями калибра 16 дюймов (учесть корабли из таблицы Outcomes).
# Справка по теме:
# Явные операции соединения
# Объединение
USE sql_ex_ships;
SELECT Ships.name
FROM Classes JOIN
  Ships ON Classes.class = Ships.class
WHERE bore = 16
UNION
SELECT Outcomes.ship
FROM Outcomes JOIN
  Classes ON Classes.class = Outcomes.ship
WHERE bore = 16;

# ---------------------------------------------------50-----------------------------------------------------------------
# Найдите названия кораблей с орудиями калибра 16 дюймов (учесть корабли из таблицы Outcomes).
# Справка по теме:
# Явные операции соединения
# Объединение
USE sql_ex_ships;
SELECT DISTINCT Battle
FROM Outcomes
WHERE Ship IN (SELECT name
               FROM Ships
               WHERE class = 'kongo')