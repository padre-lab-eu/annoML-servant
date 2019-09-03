# annoML Servant
This repository contains the Spring Boot Server which servese the **annoML Service API** for the [annoML](https://gitlab.thomb.org/thomborg/annoml) client. 

## Requirements
- Java 8 JDK
- Apache Maven
- Running PostgresSQL

## Configuration
There are two configuration files in src/main/resources.
Use `application.yml` to specify the configuration of the annoML Service.

### JWT Authentication
Provide here the shared secret between your authentication service and the annoML service
````
authProvider:
  jwt:
    header: Authorization
    secret: {your auth secret}
````


### Prosgres Database
Provide here a database table e.g. named annoml and its user configuration.
    
    url: jdbc:postgresql://localhost:5432/annoml
    username: annoml
    password: AnnoML


## Development run
Simply run `mvn spring-boot:run

## Building
Run `mvn package`.
You'll find the compiled build in the `target/` folder then.
