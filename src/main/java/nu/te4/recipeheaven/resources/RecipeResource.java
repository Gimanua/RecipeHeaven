/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.resources;

import java.sql.SQLException;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nu.te4.recipeheaven.beans.RecipeBean;
import nu.te4.recipeheaven.entities.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Adrian Klasson
 */
@Path("recipe")
public class RecipeResource {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeResource.class);
    
    @EJB
    private RecipeBean recipeBean;
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipe(@PathParam("id") int id){
        try {
            Recipe recipe = recipeBean.getRecipe(id);
            return Response.ok(recipe).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
    }
}
