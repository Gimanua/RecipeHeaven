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
import javax.ejb.Stateless;
import nu.te4.recipeheaven.ConnectionFactory;
import nu.te4.recipeheaven.entities.Category;
import nu.te4.recipeheaven.entities.Category.CategoryBuilder;
import nu.te4.recipeheaven.entities.Recipe;
import nu.te4.recipeheaven.entities.Recipe.RecipeBuilder;

/**
 *
 * @author Adrian Klasson
 */
@Stateless
public class RecipeBean {
    
    public Recipe getRecipe(int id){
        
        try (Connection connection = ConnectionFactory.getConnection()){
            String sql = "{call complete_recipe(?)}";
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.setInt(1, id);
            
            stmt.execute();
            
            ResultSet recipeData = stmt.getResultSet();
            RecipeBuilder recipeBuilder = new RecipeBuilder();
            if(recipeData != null && recipeData.next()){
                recipeBuilder.id(recipeData.getInt("recipe_id"));
                recipeBuilder.likes(recipeData.getInt("likes"));
                recipeBuilder.name(recipeData.getString("name"));
                recipeBuilder.posterUsername(recipeData.getString("poster_username"));
                recipeBuilder.image(recipeData.getString("image"));
                recipeBuilder.description(recipeData.getString("description"));
            }
            
            stmt.getMoreResults();
            
            ResultSet categoryData = stmt.getResultSet();
            List<Category> categories = new LinkedList();
            if(categoryData != null){
                while(categoryData.next()){
                    CategoryBuilder categoryBuilder = new CategoryBuilder();
                    categoryBuilder.id(categoryData.getInt("category_id"));
                    categoryBuilder.name(categoryData.getString("category_name"));
                    categories.add(categoryBuilder.build());
                }
            }
            recipeBuilder.categories(categories);
            
            return recipeBuilder.build();
        } catch (SQLException e) {
            System.out.println("RecipeBean.getRecipe: " + e.getMessage());
        }
        
        return null;
    }
}
