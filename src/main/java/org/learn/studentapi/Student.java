package org.learn.studentapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotBlank: Cannot be null, empty, or whitespace only
    // message: The error message shown when validation fails
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    // @NotBlank + email format validation
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    // @NotNull: Can be blank but not null
    // @Min and @Max: Number must be in this range
    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age must be at least 1")
    @Max(value = 150, message = "Age cannot exceed 150")
    private Integer age; // Changed from int to Integer (because Integer can be null)

    // Optional field - no validation needed, but we can add pattern
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @ManyToMany(mappedBy = "students")
    @JsonIgnoreProperties({"students", "teacher"})
    private List<Course> courses = new ArrayList<>();

    // Constructors
    public Student() {}

    public Student(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.courses = new ArrayList<>();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
}
