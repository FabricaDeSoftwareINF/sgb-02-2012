/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.model.Usuario;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.SessionFactory;
import org.hibernate.impl.SessionImpl;
import org.junit.*;
import org.mockito.Mockito;
import static org.junit.Assert.*;

/**
 *
 * @author alunoufg
 */
public class UsuarioDAOTest {
    

    
    @Test
    public void testGetSessionFactory() {
        SessionFactory sessionF =  Mockito.mock(SessionFactory.class );
       
        UsuarioDAO instance = new UsuarioDAO(sessionF);
       
        
        SessionFactory result = instance.getSessionFactory();
        assertEquals(sessionF, result);
        
    }

    @Test
    public void testFindUserByEmail() {
       
        String user = "a";
        Session session = Mockito.mock(Session.class);
        SessionFactory factory = Mockito.mock(SessionFactory.class);
        Mockito.when(factory.openSession()).thenReturn(session);
        
        Criteria criteria = Mockito.mock(Criteria.class);
        
        Mockito.when(session.createCriteria(UsuarioDAO.class)).thenReturn(criteria);
        UsuarioDAO a = new UsuarioDAO(factory);
        Mockito.when(a.createCriteria()).thenReturn(criteria);
        
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        
        Mockito.when(criteria.uniqueResult()).thenReturn(usuario);
        Mockito.when(a.getSession()).thenReturn(session);
        
        Usuario usuarioEcontrado =  a.findUserByEmail("a");
        assertEquals(usuario.getId(), usuarioEcontrado.getId());
       
    }
    @Test
    public void testFindUserByEmail2() {
       
        String user = "a";
        UsuarioDAO instance = Mockito.mock(UsuarioDAO.class);
        Usuario expResult = new Usuario();
        expResult.setId(1L);
        Mockito.when(instance.findUserByEmail(user)).thenReturn(expResult);
        
        Usuario result = instance.findUserByEmail(user);
        assertEquals(expResult.getId(), result.getId());
       
    }

    /**
     * Test of findUserByEmailAndPassword method, of class UsuarioDAO.
     */
    @Test
    public void testFindUserByEmailAndPassword() {
      
        String user = "a";
        String password = "123";
        UsuarioDAO instance = Mockito.mock(UsuarioDAO.class);
        Usuario expResult = new Usuario();
        expResult.setNome(user);
        expResult.setSenha(password);
        Mockito.when(instance.findUserByEmailAndPassword(user, password)).thenReturn(expResult);
        Usuario result = instance.findUserByEmailAndPassword(user, password);
        assertEquals(expResult, result);
        
    }
}
