package br.com.alura.ProjetoAlura.course;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String code) {
        super("Course with code " + code + " not found!");
    }
}
