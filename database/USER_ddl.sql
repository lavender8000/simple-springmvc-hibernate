-- �R��
DROP SEQUENCE USERS_SEQ;
DROP TRIGGER USERS_BEFORE_INSERT;


-- �R����
DROP TABLE USERS;

-- �R�����Ҧ����
-- TRUNCATE TABLE USERS;


-- �d�� �ǦC
-- SELECT SEQUENCE_NAME, MIN_VALUE, MAX_VALUE, INCREMENT_BY, LAST_NUMBER
-- FROM USER_SEQUENCES;
SELECT SEQUENCE_NAME, MIN_VALUE, MAX_VALUE, INCREMENT_BY, LAST_NUMBER
FROM USER_SEQUENCES;

-- �d�� Ĳ�o��
-- SELECT TRIGGER_NAME, TABLE_NAME, STATUS, TRIGGERING_EVENT, TRIGGER_TYPE
-- FROM USER_TRIGGERS;
SELECT TRIGGER_NAME, TABLE_NAME, STATUS, TRIGGERING_EVENT, TRIGGER_TYPE
FROM USER_TRIGGERS;

--------------------------------------------------
--------------------------------------------------


-- 1. �ت�
CREATE TABLE USERS (
    ID NUMBER PRIMARY KEY,
    ROLE VARCHAR2(50) NOT NULL,
    USERNAME VARCHAR2(50) NOT NULL,
    PASSWORD VARCHAR2(100) NOT NULL,
    EMAIL VARCHAR2(100) NOT NULL,
    NICKNAME VARCHAR2(50),
    CONSTRAINT USERS_USERNAME_UK UNIQUE (USERNAME)
);


-- 2. �اǦC
-- CREATE SEQUENCE�G�إߧǦC�A�W�٬� USERS_SEQ
-- NOCACHE�G���ϥΧ֨�
-- NOCYCLE�G���`���]�Χ��̤j�ȴN����^
CREATE SEQUENCE USERS_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


-- �`�N�G�p�G�b Java ���� Hibernate Entity ���ϥ� SequenceGenerator �N����Ĳ�o���A���O���­n�اǦC�A�åB�ǦC�W�٭n�� Java ���ϥΪ��@�P
-- 3. ��Ĳ�o���A�۰ʨ��o�ǦC��
--CREATE OR REPLACE TRIGGER USERS_BEFORE_INSERT  -- CREATE OR REPLACE TRIGGER �O����r�AUSERS_BEFORE_INSERT �OĲ�o���۩w�q�W��
--BEFORE INSERT ON USERS        -- ����r�A�]�wĲ�o���b USERS ��s�W��ƫeĲ�o
--FOR EACH ROW                  -- ����r�A��ܨC����Ƴ�Ĳ�o

--BEGIN                         -- PL/SQL �{���϶��}�l
--    IF :NEW.ID IS NULL THEN   -- :NEW �O Oracle Ĳ�o������r�A�Ψӫ��V���J���s��ƦC
--        SELECT USERS_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;  
--        -- USERS_SEQ �O�۩w�q�ǦC�W��
--        -- NEXTVAL �O�ǦC����r�A���o�U�@�ӧǦC��
--        -- INTO �O����r�A�N�ǦC�Ƚᤩ :NEW.ID
--        -- DUAL �O Oracle ���س浧������W
--    END IF;
--END;
--/






