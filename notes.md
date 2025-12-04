# Notes

## Spring Web App Overview
The application runs an embedded Tomcat server that by default listens on port 8080 which
routes client requests to the actual application logic and then sends responses back to clients.

By default, the web app is meant to be accessed by a browser, so it will send responses in HTML format
over http.

The runtime enviroment of a web app is roughly as follows:
- Our OS runs a JVM
- JVM runs embedded Tomcat server
- The Tomcat server routes requests to our actual application.

Inside the application, a request arrives a Spring MVC controller. The controller processes it and then
sends it to the "model layer".

There it arrives at a particular service that performs business logic. The service typically
interacts with a repository.

In a typical spring web app, the repository is a database and we can use Spring Data JPA to interact with it.
- We write our repository interfaces for Spring Data JPA and Spring Data JPA sits over JPA provider
and provides actual implementation according to our interfaces.

The response is then propagated back to the controller which sends it to Thymeleaf engine that acts
as a view layer and creates the HTML response.

### Spring Data JPA
Spring Data JPA is a high-level abstraction over JPA that sits over an acutal JPA implementation/provider
(like Hibernate). It uses the JPA implementation under the hood but provides a more convenient API
for client code that so that they don't have to deal with the JPA API directly.

## Anatomy of a Spring Web App (general Spring boot project)
Follow the maven standard directory layout.

And also adds several custom files.
- `/src/main/resources/application.properties` these are key-value pairs of configuration properties (also can be YAML version)
- `/src/test/resources/application.properties` configuration properties for unit tests (also can be YAML)
- `/src/main/resources/static` static web files served from the root url (images, javascript
- `/src/main/resources/templates` Thymeleaf templates
- `/src/main/resources/db/migration` db migration scripts

There is a concept of default package, which is `com.mycompany.myapp`. Spring boot will expect
a main class annotated with `@SpringBootApplication` in this package. This method is executed at
runtime to start the Spring boot application.

Spring Boot scans for components in the default packages and subpackages, but not outside of them!

There is package structure recommendation by Spring, but rather vague.

Commonly we organize by type/function.

## Maven wrapper
By default, there is a maven wrapper included in the project. It allows runnning maven commands without
having maven installed locally.

## SOLID principles of OOP
Date back to 1995, by Uncle Bob.

- Single responsibility principle
- Open/closed principle: Classes should be open for extension, but closed for modification.
- Liskov substitution principle: Class hierarchy should be designed so that when objects in a program are replaced
with their subtypes, the program should still behave correctly.
- Interface segregation principle: Clients should not be forced to depend on methods they do not use, i.e.
our interfaces should be small and focused, client specific.
- Dependency inversion principle: High-level modules should not depend on low-level modules. Both should depend on the same
abstractions.

DI: For example a Service depends on a PdfReader.. to apply DI, we would create an interface Reader, make PdfReader impelemnt it
and make Service depend on Reader instead.