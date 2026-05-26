package org.learn.studentapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course title is required")
    @Size(min = 2, max = 150, message = "Course title must be between 2 and 150 characters")
    @Column(nullable = false)
    private String title;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 week")
    @Column(name = "duration_weeks")
    private Integer durationWeeks;

    // Many courses can be taken by Many students - MANY-TO-MANY
    @ManyToMany
    @JoinTable(
            name = "student_courses",  // Join table name
            joinColumns = @JoinColumn(name = "course_id"),  // This table's foreign key
            inverseJoinColumns = @JoinColumn(name = "student_id")  // Other table's foreign key
    )
    @JsonIgnoreProperties("courses")
    private List<Student> students = new ArrayList<>();

    // Each course has ONE teacher - Many-to-One
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonIgnoreProperties("courses")
    private Teacher teacher;

    // Constructors
    public Course() {
    }

    public Course(String title, String description, Integer durationWeeks) {
        this.title = title;
        this.description = description;
        this.durationWeeks = durationWeeks;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDurationWeeks() {
        return durationWeeks;
    }

    public void setDurationWeeks(Integer durationWeeks) {
        this.durationWeeks = durationWeeks;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    // Helper method to add student
    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        }
        if (!student.getCourses().contains(this)) {
            student.getCourses().add(this);
        }
    }
}
