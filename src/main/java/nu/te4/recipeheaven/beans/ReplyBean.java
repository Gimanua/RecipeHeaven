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
import nu.te4.recipeheaven.entities.Reply;
import nu.te4.recipeheaven.entities.Reply.ReplyBuilder;

/**
 *
 * @author Adrian Klasson
 */
@Stateless
public class ReplyBean {
    
    public List<Reply> getReplies(ResultSet replyData) throws SQLException{
        List<Reply> replies = new LinkedList();
        while (replyData.next()) {
            ReplyBuilder builder = new ReplyBuilder()
                    .commentId(replyData.getInt("comment_id"))
                    .posterUsername(replyData.getString("poster_username"))
                    .reply(replyData.getString("reply"));
            replies.add(builder.build());
        }
        return replies;
    }
}
