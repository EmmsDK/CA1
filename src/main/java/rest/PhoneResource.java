package rest;
/*
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ParentDTO;
import errorhandling.EntityNotFoundException;
import datafacades.IDataFacade;
import businessfacades.ParentDTOFacade;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.ws.rs.Path;

@Path("phone")
public class PhoneResource {



        private static final IDataFacade<ParentDTO> FACADE =  ParentDTOFacade.getFacade();
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
            ParentDTO p = FACADE.getById(id);
            return Response.ok().entity(GSON.toJson(p)).build();
        }





        @DELETE
        @Path("{id}")
        @Produces({MediaType.APPLICATION_JSON})
        public Response delete(@PathParam("id") int id) throws EntityNotFoundException {
            ParentDTO deleted = FACADE.delete(id);
            return Response.ok().entity(GSON.toJson(deleted)).build();
        }
    }
*/