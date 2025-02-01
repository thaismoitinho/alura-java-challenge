package br.com.alura.ProjetoAlura.course;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Course {

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // The name of the course

    @Column(nullable = false, unique = true, length = 10)
    private String code; // Unique course code (4-10 characters)

    private String description; // A description of what the course is about

    @Column(nullable = false, name = "instructor_name")
    private String instructorName; // Instructor's name

    @Column(nullable = false, name = "instructor_email")
    private String instructorEmail; // Instructor's email

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE; // Default status is ACTIVE

    @Column(name = "inactive_at")
    private LocalDateTime inactiveAt; // Stores the deactivation date

    @Deprecated
    public Course() {}

    public Course(String name, String code, String description, String instructorName, String instructorEmail) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.status = Status.ACTIVE;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getInactiveAt() {
        return inactiveAt;
    }

    public void setInactiveAt(LocalDateTime inactiveAt) {
        this.inactiveAt = inactiveAt;
    }

    public enum Status {
        ACTIVE,
        INACTIVE
    }
}
