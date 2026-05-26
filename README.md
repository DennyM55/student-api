# Student Management System API

Backend project built with Java, Spring Boot, Spring MVC, Spring Data JPA, and MySQL.

## Professional Summary

Developed a RESTful Student Management System API that demonstrates core backend development skills including layered architecture, CRUD API design, database persistence, dependency injection, and Spring Boot application setup. The project exposes endpoints to manage students, teachers, courses, teacher-course assignment, and student-course enrollment.

## Skills Demonstrated

- Java 17 backend application development
- Spring Boot application setup and configuration
- REST API design using Spring MVC controllers
- CRUD endpoint implementation using `GET`, `POST`, `PUT`, and `DELETE`
- Layered backend architecture using Controller, Service, Repository, and Entity classes
- Dependency injection with Spring-managed components
- JSON request and response handling for REST APIs
- Request body validation with Jakarta Validation annotations
- Centralized exception handling with custom error responses
- Custom domain exceptions for missing resources
- Spring Data JPA repository pattern with `JpaRepository`
- Custom Spring Data JPA query methods such as `findByEmail` and `findByTitleContaining`
- Entity modeling with Jakarta Persistence API annotations
- Relational database design for students, teachers, courses, and enrollments
- One-to-many relationship mapping between teachers and courses
- Many-to-one relationship mapping between courses and teachers
- Many-to-many relationship mapping between students and courses
- Join table design and persistence using `student_courses`
- Bidirectional relationship management between JPA entities
- MySQL database integration with Hibernate ORM
- Database schema generation and updates using Hibernate `ddl-auto`
- Maven project structure, dependency management, and build lifecycle
- Integration testing with Spring Boot test support and MockMvc
- End-to-end API flow testing for create, search, assign, and enroll operations
- Direct database verification of persisted relationship data with `JdbcTemplate`
- Local API testing readiness with curl or Postman
- Git-based workflow with commits and pushes to GitHub

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
- Added a custom course search method, `findByTitleContaining`, for keyword-based title lookup.
- Modeled student, teacher, and course data with JPA relationships.
- Added a one-to-many relationship from teacher to courses.
- Added a many-to-one relationship from course to teacher.
- Added a many-to-many relationship between students and courses.
- Added an enrollment endpoint that stores student-course relationships in the `student_courses` join table.
- Added an integration test that creates a student, creates a course, enrolls the student, and verifies the join table row.
- Added integration tests for custom repository query endpoints.
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
| `GET` | `/api/courses/search?keyword={keyword}` | Search courses by title keyword |
| `POST` | `/api/courses?teacherId={teacherId}` | Create a course for a teacher |
| `PUT` | `/api/courses/{id}` | Update an existing course |
| `PUT` | `/api/courses/{courseId}/teacher/{teacherId}` | Assign a teacher to a course |
| `POST` | `/api/courses/{courseId}/enroll/{studentId}` | Enroll a student in a course |
| `DELETE` | `/api/courses/{id}` | Delete a course |

## Sample Student Request

```json
{
  "name": "Rahul",
  "email": "rahul@example.com",
  "age": 22,
  "phoneNumber": "9998887776"
}
```

## Enrollment Flow Example

Create a teacher:

```bash
curl -X POST http://localhost:8080/api/teachers \
  -H "Content-Type: application/json" \
  -d '{"name":"Anita Sharma","subject":"Computer Science"}'
```

Create a student:

```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"name":"Rahul","email":"rahul@example.com","age":22,"phoneNumber":"9998887776"}'
```

Create a course for the teacher:

```bash
curl -X POST "http://localhost:8080/api/courses?teacherId=1" \
  -H "Content-Type: application/json" \
  -d '{"title":"Spring Boot Basics","description":"Introductory Spring Boot course","durationWeeks":8}'
```

Enroll the student in the course:

```bash
curl -X POST http://localhost:8080/api/courses/1/enroll/1
```

The enrollment is persisted in the `student_courses` join table using `course_id` and `student_id`.

## Custom Query Examples

Find a student by email:

```bash
curl http://localhost:8080/api/students/email/rahul@example.com
```

Search courses by title keyword:

```bash
curl "http://localhost:8080/api/courses/search?keyword=Spring"
```

These endpoints use Spring Data JPA repository query methods:

```java
Optional<Student> findByEmail(String email);
List<Course> findByTitleContaining(String title);
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
src/test/java/org/learn/studentapi
+-- StudentApiApplicationTests.java
+-- CourseEnrollmentIntegrationTests.java
+-- RepositoryQueryEndpointIntegrationTests.java
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

Run tests:

```bash
./mvnw test
```

On Windows:

```powershell
.\mvnw.cmd test
```

## What I Learned

- How to build a Spring Boot backend application from scratch.
- How REST controllers map HTTP requests to Java methods.
- How service classes organize business logic.
- How repositories interact with a database using Spring Data JPA.
- How to create custom Spring Data JPA query methods from method names.
- How JPA entities map Java objects to database tables.
- How to model and verify many-to-many relationships using a join table.
- How to write an integration test for an end-to-end REST API flow.
- How to use Maven dependencies for backend development.
- How to structure a backend project for readability and maintainability.

## Resume Bullet Points

- Built a Java 17 and Spring Boot REST API for student management with full CRUD functionality.
- Designed layered architecture using Controller, Service, Repository, and Entity classes.
- Integrated Spring Data JPA with MySQL for relational persistence.
- Modeled one-to-many, many-to-one, and many-to-many relationships between teachers, courses, and students.
- Implemented course enrollment using a REST endpoint and a `student_courses` join table.
- Implemented custom student lookup by email using Spring Data JPA repository conventions.
- Implemented course search by title keyword using a custom Spring Data JPA repository method.
- Exposed JSON-based REST endpoints for create, read, update, delete, and search operations.

## Future Improvements

- Add more unit and integration tests for controller and service edge cases.
- Add Swagger/OpenAPI documentation.
