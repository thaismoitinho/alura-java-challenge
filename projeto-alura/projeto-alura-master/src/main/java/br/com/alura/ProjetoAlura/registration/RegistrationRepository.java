package br.com.alura.ProjetoAlura.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Optional<Registration> findByStudentIdAndCourseId(Long studentId, Long courseId);
    Optional<Registration> findByStudent_EmailAndCourse_Code(String studentEmail, String courseCode);

}
