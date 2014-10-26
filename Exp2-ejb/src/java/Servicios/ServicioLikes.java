/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servicios;

import java.util.ArrayList;
import javax.ejb.Stateful;

/**
 *
 * @author estudiante
 */
@Stateful
public class ServicioLikes {
    
    //Atributos
    
    public ArrayList tiendas;

    public static ServicioLikes servicioLikes;
    //Constructor
    
    public ServicioLikes(){
        tiendas = new ArrayList();
    }
    
    //Metodos
    
    public ArrayList getTiendas() {
        return tiendas;
    }

    public void setTiendas(ArrayList tiendas) {
        this.tiendas = tiendas;
    }
    
    public void agregarTienda (String pTienda){
        
        boolean hay = false;
        for(int i = 0; i < tiendas.size(); i++){
            
            String nombre = (String)tiendas.get(i);
            
            if(pTienda.equals(nombre)){
                hay = true;
            }
        }
        if(hay){
            tiendas.add(pTienda);
            System.out.println("Se agrego");
        }
    }
    
    public static ServicioLikes getInstanceSL(){
        if(servicioLikes==null){
            servicioLikes = new ServicioLikes();
        }
        return servicioLikes;
    }
    
    
}
