--  建立觸發器，自動取得序列值，名稱為：USERS_BEFORE_INSERT
--  設定觸發器在 USERS 表新增資料前觸發
--  每筆資料都觸發
CREATE OR REPLACE TRIGGER USERS_BEFORE_INSERT
BEFORE INSERT ON USERS
FOR EACH ROW

-- :NEW 是 Oracle 觸發器關鍵字，用來指向插入的新資料列
BEGIN
    IF :NEW.ID IS NULL THEN
        SELECT USERS_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
        -- USERS_SEQ 是自定義序列名稱
        -- NEXTVAL 是序列關鍵字，取得下一個序列值
        -- INTO 是關鍵字，將序列值賦予 :NEW.ID
        -- DUAL 是 Oracle 內建單筆虛擬表名
    END IF;
END;
/


--  建立資料  密碼為 666666
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


--  刪除觸發器
DROP TRIGGER USERS_BEFORE_INSERT;
