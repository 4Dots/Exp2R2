/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Beans.LoginBean;
import Beans.Metodos;
import bos.Usuario;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author estudiante
 */
public class MetodosTest {
    
    public MetodosTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void darLikesTest(){
        Metodos met = new Metodos();
        ArrayList likes = met.buscarLikesFB("10152676512966253");
        
        for(int i = 0; i < likes.size(); i++){
            String l = (String)likes.get(i);
            System.out.println("Nombre: "+l + " Pos: " + i);
        }
    }
    
    @Test
    public void darAmigosTest(){
        Metodos met = new Metodos();
        ArrayList amigos = met.buscarAmigosFB("10152676512966253");
        
        for(int i = 0; i < amigos.size(); i++){
            String a = (String)amigos.get(i);
            System.out.println("Nombre Amigo: "+a + " Pos: " + i);
        }
    }
    
    @Test
    public void verificarUsuario(){
        Metodos met = new Metodos();
        ArrayList amigos = met.buscarAmigosFB("10152676512966253");
        ArrayList likes = met.buscarLikesFB("10152676512966253");
        String nombre = "Sebastian Salas Movilla";
        
        Usuario usu = new Usuario(likes, "10152676512966253", nombre, amigos, true);
        met.guardarUsuario(usu);
        assertNotNull(LoginBean.getInstance().conseguirUsuario());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
