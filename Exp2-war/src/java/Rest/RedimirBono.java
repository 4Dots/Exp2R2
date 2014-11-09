/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Rest;

import Servicios.ServicioPersistenciaNoSql;
import bos.Bono;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author estudiante
 */
@Path("redimirBono")
public class RedimirBono {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RedimirBono
     */
    public RedimirBono() {
    }

    /**
     * Retrieves representation of an instance of Rest.RedimirBono
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson(@QueryParam("code") String code ) {
        
        //TODO Buscar bono, return true or false
        
        ServicioPersistenciaNoSql serv = new ServicioPersistenciaNoSql();
        
        List bonos =  serv.findAll(Bono.class);
        
        for (int i = 0; i < bonos.size(); i++)
        {
            Bono b = (Bono) bonos.get(i);
            
            if (b.getCodigo().equals(code))
            {
                return "naiz";
            }
        }
        return "false";
    }

    /**
     * PUT method for updating or creating an instance of RedimirBono
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
