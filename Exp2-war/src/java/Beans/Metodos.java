/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import static Beans.LoginBean.getLoginBeanIns;
import Servicios.ServicioMail;
import Servicios.ServicioPersistenciaNoSql;
import static Servlets.MailServlet.ALG_SIM;
import bos.Bono;
import bos.Seguridad;
import bos.Tienda;
import bos.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author estudiante
 */
public class Metodos {
    
    public Metodos(){
        
    }
    
    public void enviarCorreo(String correo, String valor, String mensaje, String tiendaN){
        
        double valorD = Double.parseDouble(valor);
        String codBase = UUID.randomUUID().toString();
        
        byte[] codigo = null;
        
        SecretKey sk = new SecretKeySpec(Seguridad.key.getBytes(), ALG_SIM);
        try
        {
            Cipher cipher = Cipher.getInstance(ALG_SIM);
            cipher.init(Cipher.ENCRYPT_MODE, sk);
            codigo = cipher.doFinal(codBase.getBytes());
        }
        catch (Exception e)
        {
            
        }
        
        Usuario usu = LoginBean.getInstance().getUser();
        System.out.println(usu.getName());
        Tienda tienda = new Tienda(tiendaN);
        
        //Se crea el Bono
        Bono bono = new Bono(codigo, valorD, usu.getID(), tienda, usu.getName() );
        usu.agregarBono(bono);
        
        //Se manda el correo
        
        String mensajeBono = "Usted ha recibido un nuevo bono de parte de " + usu.getName() + " con valor de " + valorD + " y codigo "+codBase+ " para la tienda " + tiendaN;
        mensajeBono +=  "\n" + "Tambien le mandan adjunto este mensaje: " + mensaje;
        ServicioMail mail = new ServicioMail();
        mail.enviar(correo, mensajeBono );
        
        
    }
    
    public ArrayList verHistorial(){
        
         LoginBean lb = getLoginBeanIns();
        Usuario u = lb.buscarUsuario();
        
        ServicioPersistenciaNoSql spnsql = new ServicioPersistenciaNoSql();
        
        Bono bono = new Bono();
        List listaBonos = spnsql.findAll(bono.getClass());
        ArrayList lista = new ArrayList();
        
        for(int i = 0; i < listaBonos.size(); i++){
            
            Bono b = (Bono) listaBonos.get(i);
            if(b.getNombreComprador().equals(u.getName())){
                lista.add(b);
            }
        }
        
        return lista;
    }
    
    public void buscarAmigosEnGPlus(){
        
    }
    
}