package nu.te4.recipeheaven.resources;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nu.te4.recipeheaven.beans.RecipeBean;
import nu.te4.recipeheaven.entities.Recipe;
import nu.te4.recipeheaven.exceptions.EntityMissingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Contains resources regarding recipes.
 *
 * @author Adrian Klasson
 */
@Produces(MediaType.APPLICATION_JSON)
@Path("")
public class RecipeResource {

    /**
     * Logs information.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeResource.class);

    /**
     * The bean doing all the actual work.
     */
    @EJB
    private RecipeBean recipeBean;

    /**
     * Gets a recipe with a certian ID.
     *
     * @param id The ID of the recipe to get.
     * @return A response with the recipe or an error status embedded.
     */
    @GET
    @Path("recipe/{id}")
    public Response getRecipe(@PathParam("id") int id) {
        try {
            Recipe recipe = recipeBean.getRecipe(id);
            return Response.ok(recipe).build();
        } catch (SQLException ex) {
            throw new ServiceUnavailableException("Failed to get recipe: " + ex.getMessage());
        } catch (EntityMissingException ex) {
            throw new NotFoundException("Failed to get recipe: " + ex.getMessage());
        }
    }

    /**
     * Gets brief information about a number of recipes.
     *
     * @param numberOfRecipes The number of recipes to get brief information of.
     * @return A response with the recipes or an error status embedded.
     */
    @GET
    @Path("brief-recipes/{numberOfRecipes}")
    public Response getBriefRecipes(@PathParam("numberOfRecipes") int numberOfRecipes) {
        try {
            List<Recipe> recipes = recipeBean.getBriefRecipes(numberOfRecipes);
            return Response.ok(recipes).build();
        } catch (SQLException ex) {
            LOGGER.error("Failed to retrieve data: {}", ex.getMessage());
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
    }

    /**
     * Posts a Recipe.
     *
     * @param recipe The Recipe to post.
     * @return A response with a status embedded.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("recipe")
    public Response postRecipe(@Valid Recipe recipe) {
        try {
            recipeBean.postRecipe(recipe);
            return Response.status(Response.Status.CREATED).entity(recipe).build();
        } catch (SQLException ex) {
            LOGGER.error("Failed to post Recipe: {}", ex.getMessage());
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
    }

    /**
     * Deletes a Recipe with a certain ID.
     *
     * @param id The ID of the Recipe to delete.
     * @return A response with a status embedded.
     */
    @DELETE
    @Path("recipe/{id}")
    public Response deleteRecipe(@PathParam("id") int id) {
        try {
            recipeBean.deleteRecipe(id);
            LOGGER.debug("Deleted recipe with id: {}", id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (SQLException ex) {
            throw new ServiceUnavailableException("Failed to delete recipe: " + ex.getMessage());
        } catch (EntityMissingException ex) {
            throw new NotFoundException("Failed to delete recipe: " + ex.getMessage());
        }
    }
}
