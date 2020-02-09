package br.edu.ifrs.transnacionalidades.examples;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.persistence.ApplyScriptBefore;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.edu.ifrs.transnacionalidades.ApplicationResource;
import net.jcip.annotations.NotThreadSafe;

@RunWith(Arquillian.class)
@NotThreadSafe
public class StudentResourceRetrieveIT {

    @Inject
    private StudentDAO studentDAO;

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class).addClass(ApplicationResource.class)
                .addPackage(StudentResource.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }

    @Test
    @InSequence(1)
    @Cleanup(phase = TestExecutionPhase.AFTER)
    public void cleanupBefore() {
    }

    @Test
    @InSequence(2)
    @ApplyScriptBefore("scripts/insert-students.sql")
    @Cleanup(phase = TestExecutionPhase.NONE)
    public void populateDatabase() {

        System.out.println("\n\nDatabase populated with " + studentDAO.retrieve() + " entries.\n\n");
    }

    @Test
    @InSequence(3)
    @RunAsClient
    public void successById(@ArquillianResteasyResource("api") StudentResource studentResource) {

        assert studentResource != null;

        final Response response = studentResource.retrieve(1L);

        assertEquals(Status.OK, response.getStatusInfo());

        Student student = response.readEntity(Student.class);

        assertEquals(Long.valueOf(1L), student.getId());
        assertEquals("Andrés Vidal", student.getName());
        assertEquals("andres@example.com", student.getEmail());
        assertEquals(LocalDate.of(1997, 8, 6), student.getDateOfBirth());
        assertEquals(null, student.getPassword());
    }

    @Test
    @InSequence(4)
    @RunAsClient
    public void successAll(@ArquillianResteasyResource("api") StudentResource studentResource) {

        assert studentResource != null;

        final Response response = studentResource.retrieve();

        List<Student> students = response.readEntity(new GenericType<List<Student>>() {
        });

        assertEquals(8, students.size());
    }
}