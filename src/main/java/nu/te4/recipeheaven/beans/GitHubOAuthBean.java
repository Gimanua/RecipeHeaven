/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.beans;

import java.io.IOException;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nu.te4.recipeheaven.exceptions.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Adrian Klasson
 */
@Stateless
public class GitHubOAuthBean {
    
    private static final String CLIENT_ID = PropertyBean.getProperty(PropertyBean.GitHubOAuthProperty.CLIENT_ID);
    private static final String CLIENT_SECRET = PropertyBean.getProperty(PropertyBean.GitHubOAuthProperty.CLIENT_SECRET);
    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubOAuthBean.class);
    
    public String getToken(String code) {
        String url = String.format("https://github.com/login/oauth/access_token?client_id=%s&client_secret=%s&code=%s", CLIENT_ID, CLIENT_SECRET, code);
        Client client = ClientBuilder.newClient();
        String result = client.target(url).request().post(null, String.class);
        LOGGER.debug("Result from GitHub: {}", result);
        return result.substring(13, result.indexOf("&"));
    }
    
    public JsonObject getUserInfo(String token) throws UnauthorizedException{
        String url = String.format("https://api.github.com/user?access_token=%s", token);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        if(response.getStatus() != 200){
            throw new UnauthorizedException("Unauthorized");
        }
        return response.readEntity(JsonObject.class);
    }
    
    public boolean verifyToken(String token){
        String url = String.format("https://api.github.com/user?access_token=%s", token);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        return target.request(MediaType.APPLICATION_JSON).get().getStatus() == 200;
    }
}
