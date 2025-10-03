package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UERessources
{
    UniteEnseignementBusiness helper = new UniteEnseignementBusiness();
    @Path("/liste")
    @GET
    @Produces (MediaType.APPLICATION_JSON)
    public  Response liste()
    {
        return Response.status(200).entity(
                helper.getListeUE()
        ).build();
    }


    @Path("/ajout")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response ajout(UniteEnseignement ue)
    {
        if(helper.addUniteEnseignement(ue))
            return Response.status(201).entity("Added succesfully").build();
        else
            return Response.status(409).entity("Already exists!").build();
    }
}
