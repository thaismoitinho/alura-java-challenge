package br.com.alura.ProjetoAlura.registration;

import br.com.alura.ProjetoAlura.course.Course;
import br.com.alura.ProjetoAlura.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User student;

    @ManyToOne
    private Course course;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Deprecated
    public Registration() {}

    public Registration(User student, Course course) {
        this.student = student;
        this.course = course;
        this.registrationDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public User getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
}
