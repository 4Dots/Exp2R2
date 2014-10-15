/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import Servicios.ServicioLogin;
import facebook4j.Facebook;

/**
 *
 * @author estudiante
 */
public class LoginBean {
    private ServicioLogin servicio;
    
    public LoginBean()
    {
        servicio = new ServicioLogin();                
    }
    
    public Facebook login()
    {
        return servicio.login();
    }
}
