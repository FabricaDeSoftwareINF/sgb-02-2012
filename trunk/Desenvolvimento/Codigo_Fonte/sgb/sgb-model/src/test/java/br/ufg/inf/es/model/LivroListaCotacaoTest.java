/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author usuario
 */
public class LivroListaCotacaoTest {
    
   
    /**
     * Test of getLivro method, of class LivroListaCotacao.
     */
    @Test
    public void testGetLivro() {
        ItemListaCompras instance = new ItemListaCompras();
        Livro expResult = new Livro();
        expResult.setTitulo("A");
        instance.setLivro(expResult);
        Livro result = instance.getLivro();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setLivro method, of class LivroListaCotacao.
     */
    @Test
    public void testSetLivro() {
        Livro livro = new Livro();
        livro.setTitulo("A");
        
        ItemListaCompras instance = new ItemListaCompras();
        instance.setLivro(livro);
        assertNotNull(instance.getLivro());
    }

    /**
     * Test of getQuantidadeAComprar method, of class LivroListaCotacao.
     */
    @Test
    public void testGetQuantidadeAComprar() {
        ItemListaCompras instance = new ItemListaCompras();
        instance.setQuantidadeAComprar(2);
        int expResult = 2;
        int result = instance.getQuantidadeAComprar();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setQuantidadeAComprar method, of class LivroListaCotacao.
     */
    @Test
    public void testSetQuantidadeAComprar() {
        int quantidadeAComprar = 1;
        ItemListaCompras instance = new ItemListaCompras();
        instance.setQuantidadeAComprar(quantidadeAComprar);
        assertEquals(quantidadeAComprar, instance.getQuantidadeAComprar());
        
    }
}
