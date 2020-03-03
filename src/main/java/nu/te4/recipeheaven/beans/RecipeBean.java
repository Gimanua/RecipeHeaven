/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.beans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import nu.te4.recipeheaven.ConnectionFactory;
import nu.te4.recipeheaven.entities.Category;
import nu.te4.recipeheaven.entities.Comment;
import nu.te4.recipeheaven.entities.Ingredient;
import nu.te4.recipeheaven.entities.Instruction;
import nu.te4.recipeheaven.entities.Recipe;
import nu.te4.recipeheaven.entities.Recipe.RecipeBuilder;
import nu.te4.recipeheaven.entities.Reply;
import nu.te4.recipeheaven.exceptions.EntityMissingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Adrian Klasson
 */
@Stateless
public class RecipeBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeBean.class);

    @EJB
    private CategoryBean categoryBean;

    @EJB
    private IngredientBean ingredientBean;

    @EJB
    private InstructionBean instructionBean;

    @EJB
    private CommentBean commentBean;

    @EJB
    private ReplyBean replyBean;

    public Recipe getRecipe(int id) throws SQLException, EntityMissingException {
        CallableStatement stmt = ConnectionFactory.getConnection().prepareCall("{call complete_recipe(?)}");
        stmt.setInt(1, id);
        if (!stmt.execute()) {
            throw new EntityMissingException("No recipe with id " + id);
        }

        RecipeBuilder recipeBuilder = new RecipeBuilder();
        appendRecipeInfo(stmt.getResultSet(), recipeBuilder);
        
        stmt.getMoreResults();
        List<Category> categories = categoryBean.getCategories(stmt.getResultSet());
        stmt.getMoreResults();
        List<Ingredient> ingredients = ingredientBean.getIngredients(stmt.getResultSet());
        stmt.getMoreResults();
        List<Instruction> instructions = instructionBean.getInstructions(stmt.getResultSet());
        stmt.getMoreResults();
        List<Comment> comments = commentBean.getComments(stmt.getResultSet());
        stmt.getMoreResults();
        List<Reply> replies = replyBean.getReplies(stmt.getResultSet());

        recipeBuilder.categories(categories)
                .ingredients(ingredients)
                .instructions(instructions)
                .comments(comments)
                .replies(replies);
        return recipeBuilder.build();
    }

    public List<Recipe> getBriefRecipes(int numberOfRecipes) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM recipe_info ORDER BY likes DESC LIMIT ?");
        stmt.setInt(1, numberOfRecipes);
        ResultSet briefRecipesData = stmt.executeQuery();
        List<Recipe> recipes = new LinkedList();
        while (briefRecipesData.next()) {
            RecipeBuilder builder = new RecipeBuilder()
                    .id(briefRecipesData.getInt("recipe_id"))
                    .likes(briefRecipesData.getInt("likes"))
                    .name(briefRecipesData.getString("name"))
                    .posterUsername(briefRecipesData.getString("poster_username"))
                    .image(briefRecipesData.getString("image"))
                    .description(briefRecipesData.getString("description"));
            recipes.add(builder.build());
        }
        return recipes;
    }

    private RecipeBuilder appendRecipeInfo(ResultSet recipeData, RecipeBuilder builder) throws SQLException {
        recipeData.next();
        return builder
                .likes(recipeData.getInt("likes"))
                .name(recipeData.getString("name"))
                .posterUsername(recipeData.getString("poster_username"))
                .image(recipeData.getString("image"))
                .description(recipeData.getString("description"));
    }
    
    public void postRecipe(Recipe recipe) throws SQLException {
        LOGGER.debug("Trying to post recipe: {}" + recipe);

        String sql = "INSERT INTO recipes (user_id, image, name, description) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, recipe.getUserId());
        stmt.setString(2, recipe.getImage());
        stmt.setString(3, recipe.getName());
        stmt.setString(4, recipe.getDescription());
        stmt.executeUpdate();
        ResultSet keys = stmt.getGeneratedKeys();
        if (keys.next()) {
            int id = keys.getInt(1);
            recipe.setId(id);
        }

        categoryBean.connectCategories(recipe.getCategories(), recipe.getId());
        ingredientBean.connectIngredients(recipe.getIngredients(), recipe.getId());
        instructionBean.insertInstructions(recipe.getInstructions(), recipe.getId());
    }

    /**
     * Deletes a recipe with a certain ID from the projects database.
     *
     * @param recipeId The ID of the recipe to delete.
     * @throws SQLException When the request cannot be processed by the
     * database.
     */
    public void deleteRecipe(int recipeId) throws SQLException, EntityMissingException {
        String sql = "DELETE FROM recipes WHERE id=?";
        PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
        stmt.setInt(1, recipeId);
        if(stmt.executeUpdate() != 1){
            throw new EntityMissingException("No recipe with id " + recipeId);
        }
    }
}
