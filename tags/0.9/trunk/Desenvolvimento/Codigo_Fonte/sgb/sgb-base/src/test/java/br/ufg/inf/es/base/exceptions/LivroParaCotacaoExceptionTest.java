/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.base.exceptions;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author usuario
 */
public class LivroParaCotacaoExceptionTest {
    
   

    
    @Test
    public void testSomeMethod() {
      LivroParaCotacaoException ex = new LivroParaCotacaoException("msg");
      assertEquals("msg",ex.getMessage());
    }
}
