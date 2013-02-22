/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model.exportacaodados.planilha;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 *
 * @author Alunoinf_2
 */
public class PlanilhaTest {
    

    /**
     * Test of getLinhasPlanilha method, of class Planilha.
     */
    @Test
    public void testGetLinhasPlanilha() {
        Planilha instance = new Planilha();
        List expResult = new ArrayList();
        List result = instance.getLinhasPlanilha();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setLinhasPlanilha method, of class Planilha.
     */
    @Test
    public void testSetLinhasPlanilha() {
        ArrayList<ItemPlanilha> linhasPlanilha = new ArrayList<ItemPlanilha>();
        Planilha instance = new Planilha();
        instance.setLinhasPlanilha(linhasPlanilha);
        assertEquals(instance.getLinhasPlanilha(), linhasPlanilha);
    }

    /**
     * Test of getTituloCabecalho method, of class Planilha.
     */
    @Test
    public void testGetTituloCabecalho() {
        Planilha instance = new Planilha();
        String expResult = "aaa";
        instance.setTituloCabecalho(expResult);
        String result = instance.getTituloCabecalho();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTituloCabecalho method, of class Planilha.
     */
    @Test
    public void testSetTituloCabecalho() {
        
        String tituloCabecalho = "aa";
        Planilha instance = new Planilha();
        instance.setTituloCabecalho(tituloCabecalho);
        assertEquals(instance.getTituloCabecalho(), tituloCabecalho);
    }
}
