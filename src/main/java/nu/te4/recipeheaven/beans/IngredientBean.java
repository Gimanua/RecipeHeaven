/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
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
                    .unitAbbreviation(ingredientData.getString("unit_abbreviation"))
                    .unitName(ingredientData.getString("unit_name"));
            ingredients.add(builder.build());
        }
        return ingredients;
    }
}
