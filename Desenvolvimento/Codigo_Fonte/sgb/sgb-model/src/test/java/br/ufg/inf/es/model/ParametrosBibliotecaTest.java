/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import java.math.BigDecimal;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author alunoufg
 */
public class ParametrosBibliotecaTest {
    
    

    /**
     * Test of getValorFrete method, of class ParametrosBiblioteca.
     */
    @Test
    public void testGetValorFrete() {
       
        ParametrosBiblioteca instance = new ParametrosBiblioteca();
        instance.setValorFrete(BigDecimal.ONE);
        BigDecimal expResult = new BigDecimal(1);
        BigDecimal result = instance.getValorFrete();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setValorFrete method, of class ParametrosBiblioteca.
     */
    @Test
    public void testSetValorFrete() {
        
        BigDecimal valorFrete = BigDecimal.ONE;
        ParametrosBiblioteca instance = new ParametrosBiblioteca();
        instance.setValorFrete(valorFrete);
        assertEquals(valorFrete, instance.getValorFrete());
    }

    /**
     * Test of equals method, of class ParametrosBiblioteca.
     */
    @Test
    public void testEquals() {
      
        ParametrosBiblioteca obj = new ParametrosBiblioteca();
        obj.setValorFrete(BigDecimal.ZERO);
        ParametrosBiblioteca instance = new ParametrosBiblioteca();
        instance.setValorFrete(BigDecimal.ZERO);
        
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of hashCode method, of class ParametrosBiblioteca.
     */
    @Test
    public void testHashCode() {
       
        ParametrosBiblioteca instance = new ParametrosBiblioteca();
        instance.setValorFrete(BigDecimal.ZERO);
        
        int result = instance.hashCode();
        assertTrue(result>0);
       
    }
}
