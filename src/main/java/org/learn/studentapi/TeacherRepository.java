package org.learn.studentapi;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // We can add custom query methods
    // Spring will automatically implement these

    // Find by name
    Optional<Teacher> findByName(String name);

    // Find by subject
    List<Teacher> findBySubject(String subject);
}
