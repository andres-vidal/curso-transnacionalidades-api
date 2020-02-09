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
public class StudentServiceDeleteTest {

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

    @Test
    public void success() throws StudentDoesNotExistsException {

        assert studentService != null;

        when(studentDAO.retrieve(student.getId())).thenReturn(student);

        studentService.delete(student.getId());
    }

    @Test(expected = StudentDoesNotExistsException.class)
    public void failureStudentDoesNotExist() throws StudentDoesNotExistsException {

        assert studentService != null;

        when(studentDAO.retrieve(student.getId())).thenReturn(null);

        studentService.delete(student.getId());
    }
}