package br.edu.ifrs.transnacionalidades;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.edu.ifrs.transnacionalidades.examples.Student;
import br.edu.ifrs.transnacionalidades.examples.StudentExistsException;
import br.edu.ifrs.transnacionalidades.examples.StudentService;
import br.edu.ifrs.transnacionalidades.examples.StudentValidationException;

@WebListener
public class DataInitializer implements ServletContextListener {

    @Inject
    private StudentService studentService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        List<Student> students = List.of(
                new Student("Andres Vidal", LocalDate.of(1997, 8, 6), "andres@example.com", "password"),
                new Student("António Santos", LocalDate.of(1995, 4, 1), "antonio.santos@exemplo.com", "password"),
                new Student("Cassandra Silva", LocalDate.of(1995, 4, 1), "cassandra.silva@exemplo.com", "password"),
                new Student("Félix Cipriano", LocalDate.of(1995, 4, 1), "felix.cipriano@exemplo.com", "password"),
                new Student("Marco Díaz", LocalDate.of(1995, 4, 1), "marco.diaz@exemplo.com", "password"),
                new Student("Mateus Rebocho", LocalDate.of(1995, 4, 1), "mateus.rebocho@exemplo.com", "password"),
                new Student("Nicanor Guterres", LocalDate.of(1995, 4, 1), "nicanor.guterres@exemplo.com", "password"),
                new Student("Tomás Silvestre", LocalDate.of(1995, 4, 1), "tomas.silvestre@exemplo.com", "password"));

        try {

            studentService.create(students);

        } catch (StudentExistsException | StudentValidationException e) {

            e.printStackTrace();
        }
    }

}
