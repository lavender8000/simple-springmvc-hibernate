# 簡單會員系統練習

使用 Java Spring MVC + JSP，實作註冊、登入、個人資料編輯、搜尋、密碼重置（郵件）。

### 檔案列表

- `javaweb`：該專案的 Java 目錄
- `database`：該專案的 Oracle 相關配置目錄



## 專案功能

- 會員註冊、登入、登出
- 個人資料查看與編輯
- 忘記密碼，透過 Email 重置
- 使用者搜尋（Admin 專用）



## 技術棧

- **後端**: Java, Spring MVC, Spring Security, Hibernate
- **資料庫**: Oracle
- **前端**: JSP + jQuery



## 安裝與執行

1. **資料庫設定**
   - 建立 Oracle 資料庫
   - 資料庫設定與建立使用者
   - 匯入 `ddl`、`dml` SQL 檔案

2. **SMTP 設定**
   - 配置 SMTP 的 Username、Password（已標記 TODO）

3. **部屬**
   - 使用 Maven 的 package 打包成 war 檔案
   - 將 war 檔案部屬於 Tomcat 的 webapps 目錄中

4. **測試**
   - 啟動 Tomcat 服務
   - 打開瀏覽器，並輸入下網址：
   ```
   http://localhost:8080/simple-springmvc-hibernate-1.0/
   ```