/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.resources;

import java.sql.SQLException;
import javax.ejb.EJB;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.Response;
import nu.te4.recipeheaven.beans.LikeBean;
import nu.te4.recipeheaven.exceptions.UnauthorizedException;

/**
 *
 * @author Adrian Klasson
 */
@Path("")
public class LikeResource {
    
    @EJB
    LikeBean likeBean;
    
    @POST
    @Path("like/{recipeId}")
    public Response postLike(@PathParam("recipeId") int recipeId, @HeaderParam("token") String token){
        try {
            likeBean.postLike(recipeId, token);
            return Response.status(Response.Status.CREATED).build();
        } catch (UnauthorizedException ex) {
            throw new NotAuthorizedException("Failed to post like: " + ex.getMessage(), Response.status(Response.Status.UNAUTHORIZED).build());
        } catch (SQLException ex) {
            throw new ServiceUnavailableException("Failed to post like: " + ex.getMessage());
        }
    }
}
