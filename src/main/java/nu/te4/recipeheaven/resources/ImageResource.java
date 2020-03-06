/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.resources;

import java.io.File;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import nu.te4.recipeheaven.beans.ImageBean;

/**
 *
 * @author Adrian Klasson
 */
@Path("")
public class ImageResource {
    
    @EJB
    ImageBean imageBean;
    
    @GET
    @Produces("image/jpg")
    @Path("image/{recipeId}")
    public Response getImage(@PathParam("recipeId") int recipeId){
        File image = imageBean.getImage(recipeId);
        return Response
                .ok((Object)image)
                .header("Content-Disposition","attachment; filename=recipe_image.jpg")
                .build();
    }
}
