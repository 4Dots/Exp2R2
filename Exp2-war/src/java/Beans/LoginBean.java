/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import Servicios.ServicioLogin;
import bos.Usuario;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.User;
import facebook4j.internal.http.HttpRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author estudiante
 */
@Stateful
@LocalBean
public class LoginBean implements Serializable{
    
    protected Usuario u;

    protected User user;

    protected Facebook fb;
    
    private ServicioLogin servicio;
    protected String asd;

    public String getAsd() {
        try {
            asd = fb.getName();
            return asd;
        } catch (Exception ex) {
           ex.printStackTrace();
        } 
        return "nope";
    }

    public void setAsd(String asd) {
        this.asd = asd;
    }
    
    public LoginBean()
    {
        servicio = new ServicioLogin();                
    }
    
    public Facebook login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FacebookException
    {
        fb = servicio.login(request, response);
        //user = fb.getMe();
        //asd = "huehue";
        //u = servicio.getUser();
        return fb;
    }
    
    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
