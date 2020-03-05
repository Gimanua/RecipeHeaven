/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.resources;

import java.sql.SQLException;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nu.te4.recipeheaven.beans.CommentBean;
import nu.te4.recipeheaven.entities.Comment;
import nu.te4.recipeheaven.exceptions.UnauthorizedException;

/**
 *
 * @author Adrian Klasson
 */
@Path("")
public class CommentResource {
    
    @EJB
    private CommentBean commentBean;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("comment/{recipeId}")
    public Response postComment(@PathParam("recipeId") int recipeId, @Valid Comment comment, @HeaderParam("token") String token){
        try {
            Comment postedComment = commentBean.postComment(recipeId, comment, token);
            return Response.status(Response.Status.CREATED).entity(postedComment).build();
        } catch (SQLException ex) {
            throw new ServiceUnavailableException("Failed to post comment: " + ex.getMessage());
        } catch (UnauthorizedException ex) {
            throw new NotAuthorizedException("Failed to post comment: " + ex.getMessage(), Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
