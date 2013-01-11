/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia;

import org.hibernate.SessionFactory;
import org.junit.*;
import static org.junit.Assert.*;

import org.mockito.Mockito;
/**
 *
 * @author alunoufg
 */
public class PerfilDAOTest {
    
   

    @Test
    public void testGetSessionFactory() {
        SessionFactory sessionF =  Mockito.mock(SessionFactory.class );
        PerfilDAO instance = Mockito.mock(PerfilDAO.class);
        Mockito.when(instance.getSessionFactory()).thenReturn(sessionF);
        SessionFactory result = instance.getSessionFactory();
        assertEquals(sessionF, result);
        
    }

}
