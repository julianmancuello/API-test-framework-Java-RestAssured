# API Test Framework: Java, REST Assured and JUnit 5

## Description

This project is an API test automation framework developed in Java, using REST Assured and JUnit 5. It allows for comprehensive and efficient testing of RESTful web services, supporting serialization, deserialization, and dynamic data handling.

## Features

- **Modular design:** Clear separation of responsibilities using packages.

- **Data sharing:** Context management for sharing variables across tests.

- **Serialization and deserialization:** POJO-based handling of API responses.

- **API client abstraction:** Centralized methods for API operations.

- **Dependency management:** Maven for easy integration of libraries.

## Technologies Used

- **Language:** Java 20

- **API Testing Framework:** REST Assured

- **Test Framework:** JUnit 5

- **Dependency Manager:** Maven

## Project Structure
```bash
|-- src
    |-- main
        |-- java
            |-- clients
            |-- common
            |-- context
            |-- models
        |-- resources
    |-- test
        |-- java
            |-- data
            |-- setup
            |-- tests
        |-- resources
```
- **main/java/clients:** Contains classes that abstract API operations, such as methods for retrieving and manipulating resources (e.g., getUser() in user APIs).

- **main/java/common:** Utility classes shared across tests.

- **main/java/context:** Class for sharing variables and data between tests, which allows storing, retrieving, and managing key-value pairs during test execution.

- **main/java/models:** POJOs for serialization and deserialization of API requests and responses.

- **test/java/data:** Test data providers and management.

- **test/java/setup:** Pre- and post-condition handlers for test scenarios and configuration handling.

- **test/java/tests:** Automated API test cases.

- **resources:** Configuration files required for test execution.

## Setup

1. Clone the repository:
```bash
git clone https://github.com/julianmancuello/API-test-framework-Java-RestAssured.git
```
2. Import the project into IntelliJ IDEA or any compatible IDE.
3. Ensure Java 20 is configured in the ```PATH```.
4. Run:
```bash
mvn clean test
```

## Test Execution

- Run all tests:
```bash
mvn test
```
- Run smoke tests:
```bash
mvn test -Dgroups=smoke
```
- Run regression tests:
```bash
mvn test -Dgroups=regression
```

## Author
**Julian Ezequiel Mancuello**







