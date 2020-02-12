package br.edu.ifrs.transnacionalidades.examples;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/students")
public interface StudentResource {

    /**
     * 
     * @param student  A JSON object representing the Student to be created. To be
     *                 considered valid, "email" must be non null and a valid email
     *                 address, "name" must not be blank and "dateOfBirth" must not
     *                 be null and must be a past date in "yyyy-mm-dd" format.
     * 
     * @param password A string contained in the "student-password" custom HTTP
     *                 header, to be new student's password.
     * 
     * @return Http Response CREATED containing a JSON representation of the student
     *         that was just created, if the student was successfully created.
     * 
     *         Http Response BAD REQUEST containing an explicative message, if no
     *         password was provided, if the provided password is invalid, if no
     *         student is provided or if the provided student is invalid.
     * 
     *         Http Response CONFLICT containing an explicative message, if there is
     *         an user already registered with the provided email address.
     */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Student student, @HeaderParam("student-password") String password);

    /**
     * 
     * @return A JSON array containing JSON representations of all the students in
     *         the database.
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieve();

    /**
     * 
     * @param id The id of the student to be retrieved, passed as a path parameter.
     * 
     * @return HTTP Response OK containing a JSON representation of the student with
     *         the provided id.
     * 
     *         HTTP Response NOT FOUND containing an explicative message, if there
     *         is no student registered with the provided id.
     * 
     */

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieve(@PathParam("id") Long id);

    /**
     * 
     * @param id      The id of the student to be retrieved, passed as a path
     *                parameter.
     * 
     * @param student A JSON object representing the Student to be created. To be
     *                considered valid, "email" must be non null and a valid email
     *                address, "name" must not be blank and "dateOfBirth" must not
     *                be null and must be a past date in "yyyy-mm-dd" format.
     * 
     * @return Http Response OK if the student was successfully updated.
     * 
     *         Http Response BAD REQUEST if no student is provided or if the
     *         provided student is invalid.
     * 
     *         Http Response CONFLICT containing an explicative message, if there is
     *         an user already registered with the provided email address.
     * 
     *         HTTP Response NOT FOUND containing an explicative message, if there
     *         is no student registered with the provided id.
     */

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Student student);

    /**
     * 
     * @param id The id of the student to be deleted, passed as a path parameter.
     * 
     * @return HTTP Response OK, if the student was successfully deleted.
     * 
     *         HTTP Response NOT FOUND containing an explicative message, if there
     *         is no student registered with the provided id.
     * 
     */

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id);

}