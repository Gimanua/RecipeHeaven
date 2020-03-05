/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.entities;

import javax.validation.constraints.NotEmpty;
import nu.te4.recipeheaven.building.Rebuildable;
import nu.te4.recipeheaven.entities.Comment.CommentBuilder;

/**
 *
 * @author Adrian Klasson
 */
public final class Comment implements Rebuildable<CommentBuilder>{

    private Integer commentId;
    @NotEmpty
    private String comment;
    private String posterUsername;

    public Comment(){
        
    }
    
    public Comment(CommentBuilder builder) {
        this.commentId = builder.commentId;
        this.comment = builder.comment;
        this.posterUsername = builder.posterUsername;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPosterUsername(String posterUsername) {
        this.posterUsername = posterUsername;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public String getPosterUsername() {
        return posterUsername;
    }

    @Override
    public CommentBuilder rebuild() {
        return new CommentBuilder()
                .comment(comment)
                .commentId(commentId)
                .posterUsername(posterUsername);
    }

    @Override
    public String toString() {
        return "Comment{" + "commentId=" + commentId + ", comment=" + comment + ", posterUsername=" + posterUsername + '}';
    }

    public static final class CommentBuilder {

        private Integer commentId;
        private String comment;
        private String posterUsername;
        
        public CommentBuilder commentId(Integer commentId){
            this.commentId = commentId;
            return this;
        }
        
        public CommentBuilder comment(String comment){
            this.comment = comment;
            return this;
        }
        
        public CommentBuilder posterUsername(String posterUsername){
            this.posterUsername = posterUsername;
            return this;
        }
        
        public Comment build(){
            return new Comment(this);
        }
    }
}
