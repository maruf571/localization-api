# localization-api
Internationalization (i18n) is very common & important feature in a software. The purpose of this project 
is to separate localization's from the main applicatoin and add more fredom to maintain localization separately.
  
# Feature
  As a localization app, I tried to solve the common problems. 

- Add multiple project
- Add multiple language
- Manage translation key
- Import localization from excel file
- Export all the translation as excel 
- Public API
- Multi tenant 

## Requirements

The following software is required to run this software in dev mode
environment:

|               Prerequisite                        |                 Description              |
|---------------------------------------------------|------------------------------------------|
| Java 8                                            | Programming language                     |
| Maven                                             | Build tools                              |
| Node JS                                           | To build and run angular app             |
| MySQL                                             | Primary Database                         |
------------------------------------------------------------------------------------------------


## Quick Start
```sh

# run api
cd localization-api
mvn spring-boot:run

# build as a standalone project
cd localization
mvn clean package
java -jar localization-ui/target/localization-api-<version>.jar
```


## Test

Unit test
```sh
$ mvn test
```

Integration test
```sh
$ mvn integration-test
```

## Built With
* [Spring boot 2.0.X](https://projects.spring.io/spring-boot/) -Backed Framework
* [Maven](https://maven.apache.org/) - Dependency Management
* [Angular](https://angularjs.org/) - Frontend Framework
* [Hibernate](http://hibernate.org/) - Persistence 
* [MySQL](https://www.mysql.com/downloads/) - Database
* [Liquibase](http://www.liquibase.org/) - Database source control



## Authors
* **Maruf Hassan**

## License

