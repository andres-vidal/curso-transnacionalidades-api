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

    public void create(Student student) throws StudentAlreadyExistsException, StudentValidationException {

        student.setId(null);
        validate(student);

        if (retrieve(student.getEmail()) != null) {

            String message = "A student with email " + student.getEmail() + " is already registered.";
            throw new StudentAlreadyExistsException(message);
        }

        studentDAO.create(student);
    }

    public void create(List<Student> students) throws StudentAlreadyExistsException, StudentValidationException {

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

    public void update(Student student)
            throws StudentAlreadyExistsException, StudentValidationException, StudentDoesNotExistsException {

        Student persistedStudent = retrieve(student.getId());

        if (persistedStudent == null) {

            String message = "There is no student registered with the id " + student.getId();
            throw new StudentDoesNotExistsException(message);
        }

        student.setPassword(persistedStudent.getPassword());
        validate(student);

        if (!student.getEmail().equals(persistedStudent.getEmail()) && retrieve(student.getEmail()) != null) {

            String message = "A student with email " + student.getEmail() + " is already registered.";
            throw new StudentAlreadyExistsException(message);
        }

        studentDAO.update(student);
    }

    public void delete(Long id) throws StudentDoesNotExistsException {

        Student student = retrieve(id);

        if (student == null) {

            String message = "There is no student registered with the id " + id;
            throw new StudentDoesNotExistsException(message);
        }

        studentDAO.delete(student);
    }
}