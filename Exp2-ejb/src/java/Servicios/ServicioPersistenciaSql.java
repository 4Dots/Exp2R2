/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import bos.LikeU;
import bos.Usuario;
import facebook4j.Friend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author estudiante
 */
public class ServicioPersistenciaSql {
    
    public ServicioPersistenciaSql(){
        
    }
    
    
    public void conectar() throws ClassNotFoundException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost/Prueba;create=true;user=salas;password=admin");
            conn.setSchema("SALAS");
            Statement s = conn.createStatement();
            s.executeQuery("SELECT * FROM USUARIO");
            
            ResultSet rs = s.getResultSet();
            System.out.println("Base de datos relacional");
            
            while(rs.next()){
                System.out.println("");
            }
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void desconectar()
    {
        
    }
    
    public void create(Object obj) throws ClassNotFoundException{
         Class.forName("org.apache.derby.jdbc.ClientDriver");
        
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost/Prueba;create=true;user=salas;password=admin");
            conn.setSchema("SALAS");
            Statement s = conn.createStatement();
           
            boolean existe = false;
            if(obj instanceof Usuario){
            Usuario u = (Usuario) obj;
            
            s.executeQuery("SELECT * FROM USUARIO");
            
            ResultSet rs = s.getResultSet();
            
            while(rs.next()){
                String nombre = rs.getString("ID");
                
                if(nombre.equals(u.getID())){
                    existe = true;
                }
            }
            if(!existe){
                
                String idUsu = u.getID();
                String nomUsu = u.getName();
                ArrayList<LikeU> likes = u.getLikes();
                ArrayList<Friend> amigos = u.getAmigosEnApp();
            
                s.executeUpdate("INSERT INTO USUARIO (NOMBRE, ID) VALUES ('"+nomUsu+"','"+idUsu+"')");
            
                    for(int i=0; i < likes.size(); i++){
                
                     LikeU l = likes.get(i);    
                    s.executeUpdate("INSERT INTO USUARIO_LIKEU (USUARIO_ID, LIKES_NAME) VALUES ('"+idUsu+"','"+l.getName()+"')");
                
                    }
                
                    for(int j=0; j < amigos.size(); j++){
                   
                    
                    Friend f = amigos.get(j);    
                    s.executeUpdate("INSERT INTO USUARIO_USUARIO (USUARIO_ID, AMIGOS_ID) VALUES ('"+idUsu+"','"+f.getId()+"')");
                
                    }
                }
            }
            
      
            conn.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public ArrayList buscarAmigosPorId(String id)throws ClassNotFoundException{
        
        ArrayList amigos = new ArrayList();
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost/Prueba;create=true;user=salas;password=admin");
            conn.setSchema("SALAS");
            Statement s = conn.createStatement();
           
            s.executeQuery("SELECT * FROM USUARIO_USUARIO JOIN USUARIO ON USUARIO.ID = USUARIO_USUARIO.AMIGOS_ID WHERE USUARIO_ID='"+id+"'");
            
            ResultSet rs = s.getResultSet();
            
            while(rs.next()){
                String nombreAmigo = rs.getString("NOMBRE");
                
                amigos.add(nombreAmigo);
                
            }
            conn.close();
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return amigos;
    }
    
    
    public ArrayList buscarLikesPorId(String id)throws ClassNotFoundException{
        
        ArrayList likes = new ArrayList();
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        
        try{
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost/Prueba;create=true;user=salas;password=admin");
            conn.setSchema("SALAS");
            Statement s = conn.createStatement();
           
            s.executeQuery("SELECT * FROM USUARIO_LIKEU WHERE USUARIO_ID='"+id+"'");
            
            ResultSet rs = s.getResultSet();
            
            while(rs.next()){
                String nombreTienda = rs.getString("LIKES_NAME");
                
                likes.add(nombreTienda);
                
            }
            conn.close();
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return likes;
    }
}
