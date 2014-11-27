/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Rest;

import Beans.LoginBean;
import Beans.Metodos;
import bos.Usuario;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public String getJson(@QueryParam("id") String id, @QueryParam("nombre") String nombre, @QueryParam("ref") String ref, @QueryParam("token") String token) {
        //TODO return proper representation object
        
        if (ref.equals("fb"))
        {
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
            
            String res = "[";
            
            for (int i = 0; i < likes.size(); i++)
            {
                res += "\"\"" + likes.get(i) + "\"\"";
                if (i < likes.size()-1)
                    res += ",";
            }
            res += "]";
            return res;
        }
        else if (ref.equals("g"))
        {
            String url = "http://www.google.com/search?q=mkyong";
            
            URL obj;
            try 
            {
                obj = new URL(url);
                 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            
            // optional default is GET
            con.setRequestMethod("GET");
            
            //add request header
            //con.setRequestProperty("User-Agent", USER_AGENT);
            
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            //print result
            return response.toString();
            } catch (Exception ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
        return "ERR";
        
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
