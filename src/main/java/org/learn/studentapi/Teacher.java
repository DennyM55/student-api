package org.learn.studentapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Teacher name is required")
    @Size(min = 2, max = 100, message = "Teacher name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Subject is required")
    @Size(min = 2, max = 100, message = "Subject must be between 2 and 100 characters")
    @Column(nullable = false)
    private String subject;

    // One teacher can teach MANY courses
    // @OneToMany means: "This entity has many of the other entity"
    // mappedBy = "teacher" means: "The 'teacher' field in Course owns this relationship"
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("teacher")
    private List<Course> courses = new ArrayList<>();

    // Constructors
    public Teacher() {
    }

    public Teacher(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // Helper method to add a course
    public void addCourse(Course course) {
        courses.add(course);
        course.setTeacher(this); // Set the bidirectional relationship
    }
}
