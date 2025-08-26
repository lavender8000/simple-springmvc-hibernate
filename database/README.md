# Oracle 10g Express Edition - 本機設定說明

該實作應用中的 Database 配置

---

## Oracle 版本

Oracle Database 10g Express Edition Release 10.2.0.1.0 - Production

---

## 1. 修改 SQL*Plus HTTP Port

1. 使用 cmd 登入 Oracle：
```bash
sqlplus / as sysdba
```

2. 執行下列指令修改 HTTP Port：
```sql
exec dbms_xdb.sethttpport(60001);
```

---

## 2. 修改 Oracle 安裝目錄下的捷徑（可選）

如果沒有使用 Oracle 提供的網頁功能，可以略過此步驟。

| 捷徑名稱 | 連結範例 |
| ------------ | ------------ |
| Database\_homepage | http://127.0.0.1:60001/apex |
| Online\_help       | http://127.0.0.1:60001/apex/wwv\_flow\_help.show\_help?p\_flow\_id=4500\&p\_step\_id=1000 |

---

## 3. 建立使用者

1. 建立新使用者：
```sql
CREATE USER DBO IDENTIFIED BY "123456";
```

2. 賦予 DBA 權限：
```sql
GRANT DBA TO DBO;
```

---

## 4. 資料庫初始化

在完成使用者建立與權限設定後，接下來需要依照專案提供的 SQL 檔案來創建表格並匯入初始資料。

### 4.1 檔案列表

- `USER_ddl.sql`：資料表與結構定義（DROP TABLE、CREATE TABLE、CREATE SEQUENCE 等 DDL 指令）
- `USER_dml.sql`：初始資料匯入（INSERT 指令）

### 4.2 執行方式

在 .sql 檔案目錄下執行 cmd 後，執行相關命令匯入資料

1. 使用 cmd 登入 Oracle：
```bash
sqlplus DBO/123456
```

2. 執行 ddl 建立資料表與序列：
```sql
@USER_ddl.sql
```

3. 匯入初始資料：
```sql
@USER_dml.sql
```

### 4.3 驗證資料

1. 先設定顯示格式
```sql
COLUMN id FORMAT 999
COLUMN role FORMAT A10
COLUMN username FORMAT A20
COLUMN password FORMAT A60
COLUMN email FORMAT A30
COLUMN nickname FORMAT A20
SET LINESIZE 200;
```

2. 執行簡單查詢確認表格與資料
```sql
SELECT * FROM USERS;
```