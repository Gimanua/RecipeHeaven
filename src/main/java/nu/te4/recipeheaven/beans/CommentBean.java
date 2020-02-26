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
import nu.te4.recipeheaven.entities.Comment;
import nu.te4.recipeheaven.entities.Comment.CommentBuilder;

/**
 *
 * @author Adrian Klasson
 */
@Stateless
public class CommentBean {
    
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
}
