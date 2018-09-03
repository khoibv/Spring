# spring-jta-demo
This application is example of using JTA transaction with 2 DB (MySQL and MS SQL Server)

JTA is implemented by [Atomikos](https://www.atomikos.com/Main/WebHome)


### Create database
Run `mysql.sql` in forder resources/sql to create DB in MySQL

Run `mssql.sql` in forder resources/sql to create DB in MS SQL Server


### Run
`mvn spring-boot:run`

Start application at http://localhost:8080
