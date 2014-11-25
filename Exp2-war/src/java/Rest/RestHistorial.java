/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Rest;

import Beans.Metodos;
import bos.Bono;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import com.google.gson.Gson;
/**
 * REST Web Service
 *
 * @author estudiante
 */
@Path("Historial")
public class RestHistorial {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestHistorial
     */
    public RestHistorial() {
    }

    /**
     * Retrieves representation of an instance of Rest.RestHistorial
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        Metodos met = new Metodos();
        ArrayList historialBns = met.verHistorial();
        Gson gson = new Gson();
        String json = gson.toJson(historialBns);
        return json;
    }

    /**
     * PUT method for updating or creating an instance of RestHistorial
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
