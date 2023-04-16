# quarkus-panache-sandbox

Simple Quarkus application which provides the possibility to work with the database.
It contains three modules:

artist - module with POJO class Artist, and DAO class ArtistRepository, which uses simple PrepareStatement to perform CRUD operation with Artist POJO.
The module contains MySQL dependency just for fast testing via Docker

customer - module with JPA entity Customer and corresponding CustomerRepository. The module contains MariaDB dependency just for fast testing via Docker

vintage-store - module with main application logic. Contains all necessary Panache entities to store information about books, CDs and tracks, publishers, and orders. Also, it contains Panache repositories for work with Artist POJO and Customer JPA entity.
For CRUD operation module uses an active record method for all Panache entities, and a repository method for non-Panache entities.
The module provides resource classes for JSON data transfer - to get all entities from the database and store new records. You can find the list of all available endpoint and JSON formats via swagger - just follow instruction to start the application in development mode and navigate to ```localhost:8080/q/swagger-ui/``` in your browser.
Also application contains web interface for the data representation, just navigate to ```http://localhost:8080/page/items/books``` in your browser.

Application can be run in three mode - development, testing and production
## Running the application in testing mode
You can execute Quarkus tests for several Panache entities.
Navigate to ```./vintage-store``` module and perform:
```
mvn test
```
> **_NOTE:_**  Quarkus will use H2 in-memory database for create all needful tables 

## Running the application in dev mode

You can run your application in dev mode that enables live coding using.
Navigate to root application folder and perform:
```
mvn clean install
```
Navigate to ```./vintage-store``` module and perform:
```
mvn quarkus:dev
```

> **_NOTE:_**  Quarkus will create database container with postgresql DB and container for the application


## Creating a native executable and start in production mode

You can create a native executable using.
Navigate to root application folder and perform:
```
mvn clean install
```
Navigate to ```./vintage-store``` module and perform:
```
mvn package -DskipTests -Dquarkus.package.type=native
```
Start docker image with database:
```
docker-compose up -d
```
Navigate to ```./vintage-store/targer``` and execute ```vintage-store-1.0.0-runner.exe```
