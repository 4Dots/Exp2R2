/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import bos.LikeU;
import bos.Usuario;
import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.Friend;
import facebook4j.Like;
import facebook4j.ResponseList;
import facebook4j.User;
import facebook4j.internal.http.HttpRequest;
import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.Stateful;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author estudiante
 */
@Stateful
public class ServicioLogin {
    
    private Usuario u;
    
    public ServicioLogin()
    {
        
    }
    
    
    public Facebook login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Facebook facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId("1465534577042635", "a54082448bf7322634973a7ebb6b4851");
        facebook.setOAuthPermissions("public_profile,user_likes,user_friends");
        
        request.getSession().setAttribute("facebook", facebook);
        StringBuffer callbackURL = request.getRequestURL();
        int index = callbackURL.lastIndexOf("/");
        callbackURL.replace(index, callbackURL.length(), "").append("/callback");
        response.sendRedirect(facebook.getOAuthAuthorizationURL(callbackURL.toString()));
        
//        try{
//            ArrayList<LikeU> nlik = new ArrayList();
//            ResponseList<Like> likes =  facebook.getUserLikes();
//            for (int i = 0; i < likes.size(); i++)
//            {
//               Like l = likes.get(i);
//               if (l.getCategory().equals("Clothing"))
//               {
//                   LikeU nl = new LikeU(l.getName(), l.getCategory());
//                   nlik.add(nl);
//               }
//            }
//            
//            User me = facebook.getMe();
//            
//            ResponseList<Friend> amigos =  facebook.getFriends();
//            ArrayList<Friend> am = (ArrayList<Friend>) amigos;
//            
//            u = new Usuario(nlik, me.getId(), me.getName(), am);
//        }
//        catch(Exception e)
//        {
//           e.printStackTrace();
//        }
        return facebook;
    }
    
    public Usuario getUser()
    {
        return u;
    }
    
}
