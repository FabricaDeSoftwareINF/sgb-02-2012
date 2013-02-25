/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.base.validation;

import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author usuario
 */
public class MultipleValidationExceptionTest extends TestCase{
    
 

    /**
     * Test of keysMessage method, of class MultipleValidationException.
     */
    @Test
    public void testKeysMessage() {
        String [] msg = new String[2];
        msg[0]="Erro 1";
        msg[1]="Erro 2";
        MultipleValidationException instance = new MultipleValidationException(msg);
        
        
        String[] result = instance.keysMessage();
        assertEquals(msg[0], result[0]);
        assertEquals(msg[1], result[1]);
       
    }
}
