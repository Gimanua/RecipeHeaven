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
public class Credentials {
    
    private String username;
    private Integer oAuthId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getoAuthId() {
        return oAuthId;
    }

    public void setoAuthId(Integer oAuthId) {
        this.oAuthId = oAuthId;
    }
}
