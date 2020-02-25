/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.entities;

/**
 *
 * @author Adrian Klasson
 */
public final class Comment {

    private final Integer commentId;
    private final String comment;
    private final String posterUsername;

    private Comment(CommentBuilder builder) {
        this.commentId = builder.commentId;
        this.comment = builder.comment;
        this.posterUsername = builder.posterUsername;
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

    public static final class CommentBuilder {

        private final Integer commentId;
        private final String comment;
        private final String posterUsername;

        public CommentBuilder() {
            this.commentId = null;
            this.comment = null;
            this.posterUsername = null;
        }

        private CommentBuilder(Integer commentId, String comment, String posterUsername) {
            this.commentId = commentId;
            this.comment = comment;
            this.posterUsername = posterUsername;
        }
        
        public CommentBuilder commentId(Integer commentId){
            return new CommentBuilder(commentId, comment, posterUsername);
        }
        
        public CommentBuilder comment(String comment){
            return new CommentBuilder(commentId, comment, posterUsername);
        }
        
        public CommentBuilder posterUsername(String posterUsername){
            return new CommentBuilder(commentId, comment, posterUsername);
        }
        
        public Comment build(){
            return new Comment(this);
        }
    }
}
