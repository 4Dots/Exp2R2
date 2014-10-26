/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author estudiante
 */
@Entity
public class LikeU {
    
    //Atributos
    
    @Id
    //@Column(name="LIKE_ID")
    @ManyToOne(optional=false)
    private String name;

    //Constructor
    
    public LikeU(){
        
    }
    
    public LikeU(String name, String category){
        this.name = name;
        this.category = category;
    }
    
    //Metodos
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    private String category;
    
}
