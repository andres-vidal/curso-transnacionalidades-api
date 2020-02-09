package br.edu.ifrs.transnacionalidades.examples;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

@Stateless
public class StudentServiceImpl implements StudentService {

    @Inject
    private StudentDAO studentDAO;

    private void validate(Student student) throws StudentValidationException {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        if (violations.size() > 0) {

            String message = "Student validation failed. Violations are: " + violations.toString();
            throw new StudentValidationException(message, violations);
        }
    }

    public void create(Student student) throws StudentExistsException, StudentValidationException {

        if (retrieve(student.getEmail()) == null) {

            validate(student);
            studentDAO.create(student);

        } else {

            throw new StudentExistsException("A student with email " + student.getEmail() + " is already registered.");
        }
    }

    public void create(List<Student> students) throws StudentExistsException, StudentValidationException {

        for (Student student : students)
            create(student);
    }

    public Student retrieve(Long id) {

        return studentDAO.retrieve(id);
    }

    public Student retrieve(String email) {

        return studentDAO.retrieve(email);
    }

    public List<Student> retrieve() {

        return studentDAO.retrieve();
    }

    public void update(Student student) {

        studentDAO.update(student);
    }

    public void delete(Long id) {

        studentDAO.delete(id);
    }
}