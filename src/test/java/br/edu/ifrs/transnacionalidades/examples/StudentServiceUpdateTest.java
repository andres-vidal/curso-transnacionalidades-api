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
    private Student other;

    @Before
    public void init() {

        student = new Student("Andrés Vidal", LocalDate.of(1997, 8, 6), "andres.vidal1@example.com", "password");
        student.setId(1L);

        other = new Student("Tomás Silvestre", LocalDate.of(1996, 4, 1), "tomas.silvestre@exemplo.com", "password");
        other.setId(2L);

    }

    @Test
    public void success()
            throws StudentAlreadyExistsException, StudentValidationException, StudentDoesNotExistException {

        assert studentService != null;

        when(studentDAO.retrieve(student.getId())).thenReturn(student);

        studentService.update(student);
    }

    @Test
    public void successOnEmailUpdate()
            throws StudentAlreadyExistsException, StudentValidationException, StudentDoesNotExistException {

        assert studentService != null;

        when(studentDAO.retrieve(student.getId())).thenReturn(other);
        when(studentDAO.retrieve(student.getEmail())).thenReturn(null);

        studentService.update(student);
    }

    @Test(expected = StudentAlreadyExistsException.class)
    public void failureStudentAlreadyExists()
            throws StudentAlreadyExistsException, StudentValidationException, StudentDoesNotExistException {

        assert studentService != null;

        when(studentDAO.retrieve(student.getId())).thenReturn(other);
        when(studentDAO.retrieve(student.getEmail())).thenReturn(other);

        studentService.update(student);
    }

    @Test(expected = StudentDoesNotExistException.class)
    public void failureStudentDoesNotExist()
            throws StudentAlreadyExistsException, StudentValidationException, StudentDoesNotExistException {

        assert studentService != null;

        when(studentDAO.retrieve(student.getId())).thenReturn(null);

        studentService.update(student);
    }

    @Test
    public void sucessPasswordMissing()
            throws StudentAlreadyExistsException, StudentValidationException, StudentDoesNotExistException {

        assert studentService != null;

        when(studentDAO.retrieve(student.getEmail())).thenReturn(null);
        when(studentDAO.retrieve(student.getId())).thenReturn(other);

        student.setPassword(null);
        studentService.update(student);
    }

    @Test
    public void sucessPasswordEmpty()
            throws StudentAlreadyExistsException, StudentValidationException, StudentDoesNotExistException {

        assert studentService != null;

        when(studentDAO.retrieve(student.getEmail())).thenReturn(null);
        when(studentDAO.retrieve(student.getId())).thenReturn(other);

        student.setPassword("");
        studentService.update(student);
    }

    @Test
    public void successPasswordBlank()
            throws StudentAlreadyExistsException, StudentValidationException, StudentDoesNotExistException {

        assert studentService != null;

        when(studentDAO.retrieve(student.getEmail())).thenReturn(null);
        when(studentDAO.retrieve(student.getId())).thenReturn(other);

        student.setPassword("  ");
        studentService.update(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failureEmailMissing()
            throws StudentAlreadyExistsException, StudentValidationException, StudentDoesNotExistException {

        assert studentService != null;

        when(studentDAO.retrieve(student.getId())).thenReturn(other);

        student.setEmail(null);
        studentService.update(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failureEmailEmpty()
            throws StudentAlreadyExistsException, StudentValidationException, StudentDoesNotExistException {

        assert studentService != null;

        when(studentDAO.retrieve(student.getId())).thenReturn(other);

        student.setEmail("");
        studentService.update(student);
    }

    @Test(expected = StudentValidationException.class)
    public void failureEmailBlank()
            throws StudentAlreadyExistsException, StudentValidationException, StudentDoesNotExistException {

        assert studentService != null;

        when(studentDAO.retrieve(student.getId())).thenReturn(student);

        student.setEmail("  ");
        studentService.update(student);
    }
}