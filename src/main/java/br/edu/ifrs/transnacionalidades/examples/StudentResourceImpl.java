package br.edu.ifrs.transnacionalidades.examples;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Stateless
public class StudentResourceImpl implements StudentResource {

    @Inject
    private StudentService studentService;

    public Response create(Student student, String password) {

        if (student == null || password == null) {

            return Response.status(Status.BAD_REQUEST).entity("student-password header is missing.").build();
        }

        try {

            student.setPassword(password);
            studentService.create(student);
            return Response.status(Status.CREATED).entity(student).build();

        } catch (StudentValidationException e) {

            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();

        } catch (StudentAlreadyExistsException e) {

            return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
        }

    }

    public Response retrieve() {

        return Response.status(Status.OK).entity(studentService.retrieve()).build();
    }

    public Response retrieve(final Long id) {

        Student student = studentService.retrieve(id);

        if (student != null) {

            return Response.status(Status.OK).entity(student).build();

        } else {

            return Response.status(Status.NOT_FOUND).build();
        }
    }

    public Response update(final Long id, Student student) {

        try {

            student.setId(id);
            studentService.update(student);
            return Response.status(Status.OK).build();

        } catch (StudentValidationException e) {

            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();

        } catch (StudentAlreadyExistsException e) {

            return Response.status(Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    public Response delete(final Long id) {

        try {

            studentService.delete(id);
            return Response.status(Status.OK).build();

        } catch (EntityNotFoundException e) {

            return Response.status(Status.NOT_FOUND).entity("There is no student with id " + id).build();
        }
    }

}