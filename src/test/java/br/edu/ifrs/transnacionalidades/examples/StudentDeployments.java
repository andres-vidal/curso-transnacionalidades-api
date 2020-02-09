package br.edu.ifrs.transnacionalidades.examples;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import br.edu.ifrs.transnacionalidades.ApplicationResource;

public abstract class StudentDeployments {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class).addClass(ApplicationResource.class)
                .addPackage(StudentResource.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml");
    }
}