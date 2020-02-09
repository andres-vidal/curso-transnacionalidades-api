package br.edu.ifrs.transnacionalidades.examples;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
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
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.edu.ifrs.transnacionalidades.ApplicationResource;
import net.jcip.annotations.NotThreadSafe;

@RunWith(Arquillian.class)
@NotThreadSafe
public class StudentUpdateIT {

    private Student student;

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class).addClass(ApplicationResource.class)
                .addPackage(StudentResource.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }

    @Before
    public void init() {

        student = new Student("Tomás Silvestre", LocalDate.of(1996, 4, 1), "andres.vidal1@example.com", "password2");
        student.setId(2L);
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
    }

    @Test
    @RunAsClient
    @InSequence(3)
    public void success(@ArquillianResteasyResource("api/students/1") ResteasyWebTarget target) {

        assert target != null;

        final Invocation httpPut = target.request().buildPut(Entity.entity(student, MediaType.APPLICATION_JSON));
        final Response putResponse = httpPut.invoke();

        assertEquals(Status.OK, putResponse.getStatusInfo());

        final Invocation httpGet = target.request().buildGet();
        final Response getResponse = httpGet.invoke();
        final Student updated = getResponse.readEntity(Student.class);

        assertEquals(Long.valueOf(1L), updated.getId());
        assertEquals(student.getEmail(), updated.getEmail());
        assertEquals(student.getName(), updated.getName());
        assertEquals(student.getDateOfBirth(), updated.getDateOfBirth());
    }

    @Test
    @InSequence(4)
    @Cleanup(phase = TestExecutionPhase.AFTER)
    public void cleanupAfterSuccess() {
    }

    @Test
    @InSequence(5)
    @ApplyScriptBefore("scripts/insert-students.sql")
    @Cleanup(phase = TestExecutionPhase.NONE)
    public void populateDatabaseAfterSuccess() {
    }

    @Test
    @RunAsClient
    @InSequence(6)
    public void failureStudentAlreadyExists(@ArquillianResteasyResource("api/students/1") ResteasyWebTarget target) {

        assert target != null;

        final Invocation httpGet = target.request().buildGet();
        Response getResponse = httpGet.invoke();
        final Student current = getResponse.readEntity(Student.class);

        student.setEmail("tomas.silvestre@exemplo.com");

        final Invocation httpPut = target.request().buildPut(Entity.entity(student, MediaType.APPLICATION_JSON));
        final Response putResponse = httpPut.invoke();

        assertEquals(Status.CONFLICT, putResponse.getStatusInfo());

        getResponse = httpGet.invoke();
        final Student updated = getResponse.readEntity(Student.class);

        assertEquals(Long.valueOf(1L), updated.getId());
        assertEquals(current.getEmail(), updated.getEmail());
        assertEquals(current.getName(), updated.getName());
        assertEquals(current.getDateOfBirth(), updated.getDateOfBirth());
    }

    @Test
    @RunAsClient
    @InSequence(7)
    public void failureStudentDoesNotExist(@ArquillianResteasyResource("api/students/100") ResteasyWebTarget target) {

        assert target != null;

        final Invocation httpPut = target.request().buildPut(Entity.entity(student, MediaType.APPLICATION_JSON));
        final Response putResponse = httpPut.invoke();

        assertEquals(Status.NOT_FOUND, putResponse.getStatusInfo());

    }

    @Test
    @RunAsClient
    @InSequence(8)
    public void failureEmailMissing(@ArquillianResteasyResource("api/students/1") ResteasyWebTarget target) {

        assert target != null;

        final Invocation httpGet = target.request().buildGet();
        Response getResponse = httpGet.invoke();
        final Student current = getResponse.readEntity(Student.class);

        student.setEmail(null);

        final Invocation httpPut = target.request().buildPut(Entity.entity(student, MediaType.APPLICATION_JSON));
        final Response putResponse = httpPut.invoke();

        assertEquals(Status.BAD_REQUEST, putResponse.getStatusInfo());

        getResponse = httpGet.invoke();
        final Student updated = getResponse.readEntity(Student.class);

        assertEquals(Long.valueOf(1L), updated.getId());
        assertEquals(current.getEmail(), updated.getEmail());
        assertEquals(current.getName(), updated.getName());
        assertEquals(current.getDateOfBirth(), updated.getDateOfBirth());
    }

    @Test
    @RunAsClient
    @InSequence(9)
    public void failureEmailEmpty(@ArquillianResteasyResource("api/students/1") ResteasyWebTarget target) {

        assert target != null;

        final Invocation httpGet = target.request().buildGet();
        Response getResponse = httpGet.invoke();
        final Student current = getResponse.readEntity(Student.class);

        student.setEmail("");

        final Invocation httpPut = target.request().buildPut(Entity.entity(student, MediaType.APPLICATION_JSON));
        final Response putResponse = httpPut.invoke();

        assertEquals(Status.BAD_REQUEST, putResponse.getStatusInfo());

        getResponse = httpGet.invoke();
        final Student updated = getResponse.readEntity(Student.class);

        assertEquals(Long.valueOf(1L), updated.getId());
        assertEquals(current.getEmail(), updated.getEmail());
        assertEquals(current.getName(), updated.getName());
        assertEquals(current.getDateOfBirth(), updated.getDateOfBirth());
    }

    @Test
    @RunAsClient
    @InSequence(10)
    public void failureEmailBlank(@ArquillianResteasyResource("api/students/1") ResteasyWebTarget target) {

        assert target != null;

        final Invocation httpGet = target.request().buildGet();
        Response getResponse = httpGet.invoke();
        final Student current = getResponse.readEntity(Student.class);

        student.setEmail("   ");

        final Invocation httpPut = target.request().buildPut(Entity.entity(student, MediaType.APPLICATION_JSON));
        final Response putResponse = httpPut.invoke();

        assertEquals(Status.BAD_REQUEST, putResponse.getStatusInfo());

        getResponse = httpGet.invoke();
        final Student updated = getResponse.readEntity(Student.class);

        assertEquals(Long.valueOf(1L), updated.getId());
        assertEquals(current.getEmail(), updated.getEmail());
        assertEquals(current.getName(), updated.getName());
        assertEquals(current.getDateOfBirth(), updated.getDateOfBirth());
    }
}