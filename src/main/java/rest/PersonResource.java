package rest;
/*
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import businessfacades.PersonDTOFacade;
import dtos.PersonDTO;
import errorhandling.EntityNotFoundException;
import datafacades.IDataFacade;
import businessfacades.ParentDTOFacade;
import facades.IDataFacade;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("person")
public class PersonResource {

    //Todo Remove or change relevant parts before ACTUAL use



        private static final IDataFacade<PersonDTO> FACADE =  PersonDTOFacade.getFacade();
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
            PersonDTO p = FACADE.getByInt(id);
            return Response.ok().entity(GSON.toJson(p)).build();
        }

        @POST
        @Produces({MediaType.APPLICATION_JSON})
        @Consumes({MediaType.APPLICATION_JSON})
        public Response create(String content) {
            PersonDTO pdto = GSON.fromJson(content, PersonDTO.class);
            PersonDTO newPdto = FACADE.create(pdto);
            return Response.ok().entity(GSON.toJson(newPdto)).build();
        }

        @PUT
        @Path("{id}")
        @Produces({MediaType.APPLICATION_JSON})
        @Consumes({MediaType.APPLICATION_JSON})
        public Response update(@PathParam("id") int id, String content) throws EntityNotFoundException {
            PersonDTO pdto = GSON.fromJson(content, PersonDTO.class);
            pdto.setId(id);
            PersonDTO updated = FACADE.update(pdto);
            return Response.ok().entity(GSON.toJson(updated)).build();
        }

        @DELETE
        @Path("{id}")
        @Produces({MediaType.APPLICATION_JSON})
        public Response delete(@PathParam("id") int id) throws EntityNotFoundException {
            PersonDTO deleted = FACADE.delete(id);
            return Response.ok().entity(GSON.toJson(deleted)).build();
        }
    }

*/