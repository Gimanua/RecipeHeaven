/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.resources;

import javax.ejb.EJB;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nu.te4.recipeheaven.beans.GitHubOAuthBean;
import nu.te4.recipeheaven.exceptions.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Adrian Klasson
 */
@Path("")
public class GitHubOAuthResource {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubOAuthResource.class);
    
    @EJB
    private GitHubOAuthBean gitHubOAuthBean;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("token/{code}")
    public Response getToken(@PathParam("code") String code){
        String token = gitHubOAuthBean.getToken(code);
        return Response.ok(token).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user-info/{token}")
    public Response getUserInfo(@PathParam("token") String token){
        try {
            JsonObject userInfo = gitHubOAuthBean.getUserInfo(token);
            return Response.ok(userInfo).build();
        } catch (UnauthorizedException ex) {
            throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
