package br.edu.ifrs.transnacionalidades.examples;

import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceCreateTest {

    @Mock
    private StudentDAO studentDAO;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @Before
    public void init() {

        student = new Student("Andrés Vidal", LocalDate.of(1997, 8, 6), "andres.vidal1@example.com", "password");
    }

    public void success() throws StudentExistsException, StudentValidationException {

        assert studentService != null;

        when(studentDAO.retrieve(student.getEmail())).thenReturn(null);

        studentService.create(student);
    }

    @Test(expected = StudentExistsException.class)
    public void failureStudentExists() throws StudentExistsException, StudentValidationException {

        assert studentService != null;

        when(studentDAO.retrieve(student.getEmail())).thenReturn(new Student());

        studentService.create(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failurePasswordMissing() throws StudentExistsException, StudentValidationException {

        assert studentService != null;

        student.setPassword(null);
        studentService.create(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failurePasswordEmpty() throws StudentExistsException, StudentValidationException {

        assert studentService != null;

        student.setPassword("");
        studentService.create(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failurePasswordBlank() throws StudentExistsException, StudentValidationException {

        assert studentService != null;

        student.setPassword("  ");
        studentService.create(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failureEmailMissing() throws StudentExistsException, StudentValidationException {

        assert studentService != null;

        student.setEmail(null);
        studentService.create(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failureEmailEmpty() throws StudentExistsException, StudentValidationException {

        assert studentService != null;

        student.setEmail("");
        studentService.create(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failureEmailBlank() throws StudentExistsException, StudentValidationException {

        assert studentService != null;

        student.setEmail("  ");
        studentService.create(student);
    }
}