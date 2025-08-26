-- 刪除
DROP SEQUENCE USERS_SEQ;
DROP TRIGGER USERS_BEFORE_INSERT;


-- 刪除表
DROP TABLE USERS;

-- 刪除表內所有資料
-- TRUNCATE TABLE USERS;


-- 查看 序列
-- SELECT SEQUENCE_NAME, MIN_VALUE, MAX_VALUE, INCREMENT_BY, LAST_NUMBER
-- FROM USER_SEQUENCES;
SELECT SEQUENCE_NAME, MIN_VALUE, MAX_VALUE, INCREMENT_BY, LAST_NUMBER
FROM USER_SEQUENCES;

-- 查看 觸發器
-- SELECT TRIGGER_NAME, TABLE_NAME, STATUS, TRIGGERING_EVENT, TRIGGER_TYPE
-- FROM USER_TRIGGERS;
SELECT TRIGGER_NAME, TABLE_NAME, STATUS, TRIGGERING_EVENT, TRIGGER_TYPE
FROM USER_TRIGGERS;

--------------------------------------------------
--------------------------------------------------


-- 1. 建表
CREATE TABLE USERS (
    ID NUMBER PRIMARY KEY,
    ROLE VARCHAR2(50) NOT NULL,
    USERNAME VARCHAR2(50) NOT NULL,
    PASSWORD VARCHAR2(100) NOT NULL,
    EMAIL VARCHAR2(100) NOT NULL,
    NICKNAME VARCHAR2(50),
    CONSTRAINT USERS_USERNAME_UK UNIQUE (USERNAME)
);


-- 2. 建序列
-- CREATE SEQUENCE：建立序列，名稱為 USERS_SEQ
-- NOCACHE：不使用快取
-- NOCYCLE：不循環（用完最大值就停止）
CREATE SEQUENCE USERS_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


-- 注意：如果在 Java 中的 Hibernate Entity 有使用 SequenceGenerator 就不用觸發器，但是依舊要建序列，並且序列名稱要跟 Java 中使用的一致
-- 3. 建觸發器，自動取得序列值
--CREATE OR REPLACE TRIGGER USERS_BEFORE_INSERT  -- CREATE OR REPLACE TRIGGER 是關鍵字，USERS_BEFORE_INSERT 是觸發器自定義名稱
--BEFORE INSERT ON USERS        -- 關鍵字，設定觸發器在 USERS 表新增資料前觸發
--FOR EACH ROW                  -- 關鍵字，表示每筆資料都觸發

--BEGIN                         -- PL/SQL 程式區塊開始
--    IF :NEW.ID IS NULL THEN   -- :NEW 是 Oracle 觸發器關鍵字，用來指向插入的新資料列
--        SELECT USERS_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;  
--        -- USERS_SEQ 是自定義序列名稱
--        -- NEXTVAL 是序列關鍵字，取得下一個序列值
--        -- INTO 是關鍵字，將序列值賦予 :NEW.ID
--        -- DUAL 是 Oracle 內建單筆虛擬表名
--    END IF;
--END;
--/






