/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Bibliografia;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Alunoinf_2
 */
public class LivroFormTest {
    
   
    /**
     * Test of getBibliografiaTemp method, of class LivroForm.
     */
    @Test
    public void testGetBibliografiaTemp() {
        
        LivroForm instance = new LivroForm();
        Bibliografia expResult = new Bibliografia();
        instance.setBibliografiaTemp(expResult);
        Bibliografia result = instance.getBibliografiaTemp();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setBibliografiaTemp method, of class LivroForm.
     */
    @Test
    public void testSetBibliografiaTemp() {
       
        Bibliografia bibliografiaTemp = new Bibliografia();
        LivroForm instance = new LivroForm();
        instance.setBibliografiaTemp(bibliografiaTemp);
       
    }
}
