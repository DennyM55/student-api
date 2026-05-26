package org.learn.studentapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// @Repository is another annotation
// It tells Spring: "This is a special class that handles database operations"
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Notice this is an INTERFACE, not a class
    // We don't write method implementations here
    // Spring Data JPA automatically creates the methods for us

    // JpaRepository already provides methods like:
    // - save(student) - save or update
    // - findById(id) - find by ID
    // - findAll() - get all records
    // - deleteById(id) - delete by ID
    // We don't need to write these - they're already there!

    Optional<Student> findByEmail(String email);
}
