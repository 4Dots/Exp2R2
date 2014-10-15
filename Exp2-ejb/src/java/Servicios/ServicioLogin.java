/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import javax.ejb.Stateful;

/**
 *
 * @author estudiante
 */
@Stateful
public class ServicioLogin {
    
    public ServicioLogin()
    {
        
    }
    
    
    public Facebook login()
    {
        Facebook facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId("1465534577042635", "a54082448bf7322634973a7ebb6b4851");
        facebook.setOAuthPermissions("public_profile,user_likes,user_friends");
        return facebook;
    }
    
}
