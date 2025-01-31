package br.com.alura.ProjetoAlura.course;

import br.com.alura.ProjetoAlura.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseController(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }


    @PostMapping("/new")
    public ResponseEntity<String> createCourse(@Valid @RequestBody NewCourseDTO newCourse) {
        if (courseRepository.existsByCode(newCourse.getCode())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Course code already exists");
        }

        Course course = new Course(
                newCourse.getName(),
                newCourse.getCode(),
                newCourse.getDescription(),
                newCourse.getInstructorEmail()
        );

        courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body("Course created successfully");
    }

    @PostMapping("/{code}/inactive")
    public ResponseEntity createCourse(@PathVariable("code") String courseCode) {
        // TODO: Implementar a Questão 2 - Inativação de Curso aqui...

        return ResponseEntity.ok().build();
    }

}
