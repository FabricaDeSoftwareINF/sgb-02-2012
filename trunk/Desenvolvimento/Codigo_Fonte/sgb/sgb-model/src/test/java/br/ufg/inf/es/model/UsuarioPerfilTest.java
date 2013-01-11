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
public class UsuarioPerfilTest {
   

    /**
     * Test of getIdUsuario method, of class UsuarioPerfil.
     */
    @Test
    public void testGetIdUsuario() {
       
        UsuarioPerfil instance = new UsuarioPerfil();
        long expResult = 1L;
        instance.setIdUsuario(expResult);
        long result = instance.getIdUsuario();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setIdUsuario method, of class UsuarioPerfil.
     */
    @Test
    public void testSetIdUsuario() {
        
        long idUsuario = 2L;
        UsuarioPerfil instance = new UsuarioPerfil();
        instance.setIdUsuario(idUsuario);
        long result = instance.getIdUsuario();
        assertEquals(idUsuario, result);
    }

    /**
     * Test of getIdPerfil method, of class UsuarioPerfil.
     */
    @Test
    public void testGetIdPerfil() {
       
        UsuarioPerfil instance = new UsuarioPerfil();
        long expResult = 3L;
        instance.setIdPerfil(expResult);
        long result = instance.getIdPerfil();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setIdPerfil method, of class UsuarioPerfil.
     */
    @Test
    public void testSetIdPerfil() {
        
        long idPerfil = 0L;
        UsuarioPerfil instance = new UsuarioPerfil();
        instance.setIdPerfil(idPerfil);
        long result = instance.getIdPerfil();
        assertEquals(idPerfil, result);
       
    }
}
