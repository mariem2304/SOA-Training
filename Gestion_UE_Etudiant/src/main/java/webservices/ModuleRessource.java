package webservices;

import entities.Module;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/modules")
public class ModuleRessource {

    ModuleBusiness helper = new ModuleBusiness();

    @Path("/liste")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllModules() {
        return Response
                .status(200)
                .entity(helper.getAllModules())
                .build();
    }

    @Path("/ajout")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addModule(Module module) {
        if (helper.addModule(module)) {
            return Response.status(201).entity("Module ajouté avec succès ").build();
        } else {
            return Response.status(409).entity("Le module existe déjà ").build();
        }
    }

    @Path("/delete/{matricule}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteModule(@PathParam("matricule") String matricule){
        if(helper.deleteModule(matricule)){
            return Response.status(200).entity("Module supprimé avec succès ").build();
        }
        return Response.status(404).entity("Module non trouvé").build();
    }


    @Path("/search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByNom(@QueryParam("matricule") String matricule) {
        return Response
                .status(200)
                .entity(helper.getModuleByMatricule(matricule))
                .build();
    }

    @Path("/update/{matricule}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateModule(@PathParam("matricule") String matricule, Module module) {
        if(helper.updateModule(matricule, module)) {
            return Response.status(200).entity("Module mis à jour avec succès ").build();
        } else {
            return Response.status(404).entity("Module introuvable ").build();
        }
    }

}
