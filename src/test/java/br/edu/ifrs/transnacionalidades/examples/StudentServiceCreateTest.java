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
    private StudentServiceImpl studentService;

    private Student student;

    @Before
    public void init() {

        student = new Student("Andrés Vidal", LocalDate.of(1997, 8, 6), "andres.vidal1@example.com", "password");
        student.setId(1L);
    }

    public void success() throws StudentAlreadyExistsException, StudentValidationException {

        assert studentService != null;

        when(studentDAO.retrieve(student.getEmail())).thenReturn(null);

        studentService.create(student);
    }

    @Test(expected = StudentAlreadyExistsException.class)
    public void failureStudentAlreadyExists() throws StudentAlreadyExistsException, StudentValidationException {

        assert studentService != null;

        Student otherStudent = new Student();
        otherStudent.setId(student.getId());

        when(studentDAO.retrieve(student.getEmail())).thenReturn(otherStudent);

        studentService.create(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failurePasswordMissing() throws StudentAlreadyExistsException, StudentValidationException {

        assert studentService != null;

        student.setPassword(null);
        studentService.create(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failurePasswordEmpty() throws StudentAlreadyExistsException, StudentValidationException {

        assert studentService != null;

        student.setPassword("");
        studentService.create(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failurePasswordBlank() throws StudentAlreadyExistsException, StudentValidationException {

        assert studentService != null;

        student.setPassword("  ");
        studentService.create(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failureEmailMissing() throws StudentAlreadyExistsException, StudentValidationException {

        assert studentService != null;

        student.setEmail(null);
        studentService.create(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failureEmailEmpty() throws StudentAlreadyExistsException, StudentValidationException {

        assert studentService != null;

        student.setEmail("");
        studentService.create(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failureEmailBlank() throws StudentAlreadyExistsException, StudentValidationException {

        assert studentService != null;

        student.setEmail("  ");
        studentService.create(student);
    }
}