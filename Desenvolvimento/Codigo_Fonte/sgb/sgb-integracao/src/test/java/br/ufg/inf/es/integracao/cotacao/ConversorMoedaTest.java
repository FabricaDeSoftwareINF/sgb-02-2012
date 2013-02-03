/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.cotacao;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author usuario
 */
public class ConversorMoedaTest {
    
    

    /**
     * Test of ConverterDolarParaReal method, of class ConversorMoeda.
     */
    @Test
    public void testConverterDolarParaReal_String() {
       
        String dolar = "4.5";
        String result = ConversorMoeda.ConverterDolarParaReal(dolar);
        assertNotNull(result);
        
    }

    /**
     * Test of ConverterDolarParaReal method, of class ConversorMoeda.
     */
    @Test
    public void testConverterDolarParaReal_Double() {
        
        Double dolar = 4.5;
        Double result = ConversorMoeda.ConverterDolarParaReal(dolar);
        assertNotNull(result);
        
    }
}
