package br.edu.ifrs.transnacionalidades.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

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

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class).addClass(ApplicationResource.class)
                .addPackage(StudentResource.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }

    @Before
    public void init() {

        student = new Student("Tomás Silvestre", LocalDate.of(1996, 4, 1), "andres.vidal1@example.com", "password2");

        /*
         * name": "Tomás Silvestre", "dateOfBirth": "1996-04-01", "email":
         * "tomas.silvestre@exemplo.com", "password": "password"
         */

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
    @InSequence(2)
    public void success(@ArquillianResteasyResource("api/students") ResteasyWebTarget target) {

        assert target != null;

    }
}