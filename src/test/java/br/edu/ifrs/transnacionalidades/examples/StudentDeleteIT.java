package br.edu.ifrs.transnacionalidades.examples;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
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
public class StudentDeleteIT {

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
    public void success(@ArquillianResteasyResource("api") StudentResource studentResource) {

        assert studentResource != null;

        final Response response = studentResource.delete(1L);

        assertEquals(Status.OK, response.getStatusInfo());
    }

    @Test
    @InSequence(4)
    @RunAsClient
    public void failureStudentDoesNotExist(@ArquillianResteasyResource("api") StudentResource studentResource) {

        assert studentResource != null;

        final Response response = studentResource.delete(100L);

        assertEquals(Status.NOT_FOUND, response.getStatusInfo());
    }
}