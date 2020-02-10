package br.edu.ifrs.transnacionalidades.examples;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StudentResourceRetrieveTest {

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
    public void successById() {

        assert studentResource != null;

        when(studentService.retrieve(1L)).thenReturn(student);

        final Response response = studentResource.retrieve(1L);

        assertEquals(Status.OK, response.getStatusInfo());
    }

    @Test
    public void failureById() {

        assert studentResource != null;

        when(studentService.retrieve(1L)).thenReturn(null);

        final Response response = studentResource.retrieve(1L);

        assertEquals(Status.NOT_FOUND, response.getStatusInfo());
    }

}