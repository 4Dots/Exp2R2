/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Rest;

import Beans.LoginBean;
import Beans.Metodos;
import Servicios.ServicioMail;
import static Servlets.MailServlet.ALG_SIM;
import bos.Bono;
import bos.Seguridad;
import bos.Tienda;
import bos.Usuario;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
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
@Path("MandarCorreo")
public class RestMandarCorreo {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestMandarCorreo
     */
    public RestMandarCorreo() {
    }

    /**
     * Retrieves representation of an instance of Rest.RestMandarCorreo
     * @return an instance of Servicios.ServicioMail
     */
    @GET
    @Produces("application/json")
    public String getJson(@QueryParam("email") String email, @QueryParam("valor") String valor, @QueryParam("mensaje") String mensaje, @QueryParam("tienda") String tiendaN ) {
        //TODO return proper representation object
        
        Metodos met = new Metodos();
        met.enviarCorreo(email, valor,mensaje, tiendaN);
        return "enviado";
    }

    /**
     * PUT method for updating or creating an instance of RestMandarCorreo
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(ServicioMail content) {
    }
}
