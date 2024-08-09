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


# ------------------------------------------------EXPLAIN,PROFILE + index---------------------------------------------------------------

SET profiling = 0;
SET profiling_history_size = 0;
SET profiling_history_size = 100;
SET profiling = 1;
SHOW PROFILE FOR QUERY 402;

SELECT * FROM book WHERE name='Посмертные записки Пиквикского клуба';
SELECT * FROM book ORDER BY name;
CREATE INDEX index1 ON book(name);
SELECT * FROM book ORDER BY name;

SHOW PROFILES;



USE sql_ex_computer;
DELIMITER //
CREATE PROCEDURE `rows_generator`(IN min INT, IN max INT)
  BEGIN
    DECLARE `index` INT DEFAULT min;

    WHILE `index` < max DO
      INSERT INTO Product VALUES ('B', `index`, 'PC');
      INSERT INTO PC(model, speed, ram, hd, cd, price) VALUES (`index`,500,64,5,concat('12x',`index`),600);
      SET `index` = `index` + 1;
    END WHILE;
  END//

CALL rows_generator(3000, 4999);

EXPLAIN SELECT * FROM Product, PC WHERE PC.cd LIKE '%12%' ORDER BY PC.cd;

CREATE INDEX index_pc_cd ON PC(cd);

SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd;
SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd;
SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd;
SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd;
SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd;
SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd;
SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd;
SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd;
SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd;

SHOW PROFILES;

-- при большом количестве не повторяющихся записей (около 8000 строк) 
-- разница в выборке с индексом и без него почти 10-кратная:

# с индексом (CREATE INDEX index_pc_cd ON PC(cd)):
# query_id    duration      query
# 10061	    0.00048825	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# 10062	    0.00036650	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# 10063	    0.00023600	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# 10064	    0.00025250	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# 10065	    0.00026275	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# 10066	    0.00023075	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# 10067	    0.00024250	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# 10068	    0.00043200	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# 10069	    0.00026600	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# 10070	    0.00023450	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# после удаления индекса:
# query_id    duration      query
# 10073	    0.00426150	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# 10074	    0.00294475	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# 10075	    0.00193200	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# 10076	    0.00198300	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# 10077	    0.00195475	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# 10078	    0.00200175	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# 10079	    0.00278550	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# 10080	    0.00199400	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
# 10081	    0.00243475	  SELECT * FROM PC WHERE PC.cd ='12x4500' ORDER BY PC.cd
USE sql_ex_computer;
EXPLAIN SELECT * FROM PC; -- type = ALL
EXPLAIN SELECT * FROM PC WHERE PC.price = 600; -- type = ALL
EXPLAIN SELECT * FROM PC WHERE PC.cd = '12x4500'; -- type = ref (работает созданный мной индекс index_pc_cd)
EXPLAIN SELECT * FROM Product WHERE maker = 'A'; -- type = ALL
EXPLAIN SELECT * FROM Product WHERE model = '1232'; -- type = const (работает дефолтный индекс MySQL)
use sql_ex_inc_out;
EXPLAIN SELECT * FROM Income_o WHERE point = 1 AND  date < '2001-03-24'; -- type = range



# ------------------------- План запроса ORACLE --------------------------------------
-- сначало это
EXPLAIN PLAN FOR
SELECT ROW_NUMBER() OVER(ORDER BY ORDER_TYPE) as num, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;
-- потом просмотр результатов
SELECT*FROM TABLE(dbms_xplan.display(NULL,NULL,'basic'));-- вар 1
SELECT PLAN_TABLE_OUTPUT FROM TABLE(DBMS_XPLAN.DISPLAY());-- вар 2 - более информативный


-- пример 1 (нет индекса на столбец-фильтр)
EXPLAIN PLAN for
SELECT * FROM NOTIF_Z.ORDERS o WHERE o.PLACER_ORG_ROLE = 'OA';
SELECT PLAN_TABLE_OUTPUT FROM TABLE(DBMS_XPLAN.DISPLAY());-- вар 2 - более информативный
# ----------------------------------------------------------------------------
# | Id  | Operation         | Name   | Rows  | Bytes | Cost (%CPU)| Time     |
# ----------------------------------------------------------------------------
# |   0 | SELECT STATEMENT  |        |    95 | 18430 |     5   (0)| 00:00:01 |
# |*  1 |  TABLE ACCESS FULL| ORDERS |    95 | 18430 |     5   (0)| 00:00:01 | полный перебор всех строк таблицы
# ----------------------------------------------------------------------------

-- пример 2 (все данные из индекса)
EXPLAIN PLAN for
SELECT o.ORDER_ID FROM NOTIF_Z.ORDERS o;
SELECT PLAN_TABLE_OUTPUT FROM TABLE(DBMS_XPLAN.DISPLAY());-- вар 2 - более информативный
# ------------------------------------------------------------------------------
# | Id  | Operation        | Name      | Rows  | Bytes | Cost (%CPU)| Time     |
# ------------------------------------------------------------------------------
# |   0 | SELECT STATEMENT |           |   380 |  1900 |     1   (0)| 00:00:01 |
# |   1 |  INDEX FULL SCAN | PK_ORDERS |   380 |  1900 |     1   (0)| 00:00:01 |
# ------------------------------------------------------------------------------


-- пример 3 (есть индекс на столбец-фильтр - не уникальный)
EXPLAIN PLAN for
SELECT * FROM NOTIF_Z.ORDERS o WHERE o.ORGANIZATION_ID = 11988;
SELECT PLAN_TABLE_OUTPUT FROM TABLE(DBMS_XPLAN.DISPLAY());-- вар 2 - более информативный
# ----------------------------------------------------------------------------------------------------------
# | Id  | Operation                   | Name                       | Rows  | Bytes | Cost (%CPU)| Time     |
# ----------------------------------------------------------------------------------------------------------
# |   0 | SELECT STATEMENT            |                            |     1 |   194 |     2   (0)| 00:00:01 |
# |   1 |  TABLE ACCESS BY INDEX ROWID| ORDERS                     |     1 |   194 |     2   (0)| 00:00:01 |
# |*  2 |   INDEX RANGE SCAN          | IDX_ORDERS_ORGANIZATION_ID |     1 |       |     1   (0)| 00:00:01 | поиск по индексу
# ----------------------------------------------------------------------------------------------------------



-- пример 4 (есть индекс на столбец-фильтр - уникальный)
EXPLAIN PLAN for
SELECT * FROM NOTIF_Z.ORDERS o WHERE o.ORDER_ID = 10281;
SELECT PLAN_TABLE_OUTPUT FROM TABLE(DBMS_XPLAN.DISPLAY());-- вар 2 - более информативный
# -----------------------------------------------------------------------------------------
# | Id  | Operation                   | Name      | Rows  | Bytes | Cost (%CPU)| Time     |
# -----------------------------------------------------------------------------------------
# |   0 | SELECT STATEMENT            |           |     1 |   194 |     1   (0)| 00:00:01 |
# |   1 |  TABLE ACCESS BY INDEX ROWID| ORDERS    |     1 |   194 |     1   (0)| 00:00:01 |
# |*  2 |   INDEX UNIQUE SCAN         | PK_ORDERS |     1 |       |     0   (0)| 00:00:01 | поиск по уникальному индексу
# -----------------------------------------------------------------------------------------



-- пример 5 (поиск напрямую по системному указателю ROWID)
EXPLAIN PLAN for
SELECT * FROM NOTIF_Z.ORDERS o WHERE o.ROWID = 'AABBRHAAKAAAAMSAAF';
SELECT PLAN_TABLE_OUTPUT FROM TABLE(DBMS_XPLAN.DISPLAY());-- вар 2 - более информативный
# -------------------------------------------------------------------------------------
# | Id  | Operation                  | Name   | Rows  | Bytes | Cost (%CPU)| Time     |
# -------------------------------------------------------------------------------------
# |   0 | SELECT STATEMENT           |        |     1 |   194 |     1   (0)| 00:00:01 |
# |   1 |  TABLE ACCESS BY USER ROWID| ORDERS |     1 |   194 |     1   (0)| 00:00:01 | поиск по ROWID
# -------------------------------------------------------------------------------------
