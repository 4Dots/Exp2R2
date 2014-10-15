/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bos;

import facebook4j.Friend;
import java.util.ArrayList;

/**
 *
 * @author estudiante
 */
public class Usuario {
    
    private ArrayList<LikeU> likes;
    private String ID;
    private String name;
    private ArrayList<Friend> amigosEnApp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Usuario(){
        
    }
    
    public Usuario(ArrayList<LikeU> likes, String ID, String name, ArrayList<Friend> nAmigosEnApp){
       this.likes = likes;
       this.ID = ID;
       this.name = name;
       this.amigosEnApp = nAmigosEnApp;
    }
    
    public ArrayList<LikeU> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<LikeU> likes) {
        this.likes = likes;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    
    
    
}
