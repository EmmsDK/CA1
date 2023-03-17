package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import entities.Person;
import errorhandling.EntityNotFoundException;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("person")
public class PersonResource {

    //Todo Remove or change relevant parts before ACTUAL use

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //private static final IDataFacade<PersonDTO> FACADE = PersonDTOFacade.getFacade();
    private static final PersonFacade FACADE = (PersonFacade) PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") int id) throws EntityNotFoundException {
        Person p = FACADE.getByInt(id);
        return Response.ok().entity(GSON.toJson(p)).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(String content) {
        Person p = GSON.fromJson(content, Person.class);
        Person newP = FACADE.create(p);
        return Response.ok().entity(GSON.toJson(newP)).build();
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") int id, String content) throws EntityNotFoundException {
        Person p = GSON.fromJson(content, Person.class);
        p.setId(id);
        Person updated = FACADE.update(p);
        return Response.ok().entity(GSON.toJson(updated)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") int id) throws EntityNotFoundException {
        Person deleted = FACADE.delete(id);
        return Response.ok().entity(GSON.toJson(deleted)).build();
    }
}

