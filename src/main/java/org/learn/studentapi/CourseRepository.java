package org.learn.studentapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Custom query methods
    List<Course> findByTitleContaining(String title); // Find courses with title containing text
    List<Course> findByDurationWeeksGreaterThan(Integer weeks); // Find long courses
}
