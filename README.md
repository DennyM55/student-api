# Student Management System API

Backend project built with Java, Spring Boot, Spring MVC, Spring Data JPA, and MySQL.

## Professional Summary

Developed a RESTful Student Management System API that demonstrates core backend development skills including layered architecture, CRUD API design, database persistence, dependency injection, and Spring Boot application setup. The project exposes endpoints to manage students, teachers, courses, teacher-course assignment, and student-course enrollment.

## Skills Demonstrated

- Java 17 backend development
- Spring Boot application development
- REST API design with Spring MVC
- CRUD operations using HTTP methods
- Spring Data JPA repository pattern
- Entity modeling with Jakarta Persistence API
- MySQL database integration
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
| Database | MySQL |
| Build Tool | Maven |
| API Format | REST / JSON |

## Project Highlights

- Built a clean REST API for managing student, teacher, and course records.
- Implemented a layered backend structure to separate API, business logic, and database access.
- Used `JpaRepository` to reduce boilerplate database code.
- Added a custom finder method, `findByEmail`, using Spring Data JPA naming conventions.
- Modeled student, teacher, and course data with JPA relationships.
- Added a one-to-many relationship from teacher to courses.
- Added a many-to-one relationship from course to teacher.
- Added a many-to-many relationship between students and courses.
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
| `GET` | `/api/teachers` | Get all teachers |
| `GET` | `/api/teachers/{id}` | Get a teacher by ID |
| `POST` | `/api/teachers` | Create a new teacher |
| `PUT` | `/api/teachers/{id}` | Update an existing teacher |
| `DELETE` | `/api/teachers/{id}` | Delete a teacher |
| `GET` | `/api/courses` | Get all courses |
| `GET` | `/api/courses/{id}` | Get a course by ID |
| `POST` | `/api/courses?teacherId={teacherId}` | Create a course for a teacher |
| `PUT` | `/api/courses/{id}` | Update an existing course |
| `PUT` | `/api/courses/{courseId}/teacher/{teacherId}` | Assign a teacher to a course |
| `POST` | `/api/courses/{courseId}/enroll/{studentId}` | Enroll a student in a course |
| `DELETE` | `/api/courses/{id}` | Delete a course |

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
+-- Teacher.java
+-- TeacherController.java
+-- TeacherService.java
+-- TeacherRepository.java
+-- Course.java
+-- CourseController.java
+-- CourseService.java
+-- CourseRepository.java
+-- exception/
    +-- GlobalExceptionHandler.java
    +-- ResourceNotFoundException.java
```

## How to Run

Prerequisites:

- Java 17
- Maven or the included Maven wrapper
- MySQL running locally

Default database settings:

```properties
MYSQL_URL=jdbc:mysql://localhost:3306/student_management?createDatabaseIfNotExist=true
MYSQL_USERNAME=root
MYSQL_PASSWORD=password
```

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
- Integrated Spring Data JPA with MySQL for relational persistence.
- Implemented custom student lookup by email using Spring Data JPA repository conventions.
- Exposed JSON-based REST endpoints for create, read, update, delete, and search operations.

## Future Improvements

- Add unit and integration tests for controller and service layers.
- Add Swagger/OpenAPI documentation.
