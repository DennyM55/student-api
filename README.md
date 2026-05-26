# Student Management System API

Backend project built with Java, Spring Boot, Spring MVC, Spring Data JPA, and H2 Database.

## Professional Summary

Developed a RESTful Student Management System API that demonstrates core backend development skills including layered architecture, CRUD API design, database persistence, dependency injection, and Spring Boot application setup. The project exposes endpoints to create, read, update, delete, and search student records by email.

## Skills Demonstrated

- Java 17 backend development
- Spring Boot application development
- REST API design with Spring MVC
- CRUD operations using HTTP methods
- Spring Data JPA repository pattern
- Entity modeling with Jakarta Persistence API
- In-memory database integration using H2
- Layered architecture with Controller, Service, Repository, and Entity classes
- Maven-based project setup and dependency management
- JSON request and response handling
- Basic error handling for missing student records
- API testing readiness with tools like Postman or curl

## Tech Stack

| Category | Technology |
| --- | --- |
| Language | Java 17 |
| Framework | Spring Boot |
| Web Layer | Spring MVC |
| Persistence | Spring Data JPA |
| Database | H2 Database |
| Build Tool | Maven |
| API Format | REST / JSON |

## Project Highlights

- Built a clean REST API for managing student records.
- Implemented a layered backend structure to separate API, business logic, and database access.
- Used `JpaRepository` to reduce boilerplate database code.
- Added a custom finder method, `findByEmail`, using Spring Data JPA naming conventions.
- Modeled student data with a JPA entity containing `id`, `name`, `email`, `age`, and `phoneNumber`.
- Configured the application as a Spring Boot service that can be run locally with Maven.

## API Endpoints

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/api/students` | Get all students |
| `GET` | `/api/students/{id}` | Get a student by ID |
| `GET` | `/api/students/email/{email}` | Get a student by email |
| `POST` | `/api/students` | Create a new student |
| `PUT` | `/api/students/{id}` | Update an existing student |
| `DELETE` | `/api/students/{id}` | Delete a student |

## Sample Request

```json
{
  "name": "Rahul",
  "email": "rahul@example.com",
  "age": 22,
  "phoneNumber": "9998887776"
}
```

## Project Structure

```text
src/main/java/org/learn/studentapi
+-- StudentApiApplication.java
+-- Student.java
+-- StudentController.java
+-- StudentService.java
+-- StudentRepository.java
```

## How to Run

Prerequisites:

- Java 17
- Maven or the included Maven wrapper

Run the application:

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

The API runs locally at:

```text
http://localhost:8080/api/students
```

## What I Learned

- How to build a Spring Boot backend application from scratch.
- How REST controllers map HTTP requests to Java methods.
- How service classes organize business logic.
- How repositories interact with a database using Spring Data JPA.
- How JPA entities map Java objects to database tables.
- How to use Maven dependencies for backend development.
- How to structure a backend project for readability and maintainability.

## Resume Bullet Points

- Built a Java 17 and Spring Boot REST API for student management with full CRUD functionality.
- Designed layered architecture using Controller, Service, Repository, and Entity classes.
- Integrated Spring Data JPA with H2 Database for persistence and rapid local development.
- Implemented custom student lookup by email using Spring Data JPA repository conventions.
- Exposed JSON-based REST endpoints for create, read, update, delete, and search operations.

## Future Improvements

- Add validation for student fields such as email, age, and phone number.
- Add global exception handling with proper HTTP status codes.
- Add unit and integration tests for controller and service layers.
- Add Swagger/OpenAPI documentation.
- Replace H2 with MySQL or PostgreSQL for production-style persistence.
