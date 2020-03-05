/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.beans;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import nu.te4.recipeheaven.ConnectionFactory;
import nu.te4.recipeheaven.exceptions.UnauthorizedException;

/**
 *
 * @author Adrian Klasson
 */
@Stateless
public class LikeBean {
    
    @EJB
    private UserBean userBean;
    
    public void postLike(int recipeId, String token) throws UnauthorizedException, SQLException{
        int userId = userBean.getUser(token).getId();
        String sql = "INSERT INTO likes (user_id, recipe_id) VALUES(?,?)";
        PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
        stmt.setInt(1, userId);
        stmt.setInt(2, recipeId);
        stmt.executeUpdate();
    }
}
