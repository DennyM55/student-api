package org.learn.studentapi;

import jakarta.validation.Valid;
import org.learn.studentapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController combines @Controller and @ResponseBody
// It tells Spring: "This class handles HTTP requests and returns data (not HTML pages)"
@RestController
// @RequestMapping sets the base URL for all endpoints in this controller
@RequestMapping("/api/students")
public class StudentController {

    // Connect to the Service layer
    @Autowired
    private StudentService service;

    // @GetMapping means this method handles GET requests
    // The path "/" means "the root of /api/students"
    // So this endpoint will be: GET /api/students
    @GetMapping
    public List<Student> getAllStudents() {
        // Call the service method and return the result
        // Spring automatically converts the List to JSON
        return service.getAllStudents();
    }

    // @GetMapping("/{id}") means this handles GET requests with an ID
    // The {id} is a variable - we'll extract it using @PathVariable
    // This endpoint will be: GET /api/students/1
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        // Call the service to find the student
        return service.getStudentById(id)
                // .orElseThrow() handles the case where student is not found
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    @GetMapping("/email/{email}")
    public Student getStudentByEmail(@PathVariable String email) {
        return service.getStudentByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with email: " + email));
    }

    // @PostMapping handles POST requests (creating new data)
    // This endpoint will be: POST /api/students
    // @RequestBody means the data comes from the request body (JSON)
    @PostMapping
    public Student createStudent(@Valid @RequestBody Student student) {
        return service.createStudent(student);
    }

    // @PutMapping handles PUT requests (updating existing data)
    // This endpoint will be: PUT /api/students/1
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @Valid @RequestBody Student student) {
        return service.updateStudent(id, student);
    }

    // @DeleteMapping handles DELETE requests
    // This endpoint will be: DELETE /api/students/1
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
    }
}
