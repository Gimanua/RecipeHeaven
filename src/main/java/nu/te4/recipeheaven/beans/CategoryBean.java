/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import nu.te4.recipeheaven.ConnectionFactory;
import nu.te4.recipeheaven.entities.Category;
import nu.te4.recipeheaven.entities.Category.CategoryBuilder;

/**
 *
 * @author giman
 */
@Stateless
public class CategoryBean {

    public List<Category> getCategories(ResultSet categoryData) throws SQLException {
        List<Category> categories = new LinkedList();
        while (categoryData.next()) {
            CategoryBuilder categoryBuilder = new CategoryBuilder()
                    .categoryId(categoryData.getInt("category_id"))
                    .name(categoryData.getString("category_name"));
            categories.add(categoryBuilder.build());
        }
        return categories;
    }

    /**
     * Connects some categories to a recipe.
     *
     * @param categories The categories to connect.
     * @param recipeId The Id of the recipe to connect.
     */
    public void connectCategories(List<Category> categories, int recipeId) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        for (Category category : categories) {
            String sql = "INSERT INTO recipe_categories (recipe_id, category_id) VALUES (?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, recipeId);
            stmt.setInt(2, category.getCategoryId());
        }
    }
}
