/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.resources;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nu.te4.recipeheaven.beans.RecipeBean;
import nu.te4.recipeheaven.entities.Recipe;

/**
 *
 * @author Adrian Klasson
 */
@Path("recipe")
public class RecipeResource {
    
    @EJB
    private RecipeBean recipeBean;
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecipe(@PathParam("id") int id){
        Recipe recipe = recipeBean.getRecipe(id);
        return Response.ok(recipe).build();
    }
}
