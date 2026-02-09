# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Simple member registration and product ordering CRUD application built with Spring Boot. The project focuses on **Docker-based deployment**, **cloud infrastructure**, and **CI/CD automation** (GitHub Actions planned).

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

# Run the application locally
./gradlew bootRun

# Run with Docker Compose (Oracle DB + Spring Boot)
docker-compose up -d

# Clean build
./gradlew clean build
```

## Technology Stack

- **Java 17** with **Spring Boot 3.5.6**
- **MyBatis 3.0.4** for SQL mapping (XML-based mappers)
- **Oracle Database** (production) via `ojdbc11` driver
- **H2 Database** (test environment only, Oracle compatibility mode)
- **Thymeleaf** for server-side HTML templates
- **Lombok** for boilerplate reduction
- **P6Spy** for SQL query logging with bound parameters
- **Docker Compose** for multi-container orchestration
- **JUnit 5** with Spring Boot Test

## Architecture

This is a **MyBatis-based** e-commerce web application following traditional Spring MVC layered architecture:

### Layer Structure
- **Controllers** (`springshop.controller.*`) - Handle HTTP requests, return Thymeleaf views
- **Services** (`springshop.service.*` + `impl` package) - Business logic layer
- **Mappers** (`springshop.mapper.*`) - MyBatis interfaces for database access
- **Models** (`springshop.model.*`) - Domain entities (not JPA entities, plain POJOs)
- **DTOs** (`springshop.dto.*`) - Form objects for validation and data transfer
- **Exceptions** (`springshop.exception.*`) - Custom exception hierarchy by domain

### Key Domain Models
- **Member** - Customer with embedded `Address`
- **Item** - Product with `ItemType` enum (BOOK, MOVIE, ALBUM)
- **Order** - Order aggregate linking Member and Delivery
- **OrderItem** - Line items within an Order
- **Delivery** - Delivery information with `DeliveryStatus` enum

### MyBatis Mapper Files
XML mapper files located in `src/main/resources/mapper/`:
- `MemberMapper.xml` - Member CRUD operations
- `ItemMapper.xml` - Item CRUD operations
- `OrderMapper.xml` - Order CRUD operations

All use Oracle database sequences for ID generation.

### AOP (Planned)
- `springshop.aop.AuditLogAspect` - Empty placeholder for cross-cutting audit logging

## Configuration & Profiles

### Local Development
- Config: `src/main/resources/application.yml`
- Database: Oracle DB on `localhost:1521/FREEPDB1`
- Port: `8090`
- Logging: `springshop.mapper` at DEBUG level

### Docker Environment
- Profile: `docker` (activated via `SPRING_PROFILES_ACTIVE=docker`)
- Config: `src/main/resources/application-docker.yml`
- Database: `oracle-db` container via Docker network
- Connection pooling configured (HikariCP: max 10, min idle 5)
- Logging: INFO level (production-ready)

### Test Environment
- Config: `src/test/resources/application.yml`
- Database: H2 in-memory (`MODE=Oracle`) for Oracle SQL compatibility
- No P6Spy in tests (uses native H2 driver)

## Docker Compose Setup

Multi-container architecture defined in `docker-compose.yml`:

```
┌─────────────────────────────────────────┐
│           Docker Compose                │
│                                         │
│  ┌────────────┐      ┌──────────────┐  │
│  │ springshop-│      │ springshop-  │  │
│  │    app     │◄────►│   oracle     │  │
│  │ (Port 8090)│      │ (Port 1521)  │  │
│  └────────────┘      └──────────────┘  │
│         ▲                    ▲          │
│         └─ springshop-network ─┘        │
└─────────────────────────────────────────┘
```

- **Oracle DB**: `gvenzl/oracle-free:latest` with healthcheck
- **Spring Boot App**: Built from `./Dockerfile`, waits for DB health
- **Network**: Bridge network `springshop-network`
- **Volumes**: Persistent Oracle data in `oracle-data` volume

## Important Notes

- **This is NOT a JPA project** - Uses MyBatis for all database operations
- **Oracle-specific SQL** - Mapper XML files use Oracle syntax (sequences, SYSDATE, etc.)
- **Test uses H2 with Oracle mode** - Test schema must be Oracle-compatible
- **Lombok** - Models use `@NoArgsConstructor` for MyBatis reflection
- **P6Spy wrapper** - JDBC URL is `jdbc:p6spy:oracle:thin:@...` for query logging
