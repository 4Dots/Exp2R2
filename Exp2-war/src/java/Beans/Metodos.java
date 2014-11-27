/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Beans;

import static Beans.LoginBean.getLoginBeanIns;
import Servicios.IServicioPersistenciaLocal;
import Servicios.ServicioMail;
import Servicios.ServicioPersistenciaNoSql;
import Servicios.ServicioPersistenciaSql;
import static Servlets.MailServlet.ALG_SIM;
import bos.Bono;
import bos.Seguridad;
import bos.Tienda;
import bos.Usuario;
import facebook4j.Facebook;
import facebook4j.RawAPIResponse;
import facebook4j.auth.AccessToken;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        
        Usuario usu = LoginBean.getInstance().conseguirUsuario();
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
        
        //LoginBean lb = getLoginBeanIns();
        //Usuario u = lb.buscarUsuario();
        
        Usuario u = LoginBean.getInstance().conseguirUsuario();
        
        
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
   /* 
    private AccessToken refreshToken(Facebook facebook, AccessToken currentToken) throws Exception {
        
    ConfigurationBuilder confBuilder = new ConfigurationBuilder();

    confBuilder.setDebugEnabled(APIConfiguration.DEBUG_ENABLED);
    confBuilder.setOAuthAppId(APIConfiguration.APP_ID);
    confBuilder.setOAuthAppSecret(APIConfiguration.APP_SECRET);
    confBuilder.setUseSSL(APIConfiguration.USE_SSL);
    confBuilder.setJSONStoreEnabled(APIConfiguration.JSON_STORE_ENABLED);


    Configuration configuration = confBuilder.build();
        
    String clientId = configuration.getString(ConfigurationKeys.SOCIAL_FACEBOOK_CLIENTID);
    String clientSecret = configuration.getString(ConfigurationKeys.SOCIAL_FACEBOOK_CLIENTSECRET);

    Map<String, String> params = new HashMap<String, String>();
    params.put("client_id", clientId);
    params.put("client_secret", clientSecret);
    params.put("grant_type", "fb_exchange_token");
    params.put("fb_exchange_token", currentToken.getToken());

    RawAPIResponse apiResponse = facebook.callGetAPI("/oauth/access_token", params);

    String response = apiResponse.asString();
    AccessToken newAccessToken = new AccessToken(response);

    facebook.setOAuthAccessToken(newAccessToken);

    return newAccessToken;
}
    */
    
    public ArrayList buscarAmigosFB(String ID){
        ArrayList amigos = new ArrayList();
        ServicioPersistenciaSql sql = new ServicioPersistenciaSql();
        try{
        amigos = sql.buscarAmigosPorId(ID);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return amigos;
        
    }
    
    public ArrayList buscarLikesFB(String ID){
        ArrayList likes = new ArrayList();
        ServicioPersistenciaSql sql = new ServicioPersistenciaSql();
        try{
        likes = sql.buscarLikesPorId(ID);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return likes;
        
    }
    
    public void guardarUsuario(Usuario u){
        LoginBean.getInstance().cambiarUsuario(u);
    }
}
