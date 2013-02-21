package br.ufg.inf.es.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Victor Carvalho
 */
public class CotacaoTest {

    private Cotacao cotacao;
    private double valor = 10;
    private Livraria livraria = new Livraria();

    @Before
    public void setUp() {
        cotacao = new Cotacao();
        cotacao.setValor(valor);
        cotacao.setLivraria(livraria);
    }

    /**
     * Test of getValor method, of class Cotacao.
     */
    @Test
    public void testGetValor() {
        assertEquals(valor, cotacao.getValor(), 0.0);
    }

    /**
     * Test of getLivraria method, of class Cotacao.
     */
    @Test
    public void testGetLivraria() {
        assertEquals(livraria, cotacao.getLivraria());
    }

    @Test
    public void testHashCode() {
        assertTrue(cotacao.hashCode() != 0);
    }

    @Test
    public void testHashCodeLivrariaNull() {
        cotacao.setLivraria(null);
        assertTrue(cotacao.hashCode() != 0);
    }

    @Test
    public void testEquals() {
        assertFalse(cotacao.equals(null));
        assertFalse(cotacao.equals(""));
        assertFalse(cotacao.equals(new Cotacao()));
        
        Cotacao c = new Cotacao();
        c.setValor(valor);
        assertFalse(cotacao.equals(c));
        
        Cotacao c2 = new Cotacao();
        Livraria a = new Livraria();
        a.setId(1L);
        a.setNome("a");
        c2.setLivraria(a);
        c2.setValor(valor);
        assertFalse(cotacao.equals(c2));
        
        Cotacao c5 = new Cotacao();
        c5.setValor(valor);
        c5.setLivraria(livraria);
        assertTrue(cotacao.equals(c5));
        
        
    }
}