# annoML Servant
This repository contains the Spring Boot Server which serves the **annoML Service API** for the [annoML](https://gitlab.thomb.org/thomborg/annoml) client. 

## Requirements
- Java 8 JDK
- Apache Maven
- PostgreSQL

## Configuration
Two configuration files exist in src/main/resources.

Use `application.yml` to specify the configuration of the annoML Service.

### JWT Authentication
The shared secret between your authentication service and the annoML service must be provided.
````
authProvider:
  jwt:
    header: Authorization
    secret: {your auth secret}
````


### PostgreSQL Database
A database host, the targeted database and table name must be specified here.
    
    url: jdbc:postgresql://localhost:5432/annoml
    username: annoml
    password: password


## Development server
Simply run `mvn spring-boot:run`.

## Building for production
Run `mvn package`.

You will find the compiled build in the `target/` folder.
