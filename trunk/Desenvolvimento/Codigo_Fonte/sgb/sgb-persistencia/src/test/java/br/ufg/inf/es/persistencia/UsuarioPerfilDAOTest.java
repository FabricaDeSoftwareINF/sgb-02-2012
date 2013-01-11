/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Perfil;
import br.ufg.inf.es.model.UsuarioPerfil;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author alunoufg
 */
public class UsuarioPerfilDAOTest {
    
  
 
    @Test
    public void testGetSessionFactory() {
        SessionFactory sessionF =  Mockito.mock(SessionFactory.class );
       
        UsuarioPerfilDAO instance = Mockito.mock(UsuarioPerfilDAO.class );
        Mockito.when(instance.getSessionFactory()).thenReturn(sessionF);
        
        SessionFactory result = instance.getSessionFactory();
        assertEquals(sessionF, result);
        
    }

    /**
     * Test of list method, of class UsuarioPerfilDAO.
     */
    @Test
    public void testList() {
        
        long idUsuario = 1L;
        UsuarioPerfilDAO instance = Mockito.mock(UsuarioPerfilDAO.class);
        
        Collection<UsuarioPerfil> expResult = new ArrayList<UsuarioPerfil>();
        UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
        usuarioPerfil.setId(1L);
        usuarioPerfil.setIdPerfil(1L);
        usuarioPerfil.setIdUsuario(1L);
        expResult.add(usuarioPerfil);
        
        
        Mockito.when(instance.list(idUsuario)).thenReturn(expResult);
        
        Collection result = instance.list(idUsuario);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of listPerfis method, of class UsuarioPerfilDAO.
     */
    @Test
    public void testListPerfis() {
       
        long idUsuario = 0L;
        UsuarioPerfilDAO instance = Mockito.mock(UsuarioPerfilDAO.class);
       
        Collection<Perfil> expResult = new ArrayList<Perfil>();
        expResult.add(new Perfil("A"));
        Mockito.when(instance.listPerfis(idUsuario)).thenReturn(expResult);
        
        Collection result = instance.listPerfis(idUsuario);
        assertEquals(expResult, result);
       
    }
}
