package br.com.alura.ProjetoAlura.registration;

import br.com.alura.ProjetoAlura.course.Course;
import br.com.alura.ProjetoAlura.course.CourseRepository;
import br.com.alura.ProjetoAlura.course.NewCourseDTO;
import br.com.alura.ProjetoAlura.user.User;
import br.com.alura.ProjetoAlura.user.UserRepository;
import jakarta.validation.Valid;
import org.hibernate.mapping.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public RegistrationController(RegistrationRepository registrationRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping("/new")
    public ResponseEntity<Registration> createRegistration(@Valid @RequestBody NewRegistrationDTO newRegistration) {
        // Find student by email
        User student = userRepository.findByEmail(newRegistration.getStudentEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        // Find course by ID
        Course course = courseRepository.findByCode(newRegistration.getCourseCode())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        // Check if the student is already registered for the course
        Optional<Registration> existingRegistration = registrationRepository.findByStudent_EmailAndCourse_Code(student.getEmail(), course.getCode());
        if (existingRegistration.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Student is already enrolled in this course");
        }

        // Check if the course is active before enrollment
        if (course.getStatus().equals(Course.Status.INACTIVE)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot enroll in an inactive course");
        }

        // Create and save registration
        Registration registration = new Registration(student, course);
        registrationRepository.save(registration);

        return ResponseEntity.status(HttpStatus.CREATED).body(registration);
    }

    @GetMapping("/report")
    public ResponseEntity<List<RegistrationReportItem>> report() {
        List<RegistrationReportItem> items = new ArrayList<>();

        List<Course> courseItems = courseRepository.findAll();

        for (Course course : courseItems) {

            Long totalRegistrations = registrationRepository.countByCourse_Code(course.getCode());

            items.add(new RegistrationReportItem(
                    course.getName(),
                    course.getCode(),
                    "Charles",
                    course.getInstructorEmail(),
                    totalRegistrations
            ));
        }

        return ResponseEntity.ok(items);
    }

}
