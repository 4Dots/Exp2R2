/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bos;

import Servicios.ServicioPersistenciaNoSql;
import facebook4j.Friend;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

/**
 *
 * @author estudiante
 */
@Entity
@NamedQueries(
        @NamedQuery(name = "Usuario.findAll", query = "select o from Usuario o"))
public class Usuario
{

    //Atributos
    @Id
    // @Column(name="USU_ID")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUA")
    //@SequenceGenerator(name = "SEQ_USUA", allocationSize = 1, sequenceName = "usuarios_seq")
    private String ID;

    private String name;

    //private String pass;

    private ArrayList<Friend> amigosEnApp;

    private String tipo;

    //@OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL})
    private ArrayList<Usuario> amigos;

    @OneToMany(mappedBy = "usuario", cascade =
    {
        CascadeType.ALL
    })
    private ArrayList<LikeU> likes;

    @OneToMany(mappedBy = "usuario", cascade =
    {
        CascadeType.ALL
    })
    private List<Tienda> tLikes;

    @Transient
    private ArrayList<Bono> bonosComprados;

    @Transient
    private ServicioPersistenciaNoSql spnsql;
    

//Constructor
    public Usuario()
    {

    }

    public Usuario(ArrayList<LikeU> likes, String ID, String name, ArrayList<Friend> nAmigosEnApp)
    {
        spnsql = new ServicioPersistenciaNoSql();
        this.likes = likes;
        this.ID = ID;
        this.name = name;
        this.amigosEnApp = nAmigosEnApp;
        this.bonosComprados = new ArrayList();
    }

    //Metodos
    public ArrayList<LikeU> getLikes()
    {
        return likes;
    }

    public void setLikes(ArrayList<LikeU> likes)
    {
        this.likes = likes;
    }

    public String getID()
    {
        return ID;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public ArrayList<Friend> getAmigosEnApp()
    {
        return amigosEnApp;
    }

    public void setAmigosEnApp(ArrayList<Friend> amigosEnApp)
    {
        this.amigosEnApp = amigosEnApp;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void agregarBono(Bono bono)
    {
        
        try
        {
            spnsql.create(bono);
        }
        catch (Exception ex)
        {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
