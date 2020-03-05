/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import nu.te4.recipeheaven.ConnectionFactory;
import nu.te4.recipeheaven.entities.User;
import nu.te4.recipeheaven.exceptions.UnauthorizedException;

/**
 *
 * @author Adrian Klasson
 */
@Stateless
public class UserBean {
    
    @EJB
    GitHubOAuthBean gitHubOAuthBean;
    
    public User postUser(String token) throws UnauthorizedException, SQLException {
        JsonObject userInfo = gitHubOAuthBean.getUserInfo(token);
        User user = new User.UserBuilder()
                .oauthId(userInfo.getInt("id"))
                .username(userInfo.getString("login"))
                .build();
        
        String sql = "INSERT INTO users (id, username, oauth_id) VALUES(NULL, ?, ?)";
        PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, user.getUsername());
        stmt.setInt(2, user.getOauthId());
        stmt.executeUpdate();
        ResultSet keys = stmt.getGeneratedKeys();
        keys.next();
        user.setId(keys.getInt(1));
        
        return user;
    }
    
    public User getUser(String token) throws UnauthorizedException, SQLException{
        JsonObject userInfo = gitHubOAuthBean.getUserInfo(token);
        int oAuthId = userInfo.getInt("id");
        String sql = "SELECT * FROM users WHERE oauth_id=?";
        PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql);
        stmt.setInt(1, oAuthId);
        ResultSet data = stmt.executeQuery();
        data.next();
        return new User.UserBuilder()
                .id(data.getInt("id"))
                .username(data.getString("username"))
                .oauthId(data.getInt("oauth_id"))
                .build();
    }
}
