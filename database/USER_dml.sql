--  �إ�Ĳ�o���A�۰ʨ��o�ǦC�ȡA�W�٬��GUSERS_BEFORE_INSERT
--  �]�wĲ�o���b USERS ��s�W��ƫeĲ�o
--  �C����Ƴ�Ĳ�o
CREATE OR REPLACE TRIGGER USERS_BEFORE_INSERT
BEFORE INSERT ON USERS
FOR EACH ROW

-- :NEW �O Oracle Ĳ�o������r�A�Ψӫ��V���J���s��ƦC
BEGIN
    IF :NEW.ID IS NULL THEN
        SELECT USERS_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
        -- USERS_SEQ �O�۩w�q�ǦC�W��
        -- NEXTVAL �O�ǦC����r�A���o�U�@�ӧǦC��
        -- INTO �O����r�A�N�ǦC�Ƚᤩ :NEW.ID
        -- DUAL �O Oracle ���س浧������W
    END IF;
END;
/


--  �إ߸��  �K�X�� 666666
INSERT INTO USERS (ROLE, USERNAME, PASSWORD, EMAIL, NICKNAME) VALUES ('ROLE_USER', 'alice', '$2a$10$V0gkpemrKV0w5w3/stC0m.CCtzkjl9nB4ktyznQh6S/vKOQYjpr3.', 'alice.wang@example.com', 'Alice');
INSERT INTO USERS (ROLE, USERNAME, PASSWORD, EMAIL, NICKNAME) VALUES ('ROLE_USER', 'bob', '$2a$10$V0gkpemrKV0w5w3/stC0m.CCtzkjl9nB4ktyznQh6S/vKOQYjpr3.', 'bob.liu@example.com', 'Bobby');
INSERT INTO USERS (ROLE, USERNAME, PASSWORD, EMAIL, NICKNAME) VALUES ('ROLE_USER', 'charlie.chen', '$2a$10$V0gkpemrKV0w5w3/stC0m.CCtzkjl9nB4ktyznQh6S/vKOQYjpr3.', 'charlie.chen@example.com', 'Charlie');
INSERT INTO USERS (ROLE, USERNAME, PASSWORD, EMAIL, NICKNAME) VALUES ('ROLE_USER', 'diana.hsu', '$2a$10$V0gkpemrKV0w5w3/stC0m.CCtzkjl9nB4ktyznQh6S/vKOQYjpr3.', 'diana.hsu@example.com', 'Di');
INSERT INTO USERS (ROLE, USERNAME, PASSWORD, EMAIL, NICKNAME) VALUES ('ROLE_USER', 'eric', '$2a$10$V0gkpemrKV0w5w3/stC0m.CCtzkjl9nB4ktyznQh6S/vKOQYjpr3.', 'eric.tang@example.com', 'Eric');
INSERT INTO USERS (ROLE, USERNAME, PASSWORD, EMAIL, NICKNAME) VALUES ('ROLE_USER', 'fiona.lin', '$2a$10$V0gkpemrKV0w5w3/stC0m.CCtzkjl9nB4ktyznQh6S/vKOQYjpr3.', 'fiona.lin@example.com', 'Fifi');
INSERT INTO USERS (ROLE, USERNAME, PASSWORD, EMAIL, NICKNAME) VALUES ('ROLE_USER', 'george.chen', '$2a$10$V0gkpemrKV0w5w3/stC0m.CCtzkjl9nB4ktyznQh6S/vKOQYjpr3.', 'george.chen@example.com', NULL);
INSERT INTO USERS (ROLE, USERNAME, PASSWORD, EMAIL, NICKNAME) VALUES ('ROLE_USER', 'hannah.kang', '$2a$10$V0gkpemrKV0w5w3/stC0m.CCtzkjl9nB4ktyznQh6S/vKOQYjpr3.', 'hannah.kang@example.com', 'Hana');
INSERT INTO USERS (ROLE, USERNAME, PASSWORD, EMAIL, NICKNAME) VALUES ('ROLE_USER', 'ian', '$2a$10$V0gkpemrKV0w5w3/stC0m.CCtzkjl9nB4ktyznQh6S/vKOQYjpr3.', 'ian.lee@example.com', NULL);
INSERT INTO USERS (ROLE, USERNAME, PASSWORD, EMAIL, NICKNAME) VALUES ('ROLE_USER', 'julia.hsu', '$2a$10$V0gkpemrKV0w5w3/stC0m.CCtzkjl9nB4ktyznQh6S/vKOQYjpr3.', 'julia.hsu@example.com', 'Jules');
INSERT INTO USERS (ROLE, USERNAME, PASSWORD, EMAIL, NICKNAME) VALUES ('ROLE_ADMIN', 'Lavender.Kai', '$2a$10$V0gkpemrKV0w5w3/stC0m.CCtzkjl9nB4ktyznQh6S/vKOQYjpr3.', 'lavender@example.com', 'Lavender');


--  �R��Ĳ�o��
DROP TRIGGER USERS_BEFORE_INSERT;
