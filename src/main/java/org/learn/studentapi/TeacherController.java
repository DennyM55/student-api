package org.learn.studentapi;

import jakarta.validation.Valid;
import org.learn.studentapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return service.getAllTeachers();
    }

    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable Long id) {
        return service.getTeacherById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
    }

    @PostMapping
    public Teacher createTeacher(@Valid @RequestBody Teacher teacher) {
        return service.createTeacher(teacher);
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable Long id, @Valid @RequestBody Teacher teacher) {
        return service.updateTeacher(id, teacher);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        service.deleteTeacher(id);
    }
}
