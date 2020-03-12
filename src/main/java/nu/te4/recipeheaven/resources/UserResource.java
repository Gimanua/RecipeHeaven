package nu.te4.recipeheaven.resources;

import java.sql.SQLException;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nu.te4.recipeheaven.beans.UserBean;
import nu.te4.recipeheaven.entities.User;
import nu.te4.recipeheaven.exceptions.UnauthorizedException;

/**
 *
 * @author Adrian Klasson
 */
@Produces(MediaType.APPLICATION_JSON)
@Path("")
public class UserResource {
    
    @EJB
    private UserBean userBean;
    
    @POST
    @Path("user")
    public Response postUser(@HeaderParam("token") String token){
        try {
            User user = userBean.postUser(token);
            return Response.status(Response.Status.CREATED).entity(user).build();
        } catch (UnauthorizedException ex) {
            throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).build());
        } catch (SQLException ex) {
            throw new ServiceUnavailableException("Failed to post user: " + ex.getMessage());
        }
    }
    
    @GET
    @Path("login")
    public Response login(@HeaderParam("token") String token){
        try {
            User user = userBean.login(token);
            if(user == null){
                return Response.ok().build();
            }
            else{
                return Response.status(Response.Status.CREATED).entity(user).build();
            }
        } catch (UnauthorizedException ex) {
            throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).build());
        } catch (SQLException ex) {
            throw new ServiceUnavailableException("Failed to login user: " + ex.getMessage());
        }
    }
}
