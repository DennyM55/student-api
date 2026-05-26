package org.learn.studentapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service tells Spring: "This class contains business logic"
@Service
public class StudentService {

    // We need to connect to the Repository
    // @Autowired means Spring will automatically provide the StudentRepository here
    // Think of it like: "Hey Spring, please give me the StudentRepository"
    @Autowired
    private StudentRepository repository;

    // Method to get all students
    public List<Student> getAllStudents() {
        // repository.findAll() comes from JpaRepository - gets everything from the DB
        return repository.findAll();
    }

    // Method to get a single student by ID
    public Optional<Student> getStudentById(Long id) {
        // repository.findById() comes from JpaRepository
        // It returns Optional<Student> - meaning "maybe a student, maybe nothing"

        return repository.findById(id);
    }

    public Optional<Student> getStudentByEmail(String email) {
        return repository.findByEmail(email);
    }

    // Method to create a new student
    public Student createStudent(Student student) {
        // repository.save() can either create OR update
        // Since we're creating a new student (no ID yet), it will create
        return repository.save(student);
    }

    // Method to update an existing student
    public Student updateStudent(Long id, Student newStudentData) {
        // First, find the existing student
        Optional<Student> existingStudent = repository.findById(id);

        // If found, update the fields
        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            student.setName(newStudentData.getName());
            student.setEmail(newStudentData.getEmail());
            student.setAge(newStudentData.getAge());
            student.setPhoneNumber(newStudentData.getPhoneNumber());
            return repository.save(student); // Save returns the updated student
        } else {
            // If not found, return null (or we could throw an error)
            return null;
        }
    }

    // Method to delete a student
    public void deleteStudent(Long id) {
        // repository.deleteById() comes from JpaRepository
        repository.deleteById(id);
    }
}
