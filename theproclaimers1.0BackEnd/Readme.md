# Welcome to Team Burners Lee backend project from Hackathon 2022/08/04.
To set up this side was used Spring Boot, MySQL, JPA, Hibernate to implement Rest API as Web Application to server webpage from our project..

Was built a Restful CRUD API for a simple Web application to alocate Curstomer Information, addresses (Delivery and billing) and shop cart.

## Requirements

1. Java - 1.8.x
2. Maven - 3.x.x
3. Mysql - 5.x.x

## Steps to Setup

**1. Create Mysql database**

```SQL
CREATE DATABASE theproclaimer
```

**2. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation
Current is default mysql superuser, root, with no password.

**3. Build and run the app using maven**

```bash
$ mvn package
$ java -jar target/theproclaimer-1.0.0.jar
```

Alternatively, you can run the app without packaging it using -

```bash
$ mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /api/customer
    
    POST /api/customer
    
    GET /api/customer/{Id}
    
    PUT /api/customer/{Id}
    
    DELETE /api/customer/{Id}

You can test them using postman or any other rest client.
