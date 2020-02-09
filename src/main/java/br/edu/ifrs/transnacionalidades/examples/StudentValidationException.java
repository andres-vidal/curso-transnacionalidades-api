package br.edu.ifrs.transnacionalidades.examples;

import java.util.Set;

import javax.validation.ConstraintViolation;

public class StudentValidationException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Set<ConstraintViolation<Student>> violations;

    public StudentValidationException(String errorMessage, Set<ConstraintViolation<Student>> violations) {
        super(errorMessage);
        this.violations = violations;
    }

    public Set<ConstraintViolation<Student>> getViolations() {
        return violations;
    }
}