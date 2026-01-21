# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build the project
./gradlew build

# Run tests
./gradlew test

# Run a single test class
./gradlew test --tests "springshop.SomeTestClass"

# Run a single test method
./gradlew test --tests "springshop.SomeTestClass.testMethodName"

# Run the application
./gradlew bootRun

# Clean build
./gradlew clean build
```

## Technology Stack

- **Java 17** with **Spring Boot 3.5.6**
- **Spring Data JPA** with Hibernate ORM
- **H2 Database** (in-memory for development)
- **Thymeleaf** for server-side HTML templates
- **Lombok** for boilerplate reduction
- **P6Spy** for SQL query logging with bound parameters
- **JUnit 5** with Spring Boot Test

## Architecture

This is a JPA-based e-commerce web application following Spring Boot conventions:

- **Package base**: `springshop`
- **Entry point**: `SpringshopApplication.java`
- **Expected layered architecture**:
  - Controllers (`@Controller`/`@RestController`) - handle HTTP requests
  - Services (`@Service`) - business logic
  - Repositories (`@Repository` / `JpaRepository`) - data access
  - Domain entities (`@Entity`) - JPA-mapped objects

## Key Configuration

- Main config: `src/main/resources/application.properties`
- Build config: `build.gradle`
- Gradle wrapper configured (use `./gradlew` not system gradle)
