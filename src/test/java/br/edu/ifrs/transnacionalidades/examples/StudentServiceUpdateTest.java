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
public class StudentServiceUpdateTest {

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

    public void success() throws StudentExistsException, StudentValidationException {

        assert studentService != null;

        when(studentDAO.retrieve(student.getEmail())).thenReturn(null);

        studentService.update(student);
    }

    @Test(expected = StudentExistsException.class)
    public void failureStudentExists() throws StudentExistsException, StudentValidationException {

        assert studentService != null;

        Student otherStudent = new Student();
        otherStudent.setId(2L);

        when(studentDAO.retrieve(student.getEmail())).thenReturn(otherStudent);

        studentService.update(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failurePasswordMissing() throws StudentExistsException, StudentValidationException {

        assert studentService != null;

        student.setPassword(null);
        studentService.update(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failurePasswordEmpty() throws StudentExistsException, StudentValidationException {

        assert studentService != null;

        student.setPassword("");
        studentService.update(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failurePasswordBlank() throws StudentExistsException, StudentValidationException {

        assert studentService != null;

        student.setPassword("  ");
        studentService.update(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failureEmailMissing() throws StudentExistsException, StudentValidationException {

        assert studentService != null;

        student.setEmail(null);
        studentService.update(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failureEmailEmpty() throws StudentExistsException, StudentValidationException {

        assert studentService != null;

        student.setEmail("");
        studentService.update(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failureEmailBlank() throws StudentExistsException, StudentValidationException {

        assert studentService != null;

        student.setEmail("  ");
        studentService.update(student);
    }
}