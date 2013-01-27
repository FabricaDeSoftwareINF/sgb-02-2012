/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author alunoufg
 */
public class PerfilTest {
    
   

    /**
     * Test of getTipo method, of class Perfil.
     */
    @Test
    public void testGetTipo() {
       
        Perfil instance = new Perfil("A");
        String expResult = "A";
        
        String result = instance.getTipo();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setTipo method, of class Perfil.
     */
    @Test
    public void testSetTipo() {
        
        String tipo = "A";
        Perfil instance = new Perfil();
        instance.setTipo(tipo);
         String result = instance.getTipo();
        assertEquals(tipo, result);
    }
}
