/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import nu.te4.recipeheaven.ConnectionFactory;
import nu.te4.recipeheaven.entities.ReportedRecipe;
import nu.te4.recipeheaven.entities.ReportedRecipe.ReportedRecipeBuilder;
import nu.te4.recipeheaven.exceptions.UnauthorizedException;

/**
 *
 * @author Adrian Klasson
 */
@Stateless
public class ReportBean {
    
    @EJB
    UserBean userBean;
    
    public void postReport(int recipeId, String token) throws SQLException, UnauthorizedException{
        String sql = "INSERT INTO reported_recipes (user_id, recipe_id) VALUES(?, ?)";
        PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
        
        int userId = userBean.getUser(token).getId();
        stmt.setInt(1, userId);
        stmt.setInt(2, recipeId);
        stmt.executeUpdate();
    }
    
    public List<ReportedRecipe> getReports() throws SQLException{
        String sql = "SELECT * FROM reported_recipes_info";
        PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
        ResultSet data = stmt.executeQuery();
        
        List<ReportedRecipe> reportedRecipes = new LinkedList();
        while(data.next()){
            ReportedRecipe reportedRecipe = new ReportedRecipeBuilder()
                    .recipeId(data.getInt("recipe_id"))
                    .reports(data.getInt("reports"))
                    .build();
            reportedRecipes.add(reportedRecipe);
        }
        
        return reportedRecipes;
    }
    
    public void deleteReports(int recipeId) throws SQLException{
        String sql = "DELETE FROM reported_recipes WHERE recipe_id=?";
        PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
        stmt.setInt(1, recipeId);
        stmt.executeUpdate();
    }
}
