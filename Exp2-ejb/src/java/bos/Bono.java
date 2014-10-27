/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author estudiante
 */
@Entity
@NamedQueries(@NamedQuery(name="Bono.findAll", query="select o from Bono o"))
public class Bono {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BONO")
    @SequenceGenerator(name = "SEQ_BONO", allocationSize = 1, sequenceName = "bonos_seq")
    private long id;
    
    private String codigo;
    
    private boolean reclamado;
    
    private double valor;
    
    private Usuario comprador;
    
    private Usuario dueño;
    
    private Tienda tienda;
    
    public Bono(String codigo, double valor, Usuario comprador, Tienda tienda){
        
        reclamado = false;
        this.valor= valor;
        this.comprador = comprador;
        this.tienda = tienda;
        //this.dueño= dueño;
    }
}
