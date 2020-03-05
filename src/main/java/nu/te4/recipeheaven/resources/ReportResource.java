/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.resources;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import nu.te4.recipeheaven.beans.ReportBean;
import nu.te4.recipeheaven.entities.ReportedRecipe;
import nu.te4.recipeheaven.exceptions.UnauthorizedException;

/**
 *
 * @author Adrian Klasson
 */
@Path("")
public class ReportResource {
    
    @EJB
    private ReportBean reportBean;
    
    @POST
    @Path("report/{recipeId}")
    public Response postReport(@PathParam("recipeId") int recipeId, @HeaderParam("token") String token){
        try {
            reportBean.postReport(recipeId, token);
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException ex) {
            throw new ServiceUnavailableException("Failed to post report: " + ex.getMessage());
        } catch (UnauthorizedException ex) {
            throw new NotAuthorizedException("Failed to post report: " + ex.getMessage(), Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("reports")
    public Response getReports(){
        try {
            List<ReportedRecipe> reportedRecipes = reportBean.getReports();
            return Response.ok(reportedRecipes).build();
        } catch (SQLException ex) {
            throw new ServiceUnavailableException("Failed to get reports: " + ex.getMessage());
        }
    }
    
    @DELETE
    @Path("reports/{recipeId}")
    public Response deleteReports(@PathParam("recipeId") int recipeId){
        try {
            reportBean.deleteReports(recipeId);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (SQLException ex) {
            throw new ServiceUnavailableException("Failed to delete reports: " + ex.getMessage());
        }
    }
}
