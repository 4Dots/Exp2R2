/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.plus.*;
import com.google.api.services.plus.model.PeopleFeed;
import com.google.api.services.plus.model.Person;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author estudiante
 */
@Path("gFriends")
public class GFriendsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GFriendsResource
     */
    public GFriendsResource() {
    }

    /**
     * Retrieves representation of an instance of Rest.GFriendsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson(@QueryParam("token") String token) {
        //TODO
        HttpTransport httpTransport = new UrlFetchTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        
        Plus plus = new Plus.Builder(httpTransport, jsonFactory, null).setApplicationName("")
                .setGoogleClientRequestInitializer(new PlusRequestInitializer("AIzaSyDvj08mJ-7RHFAO7dT5d7we0NxPuZjtidA")).build();
        
        String res = "";
        
        
        
        try 
        {
            PeopleFeed friends = plus.people().list(token, "visible").execute();
            List<Person> friendsList = friends.getItems();
            for (int i = 0; i < friendsList.size(); i++)
            {
                Person p = friendsList.get(i);
                res += p.getDisplayName() + "\n";
            }
        } catch (IOException ex) {
            res = "nope";
        }
        
        return res;
    }

    /**
     * PUT method for updating or creating an instance of GFriendsResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
