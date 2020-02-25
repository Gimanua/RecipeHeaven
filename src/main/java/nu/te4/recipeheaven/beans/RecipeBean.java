/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.beans;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import nu.te4.recipeheaven.ConnectionFactory;
import nu.te4.recipeheaven.entities.Category;
import nu.te4.recipeheaven.entities.Category.CategoryBuilder;
import nu.te4.recipeheaven.entities.Recipe;
import nu.te4.recipeheaven.entities.Recipe.RecipeBuilder;
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
    
    public Recipe getRecipe(int id) throws IllegalArgumentException, SQLException {

        Connection connection = ConnectionFactory.getConnection();
        CallableStatement stmt = connection.prepareCall("{call complete_recipe(?)}");
        stmt.setInt(1, id);

        if (!stmt.execute()) {
            throw new IllegalArgumentException("The id '" + id + "' gave no recipe result.");
        }

        RecipeBuilder recipeBuilder = insertRecipeInfo(stmt.getResultSet());
        stmt.getMoreResults();
        List<Category> categories = categoryBean.getCategories(stmt.getResultSet());
        stmt.getMoreResults();

        recipeBuilder = recipeBuilder.categories(categories);
        return recipeBuilder.build();
    }

    private RecipeBuilder insertRecipeInfo(ResultSet recipeData) throws SQLException {
        recipeData.next();
        return new RecipeBuilder()
                .id(recipeData.getInt("recipe_id"))
                .likes(recipeData.getInt("likes"))
                .name(recipeData.getString("name"))
                .posterUsername(recipeData.getString("poster_username"))
                .image(recipeData.getString("image"))
                .description(recipeData.getString("description"));
    }
}
