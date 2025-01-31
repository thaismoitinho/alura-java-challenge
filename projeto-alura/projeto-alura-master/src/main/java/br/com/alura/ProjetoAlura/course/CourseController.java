package br.com.alura.ProjetoAlura.course;

import br.com.alura.ProjetoAlura.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @PatchMapping("/{code}/inactive")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivateCourse(@PathVariable String code) {
        Course course = courseRepository.findByCode(code)
                .orElseThrow(() -> new CourseNotFoundException(code));

        course.setStatus(Course.Status.INACTIVE);
        course.setInactiveAt(LocalDateTime.now());
        courseRepository.save(course);
    }

}
