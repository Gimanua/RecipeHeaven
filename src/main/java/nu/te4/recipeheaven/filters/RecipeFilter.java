/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.filters;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Adrian Klasson
 */
@Provider
public class RecipeFilter implements ContainerRequestFilter{

    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        if(crc.getUriInfo().getPath().endsWith("recipe") && crc.getMethod().equals("POST")){
            
        }
    }
    
}
