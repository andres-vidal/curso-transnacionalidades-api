package br.edu.ifrs.transnacionalidades.examples;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StudentResourceDeleteTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentResourceImpl studentResource;

    @Test
    public void success() throws StudentDoesNotExistException {

        assert studentResource != null;

        Mockito.doNothing().when(studentService).delete(1L);

        final Response response = studentResource.delete(1L);

        assertEquals(Status.OK, response.getStatusInfo());
    }

    @Test
    public void failureStudentDoesNotExist() throws StudentDoesNotExistException {

        assert studentResource != null;

        Mockito.doThrow(StudentDoesNotExistException.class).when(studentService).delete(1L);

        final Response response = studentResource.delete(1L);

        assertEquals(Status.NOT_FOUND, response.getStatusInfo());
    }
}