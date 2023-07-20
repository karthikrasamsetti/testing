# testing
# Spring Boot MockMvc Testing Example

This repository contains an example Spring Boot project that demonstrates how to write unit tests using MockMvc for testing RESTful APIs.

## Project Structure

The project follows the following structure:

testing
├── src
│ └── main
│ └── java
│ └── com
│ └── example
│ └── demo
│ └── Application.java
└── src
└── test
└── java
└── com
└── example
└── demo
└── SampleControllerTest.java

- The `src/main/java` directory contains the main application code.
- The `src/test/java` directory contains the test classes.
  
## Technologies
* Spring Boot 3.0

## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Maven 3+


To build and run the project, follow these steps:

* Clone the repository: `git clone https://github.com/karthikrasamsetti/testing.git`
* Navigate to the project directory: cd testing
* Add database "world" to mysql 
* Build the project: mvn clean install
* Run the project: mvn spring-boot:run 

-> The application will be available at http://localhost:9000.
