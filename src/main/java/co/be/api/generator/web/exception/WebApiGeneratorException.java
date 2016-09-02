package co.be.api.generator.web.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Fernando Padilla
 */
public class WebApiGeneratorException extends WebApplicationException{
    
    public WebApiGeneratorException(String message) {
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity(message).type(MediaType.TEXT_PLAIN).build());
    }
}
