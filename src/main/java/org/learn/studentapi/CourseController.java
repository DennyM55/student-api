package org.learn.studentapi;

import jakarta.validation.Valid;
import org.learn.studentapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping
    public List<Course> getAllCourses() {
        return service.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Long id) {
        return service.getCourseById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    @PostMapping
    public Course createCourse(@Valid @RequestBody Course course,
                               @RequestParam Long teacherId) {
        return service.createCourse(course, teacherId);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @Valid @RequestBody Course course) {
        return service.updateCourse(id, course);
    }

    @PutMapping("/{courseId}/teacher/{teacherId}")
    public Course assignTeacher(@PathVariable Long courseId,
                                @PathVariable Long teacherId) {
        return service.assignTeacher(courseId, teacherId);
    }

    // Endpoint to enroll a student
    @PostMapping("/{courseId}/enroll/{studentId}")
    public Course enrollStudent(@PathVariable Long courseId,
                                @PathVariable Long studentId) {
        return service.enrollStudent(courseId, studentId);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        service.deleteCourse(id);
    }
}
