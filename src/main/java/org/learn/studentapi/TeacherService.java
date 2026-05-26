package org.learn.studentapi;


import org.learn.studentapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository repository;

    public List<Teacher> getAllTeachers() {
        return repository.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return repository.findById(id);
    }

    public Teacher createTeacher(Teacher teacher) {
        return repository.save(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher newTeacherData) {
        Optional<Teacher> existingTeacher = repository.findById(id);

        if (existingTeacher.isPresent()) {
            Teacher teacher = existingTeacher.get();
            teacher.setName(newTeacherData.getName());
            teacher.setSubject(newTeacherData.getSubject());
            return repository.save(teacher);
        }

        throw new ResourceNotFoundException("Teacher not found with id: " + id);
    }

    public void deleteTeacher(Long id) {
        repository.deleteById(id);
    }

    public Optional<Teacher> findByName(String name) {
        return repository.findByName(name);
    }
}
