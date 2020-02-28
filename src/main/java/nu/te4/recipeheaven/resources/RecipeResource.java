/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.resources;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
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
@Produces(MediaType.APPLICATION_JSON)
@Path("")
public class RecipeResource {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeResource.class);
    
    @EJB
    private RecipeBean recipeBean;
    
    @GET
    @Path("recipe/{id}")
    public Response getRecipe(@PathParam("id") int id){
        try {
            Recipe recipe = recipeBean.getRecipe(id);
            return Response.ok(recipe).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException ex) {
            LOGGER.error("Failed to retrieve data: {}", ex.getMessage());
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
    }
    
    @GET
    @Path("briefRecipes/{numberOfRecipes}")
    public Response getBriefRecipes(@PathParam("numberOfRecipes") int numberOfRecipes){
        try {
            List<Recipe> recipes = recipeBean.getBriefRecipes(numberOfRecipes);
            return Response.ok(recipes).build();
        } catch (SQLException ex) {
            LOGGER.error("Failed to retrieve data: {}", ex.getMessage());
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
    }
}
