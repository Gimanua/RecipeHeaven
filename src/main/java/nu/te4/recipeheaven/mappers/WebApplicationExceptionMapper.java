package nu.te4.recipeheaven.mappers;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Maps WebApplicationException instances to Responses.
 * Used by Jax-RS, primarily to control the logging of the exceptions.
 * @author Adrian Klasson
 */
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebApplicationExceptionMapper.class);
    
    @Override
    public Response toResponse(WebApplicationException exception) {
        int status = exception.getResponse().getStatus();
        if(status >= 500){
            LOGGER.error(exception.getMessage());
        }
        else{
            LOGGER.debug(exception.getMessage());
        }
        return exception.getResponse();
    }

}