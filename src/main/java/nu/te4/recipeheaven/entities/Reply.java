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
public final class Reply {

    private final Integer commentId;
    private final String reply;
    private final String posterUsername;

    private Reply(ReplyBuilder builder) {
        this.commentId = builder.commentId;
        this.reply = builder.reply;
        this.posterUsername = builder.posterUsername;
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

    public static final class ReplyBuilder {

        private final Integer commentId;
        private final String reply;
        private final String posterUsername;

        public ReplyBuilder() {
            this.commentId = null;
            this.reply = null;
            this.posterUsername = null;
        }

        private ReplyBuilder(Integer commentId, String reply, String posterUsername) {
            this.commentId = commentId;
            this.reply = reply;
            this.posterUsername = posterUsername;
        }
        
        public ReplyBuilder commentId(Integer commentId){
            return new ReplyBuilder(commentId, reply, posterUsername);
        }
        
        public ReplyBuilder reply(String reply){
            return new ReplyBuilder(commentId, reply, posterUsername);
        }
        
        public ReplyBuilder posterUsername(String posterUsername){
            return new ReplyBuilder(commentId, reply, posterUsername);
        }
        
        public Reply build(){
            return new Reply(this);
        }
    }
}
