package br.edu.ifrs.transnacionalidades.examples;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StudentResourceCreateTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentResourceImpl studentResource;

    private Student student;

    @Before
    public void init() {

        student = new Student("Andrés Vidal", LocalDate.of(1997, 8, 6), "andres.vidal1@example.com", "password");
        student.setId(1L);
    }

    @Test
    public void success() throws StudentAlreadyExistsException, StudentValidationException {

        assert studentResource != null;

        Mockito.doNothing().when(studentService).create(student);

        final Response response = studentResource.create(student, student.getPassword());

        assertEquals(Status.CREATED, response.getStatusInfo());
    }

    @Test
    public void failureStudentAlreadyExists() throws StudentAlreadyExistsException, StudentValidationException {

        assert studentResource != null;

        Mockito.doThrow(StudentAlreadyExistsException.class).when(studentService).create(student);

        final Response response = studentResource.create(student, student.getPassword());

        assertEquals(Status.CONFLICT, response.getStatusInfo());
    }

    @Test
    public void failureStudentValidation() throws StudentAlreadyExistsException, StudentValidationException {

        assert studentResource != null;

        Mockito.doThrow(StudentValidationException.class).when(studentService).create(student);

        final Response response = studentResource.create(student, student.getPassword());

        assertEquals(Status.BAD_REQUEST, response.getStatusInfo());
    }

    @Test
    public void failureMissingPassword() {

        assert studentResource != null;

        final Response response = studentResource.create(student, null);

        assertEquals(Status.BAD_REQUEST, response.getStatusInfo());
    }

    @Test
    public void failureMissingStudent() {

        assert studentResource != null;

        final Response response = studentResource.create(null, "password");

        assertEquals(Status.BAD_REQUEST, response.getStatusInfo());
    }
}