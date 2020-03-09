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
import nu.te4.recipeheaven.entities.Ingredient;
import nu.te4.recipeheaven.entities.Ingredient.IngredientBuilder;

/**
 *
 * @author Adrian Klasson
 */
@Stateless
public class IngredientBean {
    
    public List<Ingredient> getIngredients(ResultSet ingredientData) throws SQLException{
        List<Ingredient> ingredients = new LinkedList();
        while(ingredientData.next()){
            IngredientBuilder builder = new IngredientBuilder()
                    .amount(ingredientData.getDouble("amount"))
                    .ingredientId(ingredientData.getInt("ingredient_id"))
                    .name(ingredientData.getString("name"))
                    .unitAbbreviation(ingredientData.getString("unit_abbreviation"));
            ingredients.add(builder.build());
        }
        return ingredients;
    }
    
    /**
     * Connects some ingredients to a recipe.
     *
     * @param ingredients The ingredients to connect.
     * @param recipeId The Id of the recipe to connect.
     */
    public void connectIngredients(List<Ingredient> ingredients, int recipeId) throws SQLException{
        Connection connection = ConnectionFactory.getConnection();
        for (Ingredient ingredient : ingredients) {
            
            String sql = ingredient.getUnitId() == null ? 
                    "INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES (?, ?, ?, NULL)" : 
                    "INSERT INTO recipe_ingredients (recipe_id, ingredient_id, amount, unit_id) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, recipeId);
            stmt.setInt(2, ingredient.getIngredientId());
            stmt.setDouble(3, ingredient.getAmount());
            if(ingredient.getUnitId() != null){
                stmt.setInt(4, ingredient.getUnitId());
            }
            stmt.executeUpdate();
        }
    }
    
    public void putIngredients(List<Ingredient> ingredients, int recipeId) throws SQLException{
        deleteIngredients(recipeId);
        connectIngredients(ingredients, recipeId);
    }
    
    private void deleteIngredients(int recipeId) throws SQLException{
        String sql = "DELETE FROM recipe_ingredients WHERE recipe_id=?";
        PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
        stmt.setInt(1, recipeId);
        stmt.executeUpdate();
    }
}
