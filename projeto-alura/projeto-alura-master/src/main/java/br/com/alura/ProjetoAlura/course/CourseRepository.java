package br.com.alura.ProjetoAlura.course;

import br.com.alura.ProjetoAlura.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCode(String code);

    // Find a course by its code
    Optional<Course> findByCode(String code);

}
