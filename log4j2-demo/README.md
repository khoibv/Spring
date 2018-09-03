# Sample app with log4j version 2

### Features
+ Using [Routes](https://logging.apache.org/log4j/2.x/manual/appenders.html#Routes) to classify log dynamically
+ Add sample output log to DB (H2)
+ Add sample output log to queue (ActiveMQ) 


### Run
+ Start server
```
mvn spring-boot:run
```

+ Test
```
Web: http://localhost:8080
H2: http://localhost:8080/h2-console (using JDBC URL: jdbc:h2:mem:testdb, username: sa, password: <empty>)
Message queue: 
```
