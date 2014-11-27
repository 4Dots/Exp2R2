/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Rest;

import Beans.LoginBean;
import Beans.Metodos;
import bos.Usuario;
import java.util.ArrayList;
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
@Path("login")
public class Login {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Login
     */
    public Login() {
    }

    /**
     * Retrieves representation of an instance of Rest.Login
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson(@QueryParam("id") String id, @QueryParam("nombre") String nombre) {
        //TODO return proper representation object
        System.out.println("ID: "+ id);
        System.out.println("Nombre: " + nombre);
      
        Metodos met = new Metodos();
        ArrayList amigos =  met.buscarAmigosFB(id);
        ArrayList likes = met.buscarLikesFB(id);
        Usuario u = new Usuario(likes, id, nombre, amigos);
        met.guardarUsuario(u);
        Usuario usu = LoginBean.getInstance().conseguirUsuario();
        System.out.println("Usuario creado: "+usu.getID());
        System.out.println("Llego a REST");
        return "true";
    }

    /**
     * PUT method for updating or creating an instance of Login
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
