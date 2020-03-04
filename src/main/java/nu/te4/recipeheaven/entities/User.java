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
public class User {

    private Integer id;
    private String username;
    private Integer oauthId;

    public User() {
    }

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.oauthId = builder.oauthId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getOauthId() {
        return oauthId;
    }

    public void setOauthId(Integer oauthId) {
        this.oauthId = oauthId;
    }

    public static final class UserBuilder {

        private Integer id;
        private String username;
        private Integer oauthId;

        public UserBuilder id(Integer id){
            this.id = id;
            return this;
        }
        
        public UserBuilder username(String username){
            this.username = username;
            return this;
        }
        
        public UserBuilder oauthId(Integer oauthId){
            this.oauthId = oauthId;
            return this;
        }
        
        public User build(){
            return new User(this);
        }
    }
}
