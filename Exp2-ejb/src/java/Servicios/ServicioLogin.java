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
    
    //Atributos
    private Usuario u;
    
    private static Facebook fb;
    
    private static ServicioLogin servicioLogin;
    
    private static ServicioLikes servicioLikes;
    
    //Constructor
    
    public ServicioLogin()
    {
        
    }
    
    public static ServicioLogin getInstance(){
        if(servicioLogin == null){
            servicioLogin = new ServicioLogin();
        }
        return servicioLogin;
    }
    
    //Metodos
    
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
       
        try{
            ArrayList<LikeU> nlik = new ArrayList();
            ResponseList<Like> likes =  facebook.getUserLikes();
            System.out.println("Tamaño Likes: "+likes.size());
            for (int i = 0; i < likes.size(); i++)
            {
               Like l = likes.get(i);
            //   if (l.getCategory().equals("Clothing"))
               {
                   System.out.println("Likes en ServicioLogin: "+l.getName());
                   LikeU nl = new LikeU(l.getName(), l.getCategory());
                   nlik.add(nl);
               }
            }
            
            User me = facebook.getMe();
            
            ResponseList<Friend> amigos =  facebook.getFriends();
            ArrayList<Friend> am = (ArrayList<Friend>) amigos;
            
            u = new Usuario(nlik, me.getId(), me.getName(), am);
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
        fb = facebook;
        return facebook;
    }
    
    public Usuario getUser()
    {   
        System.out.println("Usuario en ServicioLogin2: " +u);
        return buscarUsuario();
    }
    
    public void cambiarUsuario(Usuario usu){
        u = usu;
    }
    
    public Usuario conseguirUsuario(){
        return u;
    }
    
    public Usuario buscarUsuario(){
        Usuario usu = null;
        ArrayList<LikeU> nlik = new ArrayList();
        
        try{
            ResponseList<Like> likes =  fb.getUserLikes();
            System.out.println("Tamaño Likes: "+likes.size());
            for (int i = 0; i < likes.size(); i++)
            {
               Like l = likes.get(i);
               if (l.getCategory().contains("Clothing"))
               {
                   System.out.println("Likes en ServicioLogin: "+l.getName());
                   LikeU nl = new LikeU(l.getName(), l.getCategory());
                   nlik.add(nl);
                   servicioLikes.getInstanceSL().agregarTienda(l.getName());
               }
            }
            
            User me = fb.getMe();
            
            ResponseList<Friend> amigos =  fb.getFriends();
            ArrayList<Friend> am = (ArrayList<Friend>) amigos;
            
            ServicioPersistenciaSql sql = new ServicioPersistenciaSql();
            
            
            usu = new Usuario(nlik, me.getId(), me.getName(), am);
            sql.create(usu);
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }
            
        return usu;    
    }
}
