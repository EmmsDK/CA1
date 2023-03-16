package rest;

/*
import businessfacade.HobbyDTOFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import businessfacades.HobbyDTOFacade;
import dtos.HobbyDTO;
import errorhandling.EntityNotFoundException;
import datafacades.IDataFacade;
import businessfacades.ParentDTOFacade;
import facades.IDataFacade;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("hobby")
public class HobbyResource {


    //Todo Remove or change relevant parts before ACTUAL use

        private static final IDataFacade<HobbyDTO> FACADE =  HobbyDTOFacade.getFacade();
        private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

        @GET
        @Produces({MediaType.APPLICATION_JSON})
        public Response getAll() {
            return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();
        }
//VOKSEN HJÆLP PLOX
        @GET
        @Path("/{id}")
        @Produces({MediaType.APPLICATION_JSON})
        public Response getByName(@PathParam("id") String id) throws EntityNotFoundException {
            HobbyDTO p = FACADE.getByString(id);
            return Response.ok().entity(GSON.toJson(p)).build();
        }

        @POST
        @Produces({MediaType.APPLICATION_JSON})
        @Consumes({MediaType.APPLICATION_JSON})
        public Response create(String content) {
            HobbyDTO hdto = GSON.fromJson(content, HobbyDTO.class);
            HobbyDTO newHdto = FACADE.create(hdto);
            return Response.ok().entity(GSON.toJson(newHdto)).build();
        }

        @PUT
        @Path("{id}")
        @Produces({MediaType.APPLICATION_JSON})
        @Consumes({MediaType.APPLICATION_JSON})
        public Response update(@PathParam("id") String id, String content) throws EntityNotFoundException {
            HobbyDTO hdto = GSON.fromJson(content, HobbyDTO.class);
            hdto.setName(id);
            HobbyDTO updated = FACADE.update(hdto);
            return Response.ok().entity(GSON.toJson(updated)).build();
        }

        @DELETE
        @Path("{id}")
        @Produces({MediaType.APPLICATION_JSON})
        public Response delete(@PathParam("id") String id) throws EntityNotFoundException {
            HobbyDTO deleted = FACADE.delete(id);
            return Response.ok().entity(GSON.toJson(deleted)).build();
        }
    }
*/