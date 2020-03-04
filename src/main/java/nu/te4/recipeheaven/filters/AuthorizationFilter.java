/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.filters;

import java.io.IOException;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import nu.te4.recipeheaven.beans.GitHubOAuthBean;

/**
 *
 * @author Adrian Klasson
 */
@Provider
public class AuthorizationFilter implements ContainerRequestFilter{

    @EJB
    private GitHubOAuthBean gitHubOAuthBean;
    
    @Override
    public void filter(ContainerRequestContext crc) throws IOException {
        String method = crc.getMethod();
        if(method.equals("POST") || method.equals("DELETE") || method.equals("PUT")){
            String token = crc.getHeaders().getFirst("token");
            if(!gitHubOAuthBean.verifyToken(token)){
                crc.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
    }
    
}
