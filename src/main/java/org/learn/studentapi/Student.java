package org.learn.studentapi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// This @Entity annotation tells Spring: "This class represents a database table"
@Entity
public class Student {

    // @Id means this field is the primary key (unique identifier for each student)
    @Id
    // @GeneratedValue means the database will automatically generate IDs (1, 2, 3, etc.)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // These are regular fields - no special annotations needed
    private String name;
    private String email;
    private int age;
    private String phoneNumber;

    // This is a CONSTRUCTOR - a special method to create Student objects
    // Don't worry too much about this yet
    public Student() {
        // This empty constructor is required by JPA (the database system)
    }

    // This constructor lets us create a student with all fields at once
    public Student(String name, String email, int age, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // GETTERS AND SETTERS
    // These are methods that allow us to read and modify the private fields
    // You can generate these automatically in IntelliJ, but I'll show you the code

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
