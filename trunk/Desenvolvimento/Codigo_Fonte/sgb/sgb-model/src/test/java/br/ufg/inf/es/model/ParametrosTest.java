/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author alunoufg
 */
public class ParametrosTest {
   

    /**
     * Test of getParametroMEC method, of class Parametros.
     */
    @Test
    public void testGetParametroMEC() {
        
        Parametros instance = new Parametros();
        instance.setParametroMEC(10);
        Integer expResult = 10;
        Integer result = instance.getParametroMEC();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setParametroMEC method, of class Parametros.
     */
    @Test
    public void testSetParametroMEC() {
       
        
        Parametros instance = new Parametros();
        instance.setParametroMEC(10);
        Integer expResult = 10;
        Integer result = instance.getParametroMEC();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValorFrete method, of class Parametros.
     */
    @Test
    public void testGetValorFrete() {
       
        Parametros instance = new Parametros();
        BigDecimal expResult = new BigDecimal(1);
        instance.setValorFrete(expResult);
        BigDecimal result = instance.getValorFrete();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setValorFrete method, of class Parametros.
     */
    @Test
    public void testSetValorFrete() {
        System.out.println("setValorFrete");
        BigDecimal valorFrete = new BigDecimal(1);
        Parametros instance = new Parametros();
       instance.setValorFrete(valorFrete);
        BigDecimal result = instance.getValorFrete();
        assertEquals(valorFrete, result);
    }
}
