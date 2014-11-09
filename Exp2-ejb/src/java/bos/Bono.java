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
@NamedQueries(
        @NamedQuery(name = "Bono.findAll", query = "select o from Bono o"))
public class Bono
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BONO")
    @SequenceGenerator(name = "SEQ_BONO", allocationSize = 1, sequenceName = "bonos_seq")
    private long id;

    private byte[] codigo;

    private boolean reclamado;

    private double valor;

    private String IDcomprador;
    
    private String nombreComprador;

    private Tienda tienda;

    public Bono(byte [] codigo, double valor, String comprador, Tienda tienda, String nombre)
    {

        reclamado = false;
        this.valor = valor;
        this.IDcomprador = comprador;
        this.nombreComprador = nombre;
        this.tienda = tienda;
        this.codigo = codigo;
        //this.dueño= dueño;
    }
    
    public Bono ()
    {
        
    }

    /**
     * @return the id
     */
    public long getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * @return the codigo
     */
    public byte [] getCodigo()
    {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(byte [] codigo)
    {
        this.codigo = codigo;
    }

    /**
     * @return the reclamado
     */
    public boolean isReclamado()
    {
        return reclamado;
    }

    /**
     * @param reclamado the reclamado to set
     */
    public void setReclamado(boolean reclamado)
    {
        this.reclamado = reclamado;
    }

    /**
     * @return the valor
     */
    public double getValor()
    {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor)
    {
        this.valor = valor;
    }

    /**
     * @return the tienda
     */
    public Tienda getTienda()
    {
        return tienda;
    }

    /**
     * @param tienda the tienda to set
     */
    public void setTienda(Tienda tienda)
    {
        this.tienda = tienda;
    }

    public String getIDcomprador()
    {
        return IDcomprador;
    }

    public void setIDcomprador(String IDcomprador)
    {
        this.IDcomprador = IDcomprador;
    }

    public String getNombreComprador()
    {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador)
    {
        this.nombreComprador = nombreComprador;
    }
}
