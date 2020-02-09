package br.edu.ifrs.transnacionalidades.examples;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.persistence.ApplyScriptBefore;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class StudentResourceRetrieveIT extends StudentDeployments {

    @Test
    @InSequence(1)
    public void cleanupBefore() {
    }

    @Test
    @InSequence(2)
    @ApplyScriptBefore("scripts/insert-students.sql")
    @Cleanup(phase = TestExecutionPhase.NONE)
    public void populateDatabase() {
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

        System.out.println("\n\n\n" + response.readEntity(String.class) + "\n\n\n");
    }
}