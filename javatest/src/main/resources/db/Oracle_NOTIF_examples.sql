-- Локальная БД DEV 8.0.0
-- URL: jdbc:oracle:thin:@192.168.56.217:1521:XE
-- login: NOTIF_Z
-- pass: NOTIF_Z
--или
-- login: smithj
-- pass: pwd4smithj

ALTER USER NOTIF_Z_RW IDENTIFIED BY "NOTIF_Z_RW";-- если пароль просрочился
ALTER PROFILE DEFAULT LIMIT PASSWORD_LIFE_TIME UNLIMITED;-- чтобы пароль был вечным

--куча кода чтобы тупо создать схему (в оракл схема и юзер создаются одновременно)
----------------------------------------------begin-----------------------------------------
--заходим через sys as sysdba, затем:
CREATE TEMPORARY TABLESPACE tbs_temp_01 -- неведомая херня
TEMPFILE 'tbs_temp_01.dbf'
SIZE 5M
AUTOEXTEND ON;

CREATE TABLESPACE tbs_perm_01 -- неведомая херня
DATAFILE 'tbs_perm_01.dat'
SIZE 20M
ONLINE;

CREATE USER smithj
IDENTIFIED BY pwd4smithj
  DEFAULT TABLESPACE tbs_perm_01 -- подключаем 2 неведомые херни
  TEMPORARY TABLESPACE tbs_temp_01
  QUOTA 20M on tbs_perm_01; -- еще какую-то квоту

GRANT ALL PRIVILEGES TO smithj; -- может достаточно и этой строчки, но лучше захреначить и все права ниже
GRANT create session TO smithj;
GRANT create table TO smithj;
GRANT create view TO smithj;
GRANT create any trigger TO smithj;
GRANT create any procedure TO smithj;
GRANT create sequence TO smithj;
GRANT create synonym TO smithj;

alter session set current_schema = smithj; -- так и не понял нужна эта хрень или нет

-- теперь нужно зайти через smithj, затем сто:
CREATE SCHEMA AUTHORIZATION smithj
  CREATE TABLE products -- просто создать схему нельзя, нужно обязательно что-то создавать
  ( product_id number(10) not null,
    product_name varchar2(50) not null,
    category varchar2(50),
    CONSTRAINT products_pk PRIMARY KEY (product_id)
  );
----------------------------------------------end-----------------------------------------



SELECT * FROM NOTIF_Z.NOTICE ORDER BY NOTICE_ID DESC;
SELECT NOTICE_ID, ORDER_ID, ORDER_NAME, STATUS FROM NOTIF_Z.NOTICE ORDER BY NOTICE_ID DESC;
SELECT * FROM NOTIF_Z.PROCEDURES;
SELECT * FROM NOTIF_Z.PROTOCOLS_CANCEL;
SELECT * FROM NOTIF_Z.PROTOCOLS;
SELECT * FROM NOTIF_Z.STPROTOCOLS;
SELECT * FROM NOTIF_Z.ORDERS;
SELECT * FROM NOTIF_Z.LOT_POSITION;
SELECT * FROM NOTIF_Z.LOT;
SELECT * FROM NOTIF_Z.DOC_EXPLAIN_REQUESTS;
SELECT * FROM NOTIF_Z.CUSTOMER_REQST_NOTICE;
SELECT * FROM NOTIF_Z.LOT_LIFE_CYCLE_CASES;
SELECT * FROM NOTIF_Z.XSL_TEMPLATE;
SELECT * FROM NOTIF_Z.PRINT_FORM;
SELECT * FROM NOTIF_Z.PRINT_FORM_CONTENT;
SELECT * FROM NOTIF_Z.MEDICINES;

---------------------------------------------------Аналитика-------------------------------------------------

SELECT ROW_NUMBER() OVER(ORDER BY ORDER_TYPE) as num, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;
SELECT ROW_NUMBER() OVER(ORDER BY ORDER_ID) as num, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;
SELECT ROW_NUMBER() OVER(PARTITION BY (ORDER_TYPE) ORDER BY ORDER_ID) as num, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;

SELECT RANK() OVER(ORDER BY ORDER_TYPE) as num, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;
SELECT DENSE_RANK() OVER(ORDER BY ORDER_TYPE) as num, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;

SELECT NTILE(4) OVER(ORDER BY ORDER_TYPE) as num, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;
SELECT NTILE(4) OVER(PARTITION BY  (ORDER_TYPE) ORDER BY ORDER_TYPE) as num, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;

SELECT * FROM (SELECT NTILE(4) OVER(PARTITION BY  (ORDER_TYPE) ORDER BY ORDER_TYPE) as num, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE)
WHERE num=4;
EXPLAIN PLAN for
SELECT LAG(NOTICE_ID) OVER (ORDER BY NOTICE_ID) as LAG, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;
SELECT LEAD(NOTICE_ID) OVER (ORDER BY NOTICE_ID) as LAG, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;
SELECT LAG(NOTICE_ID) OVER (PARTITION BY (ORDER_TYPE) ORDER BY NOTICE_ID) as LAG, NOTICE_ID, ORDER_ID, ORDER_NAME,ORDER_TYPE,STATUS FROM NOTIF_Z.NOTICE;

---------------------------------------------------Рекурсия-------------------------------------------------

EXPLAIN PLAN for
WITH Letters(code,letter) AS(
SELECT ASCII('A') code, CHR(ASCII('A')) letter from DUAL
UNION ALL
SELECT code+1, CHR(code+1) FROM Letters
WHERE code+1 <= ASCII('Z')
)
SELECT letter FROM Letters;
SELECT PLAN_TABLE_OUTPUT FROM TABLE(DBMS_XPLAN.DISPLAY());-- вар 2 - более информативный




---------------------------------------------------Планы запроса-------------------------------------------------

-- пример 1 (нет индекса на столбец-фильтр)
EXPLAIN PLAN for
SELECT * FROM NOTIF_Z.ORDERS o WHERE o.PLACER_ORG_ROLE = 'OA';
SELECT PLAN_TABLE_OUTPUT FROM TABLE(DBMS_XPLAN.DISPLAY());-- вар 2 - более информативный
-- ----------------------------------------------------------------------------
-- | Id  | Operation         | Name   | Rows  | Bytes | Cost (%CPU)| Time     |
-- ----------------------------------------------------------------------------
-- |   0 | SELECT STATEMENT  |        |    95 | 18430 |     5   (0)| 00:00:01 |
-- |*  1 |  TABLE ACCESS FULL| ORDERS |    95 | 18430 |     5   (0)| 00:00:01 | полный перебор всех строк таблицы
-- ----------------------------------------------------------------------------

-- пример 2 (все данные из индекса)
EXPLAIN PLAN for
SELECT o.ORDER_ID FROM NOTIF_Z.ORDERS o;
SELECT PLAN_TABLE_OUTPUT FROM TABLE(DBMS_XPLAN.DISPLAY());-- вар 2 - более информативный
-- ------------------------------------------------------------------------------
-- | Id  | Operation        | Name      | Rows  | Bytes | Cost (%CPU)| Time     |
-- ------------------------------------------------------------------------------
-- |   0 | SELECT STATEMENT |           |   380 |  1900 |     1   (0)| 00:00:01 |
-- |   1 |  INDEX FULL SCAN | PK_ORDERS |   380 |  1900 |     1   (0)| 00:00:01 |
-- ------------------------------------------------------------------------------


-- пример 3 (есть индекс на столбец-фильтр - не уникальный)
EXPLAIN PLAN for
SELECT * FROM NOTIF_Z.ORDERS o WHERE o.ORGANIZATION_ID = 367;
SELECT PLAN_TABLE_OUTPUT FROM TABLE(DBMS_XPLAN.DISPLAY());-- вар 2 - более информативный
-- ----------------------------------------------------------------------------------------------------------
-- | Id  | Operation                   | Name                       | Rows  | Bytes | Cost (%CPU)| Time     |
-- ----------------------------------------------------------------------------------------------------------
-- |   0 | SELECT STATEMENT            |                            |     1 |   194 |     2   (0)| 00:00:01 |
-- |   1 |  TABLE ACCESS BY INDEX ROWID| ORDERS                     |     1 |   194 |     2   (0)| 00:00:01 |
-- |*  2 |   INDEX RANGE SCAN          | IDX_ORDERS_ORGANIZATION_ID |     1 |       |     1   (0)| 00:00:01 | поиск по индексу
-- ----------------------------------------------------------------------------------------------------------



-- пример 4 (есть индекс на столбец-фильтр - уникальный)
EXPLAIN PLAN for
SELECT * FROM NOTIF_Z.ORDERS o WHERE o.ORDER_ID = 114;
SELECT PLAN_TABLE_OUTPUT FROM TABLE(DBMS_XPLAN.DISPLAY());-- вар 2 - более информативный
-- -----------------------------------------------------------------------------------------
-- | Id  | Operation                   | Name      | Rows  | Bytes | Cost (%CPU)| Time     |
-- -----------------------------------------------------------------------------------------
-- |   0 | SELECT STATEMENT            |           |     1 |   194 |     1   (0)| 00:00:01 |
-- |   1 |  TABLE ACCESS BY INDEX ROWID| ORDERS    |     1 |   194 |     1   (0)| 00:00:01 |
-- |*  2 |   INDEX UNIQUE SCAN         | PK_ORDERS |     1 |       |     0   (0)| 00:00:01 | поиск по уникальному индексу
-- -----------------------------------------------------------------------------------------



-- пример 5 (поиск напрсмую по системному указателю ROWID)
EXPLAIN PLAN for
SELECT ROWID FROM NOTIF_Z.ORDERS o WHERE o.ORDER_ID = 114;
SELECT * FROM NOTIF_Z.ORDERS o WHERE o.ROWID = 'AAAE8sAAJAAAAEzAAF';
SELECT PLAN_TABLE_OUTPUT FROM TABLE(DBMS_XPLAN.DISPLAY());-- вар 2 - более информативный
-- -------------------------------------------------------------------------------------
-- | Id  | Operation                  | Name   | Rows  | Bytes | Cost (%CPU)| Time     |
-- -------------------------------------------------------------------------------------
-- |   0 | SELECT STATEMENT           |        |     1 |   194 |     1   (0)| 00:00:01 |
-- |   1 |  TABLE ACCESS BY USER ROWID| ORDERS |     1 |   194 |     1   (0)| 00:00:01 | поиск по ROWID
-- -----------------------------------------------------------------------------------------



------------- пример как создание индекса повлияло на план запроса --------------
CREATE INDEX ind1 on PC (cd);

EXPLAIN PLAN for
SELECT * FROM PC WHERE cd = '12x';
SELECT PLAN_TABLE_OUTPUT FROM TABLE(DBMS_XPLAN.DISPLAY());

--без индекса
----------------------------------------------------------------------------
--| Id  | Operation         | Name | Rows  | Bytes | Cost (%CPU)| Time     |
----------------------------------------------------------------------------
--|   0 | SELECT STATEMENT  |      |     4 |   396 |     3   (0)| 00:00:01 |
--|*  1 |  TABLE ACCESS FULL| PC   |     4 |   396 |     3   (0)| 00:00:01 |
----------------------------------------------------------------------------

--с индексом
--------------------------------------------------------------------------------------
--| Id  | Operation                   | Name | Rows  | Bytes | Cost (%CPU)| Time     |
--------------------------------------------------------------------------------------
--|   0 | SELECT STATEMENT            |      |     4 |   396 |     2   (0)| 00:00:01 |
--|   1 |  TABLE ACCESS BY INDEX ROWID| PC   |     4 |   396 |     2   (0)| 00:00:01 |
--|*  2 |   INDEX RANGE SCAN          | IND1 |     1 |       |     1   (0)| 00:00:01 |
--------------------------------------------------------------------------------------
DROP INDEX ind1;





---------------------------------------------------Процедуры-------------------------------------------------

--включение консоли
set serveroutput on;

-- создание процедуры
CREATE OR REPLACE PROCEDURE simpleProc(m in VARCHAR)
IS
  BEGIN
    -- DBMS_OUTPUT.enable;
    DBMS_OUTPUT.put_line('Печатаем из процедуры:' || m);-- в идее сначала нужно нажать желтый квадратик у окна Database Console
  END;
/

-- анонимный блок с вызовом процедуры (простой select тут не работает)
BEGIN
  simpleProc('12314155');
END;
/



-- создание процедуры
CREATE OR REPLACE PROCEDURE TESTOUT(model_ IN VARCHAR2, maker_ OUT VARCHAR2)
IS
  BEGIN
    SELECT p.maker INTO maker_ FROM product p WHERE p.model = model_;
  END TESTOUT;
/

-- анонимный блок с переменными и вызовом процедуры  (простой select тут не работает)
DECLARE
  model_ VARCHAR2(50) DEFAULT '1121';
  maker_ VARCHAR2(10);
BEGIN
  TESTOUT(model_, maker_);
  DBMS_OUTPUT.put_line('model: '|| model_);
  DBMS_OUTPUT.put_line('maker: '|| maker_);
END;
/




-- создание процедуры (пример неизменяемости IN-параметра)
CREATE OR REPLACE PROCEDURE TESTOUT2(model_ IN VARCHAR2, maker_ OUT VARCHAR2)
IS
  BEGIN
    model_:='1288';-- ошибка
    SELECT p.maker INTO maker_ FROM product p WHERE p.model = model_;
  END TESTOUT2;
/




-- создание процедуры с доп. переменными и обр. исключений (если строк больше 1)
CREATE OR REPLACE PROCEDURE write_from_cd(model_ IN PC.model%TYPE, code_ IN PC.code%TYPE) -- берем типы из полей
IS
  cd_ pc.cd%TYPE;
  BEGIN
    SELECT p.cd INTO cd_ FROM PC p WHERE p.model = model_;
    DBMS_OUTPUT.put_line('model: ' || model_);
    DBMS_OUTPUT.put_line('cd: ' || cd_);
  EXCEPTION
    WHEN TOO_MANY_ROWS THEN
      SELECT p.cd INTO cd_ FROM PC p WHERE p.code = code_;
      DBMS_OUTPUT.put_line('code: ' || code_);
      DBMS_OUTPUT.put_line('cd: ' || cd_);
      -- DBMS_OUTPUT.put_line('EXCEPTION!!! TOO_MANY_ROWS!');
    WHEN OTHERS THEN
      DBMS_OUTPUT.put_line('EXCEPTION!!!');
  END;
/
BEGIN
  DBMS_OUTPUT.put_line('----1121 несколько, поэтому ищем по коду:-------');
  write_from_cd('1121', 4);
  DBMS_OUTPUT.put_line('----1260 такая одна, поэтому ищем по модели:-------');
  write_from_cd('1260', 4);
END;
/




-- процедура возвращающая ResultSet
CREATE OR REPLACE PROCEDURE result_set_procedure(retval OUT SYS_REFCURSOR) IS
  BEGIN
    OPEN retval FOR
     SELECT p.CODE,p.MODEL,p.SPEED,p.RAM,p.HD,p.CD,p.PRICE,prod.MAKER FROM PC p
       JOIN PRODUCT prod ON p.MODEL = prod.MODEL;
  END result_set_procedure;
/


DECLARE
  myrefcur  SYS_REFCURSOR;
  code_    PC.CODE%TYPE;
  model_    PC.MODEL%TYPE;
  speed_    PC.SPEED%TYPE;
  ram_    PC.RAM%TYPE;
  hd_    PC.HD%TYPE;
  cd_    PC.CD%TYPE;
  price_    PC.PRICE%TYPE;
  maker_    PRODUCT.MAKER%TYPE;
  pc_    PC%ROWTYPE;
BEGIN
  result_set_procedure(myrefcur);
  LOOP
    FETCH myrefcur INTO code_,model_,speed_,ram_,hd_,cd_,price_,maker_;
    EXIT WHEN myrefcur%NOTFOUND;
    dbms_output.put_line( code_ || ', ' || model_ || ', ' || speed_ || ', ' || ram_ || ', ' || hd_ || ', ' || cd_ || ', ' || price_ || ', ' || maker_);
  END LOOP;
  CLOSE myrefcur;
END;


-- удаление процедур
DROP PROCEDURE SIMPLEPROC;
DROP PROCEDURE TESTOUT;
DROP PROCEDURE TESTOUT2;
DROP PROCEDURE write_from_cd;
DROP PROCEDURE result_set_procedure;



---------------------------------------------------Курсоры------------------------------------------------

-- Создаем простой курсор в анонимном блоке
DECLARE
  CURSOR c1 IS
    SELECT * FROM PRODUCT p;
BEGIN
  FOR item IN c1 -- item - это строка из результирующего набора
  LOOP
    DBMS_OUTPUT.put_line('maker: ' || item.MAKER || ', model: ' || item.MODEL || ', type: ' || item.TYPE);
  END LOOP;
END;
/





-- создание процедуры с курсором
CREATE OR REPLACE PROCEDURE write_from_cd_cur(model_ IN PC.model%TYPE) -- берем типы из полей
IS
  CURSOR c1 IS
    SELECT p.cd FROM PC p WHERE p.model = model_;

  BEGIN
    FOR item IN c1 -- item - это строка из результирующего набора
    LOOP
      DBMS_OUTPUT.put_line('model: ' || model_ || ', cd: ' || item.CD);
    END LOOP;

    EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.put_line('EXCEPTION!!!');
  END;
/
BEGIN
  DBMS_OUTPUT.put_line('----1121:-------');
  write_from_cd_cur('1121');
  DBMS_OUTPUT.put_line('----1233:-------');
  write_from_cd_cur('1233');
END;
/



---------------------------------------------------Триггеры------------------------------------------------

-- операторный триггер
CREATE OR REPLACE TRIGGER BFINS_Prod BEFORE INSERT ON PRODUCT
  DECLARE
  BEGIN
    DBMS_OUTPUT.put_line('before insert PRODUCT!');
  END BFINS_Prod;
/

-- операторный триггер
CREATE OR REPLACE TRIGGER AFINS_Prod AFTER INSERT ON PRODUCT
  DECLARE
  BEGIN
    DBMS_OUTPUT.put_line('after insert PRODUCT!');
  END AFINS_Prod;
/

-- строковый триггер, для каждой добавленной строки делает свое действие
CREATE OR REPLACE TRIGGER AFINS_Prod_fer AFTER INSERT ON PRODUCT
  FOR EACH ROW
  DECLARE
  BEGIN
    DBMS_OUTPUT.put_line('Вставлена запись: maker: ' || :new.MAKER || ', model: ' || :new.MODEL || ', type: ' || :new.TYPE);
  END AFINS_Prod_fer;
/

--тестируем 3 предыдущих триггера
INSERT INTO Product VALUES ('NEW', '6324','PC');
DELETE FROM PRODUCT WHERE MODEL = '6324';

ALTER TRIGGER AFINS_Prod_fer  DISABLE;-- отключаем триггер

--триггер на обновление
CREATE OR REPLACE TRIGGER BFUP_Prod BEFORE UPDATE ON PRODUCT
  FOR EACH ROW
  DECLARE
  BEGIN
    DBMS_OUTPUT.put_line('Старая запись: maker: ' || :old.MAKER || ', model: ' || :old.MODEL || ', type: ' || :old.TYPE);
    DBMS_OUTPUT.put_line('Обновленная запись: maker: ' || :new.MAKER || ', model: ' || :new.MODEL || ', type: ' || :new.TYPE);
  END BFUP_Prod;
/

INSERT INTO Product VALUES ('NEW', '6324','PC');
--тестируем триггер обновления
UPDATE Product SET MAKER = 'ABCD', TYPE ='GAME' WHERE MODEL='6324';
DELETE FROM PRODUCT WHERE MODEL = '6324';

----------------------------------------------Разное--------------------------------------------------------------------
--апдейт внутри которого поле задается через селект
UPDATE laptop l
SET
  screen = screen + 1,
  price  = (SELECT avg(p.price) FROM laptop p)
;

--на случай уменьшения размера поля NUMBER
SELECT * from INCOME;

ALTER TABLE INCOME ADD AMOUNT_TEMP NUMBER(29, 11);
UPDATE INCOME SET AMOUNT_TEMP = INC;
UPDATE INCOME SET INC = NULL;
ALTER TABLE INCOME MODIFY INC NUMBER(12, 2);
UPDATE INCOME SET INC = AMOUNT_TEMP;
ALTER TABLE INCOME DROP COLUMN AMOUNT_TEMP;

SELECT * from INCOME;

SELECT DATA_SCALE, DATA_PRECISION
FROM USER_TAB_COLUMNS
WHERE TABLE_NAME = 'INCOME'
AND COLUMN_NAME = 'INC';


SELECT * from PC;
SELECT * from Product;