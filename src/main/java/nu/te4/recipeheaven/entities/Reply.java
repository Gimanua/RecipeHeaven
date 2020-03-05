/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.entities;

import javax.validation.constraints.NotEmpty;
import nu.te4.recipeheaven.building.Rebuildable;
import nu.te4.recipeheaven.entities.Reply.ReplyBuilder;

/**
 *
 * @author Adrian Klasson
 */
public final class Reply implements Rebuildable<ReplyBuilder>{

    private Integer id;
    private Integer commentId;
    @NotEmpty
    private String reply;
    private String posterUsername;

    public Reply(){
        
    }
    
    public Reply(ReplyBuilder builder) {
        this.id = builder.id;
        this.commentId = builder.commentId;
        this.reply = builder.reply;
        this.posterUsername = builder.posterUsername;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public void setPosterUsername(String posterUsername) {
        this.posterUsername = posterUsername;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public String getReply() {
        return reply;
    }

    public String getPosterUsername() {
        return posterUsername;
    }

    @Override
    public ReplyBuilder rebuild() {
        return new ReplyBuilder()
                .commentId(commentId)
                .posterUsername(posterUsername)
                .reply(reply);
    }

    @Override
    public String toString() {
        return "Reply{" + "commentId=" + commentId + ", reply=" + reply + ", posterUsername=" + posterUsername + '}';
    }

    public static final class ReplyBuilder {

        private Integer id;
        private Integer commentId;
        private String reply;
        private String posterUsername;
        
        public ReplyBuilder id(Integer id){
            this.id = id;
            return this;
        }
        
        public ReplyBuilder commentId(Integer commentId){
            this.commentId = commentId;
            return this;
        }
        
        public ReplyBuilder reply(String reply){
            this.reply = reply;
            return this;
        }
        
        public ReplyBuilder posterUsername(String posterUsername){
            this.posterUsername = posterUsername;
            return this;
        }
        
        public Reply build(){
            return new Reply(this);
        }
    }
}
