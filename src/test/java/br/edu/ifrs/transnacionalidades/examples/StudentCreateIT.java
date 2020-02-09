package br.edu.ifrs.transnacionalidades.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
public class StudentCreateIT {

    private Student student;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class).addClass(ApplicationResource.class)
                .addPackage(StudentResource.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }

    @Before
    public void init() {

        student = new Student("Andrés Vidal", LocalDate.of(1997, 8, 6), "andres.vidal1@example.com", "password");
    }

    @Test
    @InSequence(1)
    @Cleanup(phase = TestExecutionPhase.AFTER)
    public void cleanupBefore() {
    }

    @Test
    @RunAsClient
    @InSequence(2)
    public void success(@ArquillianResteasyResource("api/students") ResteasyWebTarget target) {

        assert target != null;

        final Invocation.Builder builder = target.request();
        builder.accept(MediaType.APPLICATION_JSON);
        builder.header("student-password", student.getPassword());
        final Invocation invocation = builder.buildPost(Entity.entity(student, MediaType.APPLICATION_JSON));

        final Response response = invocation.invoke();

        assertEquals(Status.CREATED, response.getStatusInfo());

        Student created = response.readEntity(Student.class);

        assertTrue(created.getId() != null);
        assertEquals(student.getName(), created.getName());
        assertEquals(student.getDateOfBirth(), created.getDateOfBirth());
        assertEquals(student.getEmail(), created.getEmail());
        assertEquals(student.isAdmin(), created.isAdmin());
        assertEquals(null, created.getPassword());
    }

    @Test
    @InSequence(3)
    @Cleanup(phase = TestExecutionPhase.AFTER)
    public void cleanAfterSuccess() {
    }

    @Test
    @RunAsClient
    @InSequence(4)
    public void failureStudentExists(@ArquillianResteasyResource("api/students") ResteasyWebTarget target) {

        assert target != null;

        final Invocation.Builder builder = target.request();
        builder.accept(MediaType.APPLICATION_JSON);
        builder.header("student-password", student.getPassword());
        final Invocation invocation = builder.buildPost(Entity.entity(student, MediaType.APPLICATION_JSON));

        invocation.invoke();

        final Response response = invocation.invoke();

        assertEquals(Status.CONFLICT, response.getStatusInfo());

    }

    @Test
    @InSequence(5)
    @Cleanup(phase = TestExecutionPhase.AFTER)
    public void cleanAfterFailureStudentExists() {
    }

    @Test
    @RunAsClient
    @InSequence(6)
    public void failurePasswordMissing(@ArquillianResteasyResource("api/students") ResteasyWebTarget target) {

        assert target != null;

        final Invocation.Builder builder = target.request();
        builder.accept(MediaType.APPLICATION_JSON);
        final Invocation invocation = builder.buildPost(Entity.entity(student, MediaType.APPLICATION_JSON));

        final Response response = invocation.invoke();

        assertEquals(Status.BAD_REQUEST, response.getStatusInfo());

    }

    @Test
    @RunAsClient
    @InSequence(7)
    public void failurePasswordEmpty(@ArquillianResteasyResource("api/students") ResteasyWebTarget target) {

        assert target != null;

        final Invocation.Builder builder = target.request();
        builder.accept(MediaType.APPLICATION_JSON);
        builder.header("student-password", "");
        final Invocation invocation = builder.buildPost(Entity.entity(student, MediaType.APPLICATION_JSON));

        final Response response = invocation.invoke();

        assertEquals(Status.BAD_REQUEST, response.getStatusInfo());

    }

    @Test
    @RunAsClient
    @InSequence(8)
    public void failurePasswordBlank(@ArquillianResteasyResource("api/students") ResteasyWebTarget target) {

        assert target != null;

        final Invocation.Builder builder = target.request();
        builder.accept(MediaType.APPLICATION_JSON);
        builder.header("student-password", "  ");
        final Invocation invocation = builder.buildPost(Entity.entity(student, MediaType.APPLICATION_JSON));

        final Response response = invocation.invoke();

        assertEquals(Status.BAD_REQUEST, response.getStatusInfo());

    }

    @Test
    @RunAsClient
    @InSequence(9)
    public void failureEmailEmpty(@ArquillianResteasyResource("api/students") ResteasyWebTarget target) {

        assert target != null;

        student.setEmail("");

        final Invocation.Builder builder = target.request();
        builder.accept(MediaType.APPLICATION_JSON);
        builder.header("student-password", student.getPassword());
        final Invocation invocation = builder.buildPost(Entity.entity(student, MediaType.APPLICATION_JSON));

        final Response response = invocation.invoke();

        assertEquals(Status.BAD_REQUEST, response.getStatusInfo());

    }

    @Test
    @RunAsClient
    @InSequence(10)
    public void failureEmailBlank(@ArquillianResteasyResource("api/students") ResteasyWebTarget target) {

        assert target != null;

        student.setEmail("  ");

        final Invocation.Builder builder = target.request();
        builder.accept(MediaType.APPLICATION_JSON);
        builder.header("student-password", student.getPassword());
        final Invocation invocation = builder.buildPost(Entity.entity(student, MediaType.APPLICATION_JSON));

        final Response response = invocation.invoke();

        assertEquals(Status.BAD_REQUEST, response.getStatusInfo());

    }

}