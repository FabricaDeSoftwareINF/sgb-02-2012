/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.base.validation;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author usuario
 */
public class ValidationExceptionTest {
    
  

    /**
     * Test of getKeyMessage method, of class ValidationException.
     */
    @Test
    public void testGetKeyMessage() {
        ValidationException instance = new ValidationException("teste");
        String expResult = "teste";
        String result = instance.getKeyMessage();
        assertEquals(expResult, result);
        
    }
}
