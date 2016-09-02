package co.be.api.generator.web.rest;

import co.be.api.generator.business.ApiManager;
import co.be.api.generator.business.exception.BusinessException;
import co.be.api.generator.web.exception.WebApiGeneratorException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Fernando Padilla Velez
 */
@Path("generator")
public class GeneratorREST { 

    private static final Logger LOG = Logger.getLogger(GeneratorREST.class.getName());
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String query(@QueryParam("id") int id, @QueryParam("params") String params){
        
        String respuesta = null;
        try{
            if(id <= 0) { 
                throw new WebApiGeneratorException("error: El id no es válido");
            } 
            ApiManager apiManager = new ApiManager();
            respuesta = apiManager.query(id, params);
        }catch(WebApiGeneratorException | BusinessException e){
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new WebApiGeneratorException(e.getMessage());
        }
        return respuesta;
    }
}