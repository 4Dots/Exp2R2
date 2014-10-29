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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author estudiante
 */
@Entity
@NamedQueries(
        @NamedQuery(name = "Tienda.findAll", query = "select o from Tienda o"))
public class Tienda
{

    //Atributos
    @Id
    //@Column(name="TIENDA_ID")
    @ManyToOne(optional = false)
    private String nombre;

    //Constructor
    public Tienda(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
}
