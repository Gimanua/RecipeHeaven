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
import javax.ejb.EJB;
import javax.ejb.Stateless;
import nu.te4.recipeheaven.ConnectionFactory;
import nu.te4.recipeheaven.entities.Reply;
import nu.te4.recipeheaven.entities.Reply.ReplyBuilder;
import nu.te4.recipeheaven.exceptions.UnauthorizedException;

/**
 *
 * @author Adrian Klasson
 */
@Stateless
public class ReplyBean {
    
    @EJB
    private UserBean userBean;
    
    public List<Reply> getReplies(int commentId) throws SQLException{
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "SELECT * FROM reply_info WHERE comment_id=?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, commentId);
            ResultSet data = stmt.executeQuery();
            List<Reply> replies = new LinkedList();
            while(data.next()){
                Reply reply = new ReplyBuilder()
                        .posterUsername(data.getString("poster_username"))
                        .reply(data.getString("reply"))
                        .id(data.getInt("reply_id"))
                        .commentId(data.getInt("comment_id"))
                        .build();
                replies.add(reply);
            }
            return replies;
        }
    }
    
    public Reply postReply(Reply reply, int commentId, String token) throws UnauthorizedException, SQLException{
        String sql = "INSERT INTO replies (id, user_id, comment_id, reply) VALUES(NULL,?,?,?)";
        PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        
        int userId = userBean.getUser(token).getId();
        
        stmt.setInt(1, userId);
        stmt.setInt(2, commentId);
        stmt.setString(3, reply.getReply());
        stmt.executeUpdate();
        
        ResultSet keys = stmt.getGeneratedKeys();
        keys.next();
        reply.setId(keys.getInt(1));
        
        return reply;
    }
}
