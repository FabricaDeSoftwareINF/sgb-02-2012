package br.ufg.inf.es.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
    public void testHashCode(){
        assertTrue(cotacao.hashCode()!=0);
    }
    @Test
    public void testHashCodeLivrariaNull(){
        cotacao.setLivraria(null);
        assertTrue(cotacao.hashCode()!=0);
    }
}