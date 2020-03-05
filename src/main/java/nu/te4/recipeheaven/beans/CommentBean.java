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
import nu.te4.recipeheaven.entities.Comment;
import nu.te4.recipeheaven.entities.Comment.CommentBuilder;
import nu.te4.recipeheaven.exceptions.UnauthorizedException;

/**
 *
 * @author Adrian Klasson
 */
@Stateless
public class CommentBean {
    
    @EJB
    private UserBean userBean;
    
    public List<Comment> getComments(ResultSet commentData) throws SQLException{
        List<Comment> comments = new LinkedList();
        while(commentData.next()){
            CommentBuilder builder = new CommentBuilder()
                    .comment(commentData.getString("comment"))
                    .commentId(commentData.getInt("comment_id"))
                    .posterUsername(commentData.getString("poster_username"));
            comments.add(builder.build());
        }
        return comments;
    }
    
    public Comment postComment(int recipeId, Comment comment, String token) throws SQLException, UnauthorizedException{
        String sql = "INSERT INTO comments (id,user_id,recipe_id,comment) VALUES(NULL,?,?,?)";
        PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        
        int userId = userBean.getUser(token).getId();
        
        stmt.setInt(1, userId);
        stmt.setInt(2, recipeId);
        stmt.setString(3, comment.getComment());
        
        stmt.executeUpdate();
        ResultSet keys = stmt.getGeneratedKeys();
        keys.next();
        comment.setCommentId(keys.getInt(1));
        return comment;
    }
}
