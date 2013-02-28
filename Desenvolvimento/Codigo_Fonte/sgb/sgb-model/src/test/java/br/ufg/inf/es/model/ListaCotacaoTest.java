package br.ufg.inf.es.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Victor Carvalho
 */
public class ListaCotacaoTest {

    private ListaCotacao listaCotacao;
    private String nome = "nome";
    private Date dataRealizada = new Date();
     ItemListaCotacao cotacao = new ItemListaCotacao();
    private Collection<ItemListaCotacao> cotacoesLivro =new ArrayList<ItemListaCotacao>();

    /**
     * setup
     */
    @Before
    public void setUp() {
        listaCotacao = new ListaCotacao();
       
        cotacao.setValorMedio(5);
        cotacao.setQuantidadeExigida(4);
        cotacao.setLivro(new Livro());
       
        listaCotacao.setNome(nome);
        listaCotacao.setDataRealizada(dataRealizada);
        listaCotacao.setItensListaCotacao(cotacoesLivro);
        cotacoesLivro.add(cotacao);
    }

    /**
     * Test of getNome method, of class ListaCotacao.
     */
    @Test
    public void testGetNome() {
        assertEquals(nome, listaCotacao.getNome());
    }

    /**
     * Test of getDataRealizada method, of class ListaCotacao.
     */
    @Test
    public void testGetDataRealizada() {
        assertEquals(dataRealizada, listaCotacao.getDataRealizada());
    }

    /**
     * Test of getDataRealizada method, of class ListaCotacao.
     */
    @Test
    public void testGetDataRealizadaQuandoADataEstaNula() {
        assertNull(new ListaCotacao().getDataRealizada());
    }

    /**
     * Test of setDataRealizada method, of class ListaCotacao.
     */
    @Test
    public void testSetDataRealizadaQuandoADataEstaNula() {
        ListaCotacao other = new ListaCotacao();
        other.setDataRealizada(null);
        assertNull(other.getDataRealizada());
    }

    /**
     * Test of getItemListaCotacao method, of class ListaCotacao.
     */
    @Test
    public void testGetItemListaCotacao() {
        assertEquals(cotacoesLivro, listaCotacao.getItensListaCotacao());
    }

    @Test
    public void testGetValor() {
       
        double valor = listaCotacao.getValor();
        assertTrue(valor > -1);
    }
   
    @Test
    public void testEquals(){
        setUp();
        assertTrue(listaCotacao.equals(listaCotacao));
        assertFalse(listaCotacao.equals(null));
        assertFalse(listaCotacao.equals(" "));
        
        ListaCotacao listaCotacao2 = new ListaCotacao();
        listaCotacao2.setNome("b");
        listaCotacao2.setDataRealizada(new Date());
        cotacoesLivro.add(new ItemListaCotacao());
        listaCotacao2.setItensListaCotacao(cotacoesLivro);
        
        assertFalse(listaCotacao.equals(listaCotacao2));
        listaCotacao2.setNome(null);
        assertFalse(listaCotacao.equals(listaCotacao2));
        listaCotacao2.setNome(nome);
        
       
        listaCotacao2.setItensListaCotacao(null);
        assertFalse(listaCotacao.equals(listaCotacao2));
        listaCotacao2.setItensListaCotacao(cotacoesLivro);
        
        listaCotacao2.setDataRealizada(null);
        assertFalse(listaCotacao.equals(listaCotacao2));
        listaCotacao2.setDataRealizada(dataRealizada);        
        listaCotacao2.setItensListaCotacao(null);
        assertFalse(listaCotacao.equals(listaCotacao2));
        listaCotacao2.setItensListaCotacao(cotacoesLivro);
    }
     @Test
    public void testHashCode()
    {
        listaCotacao.setNome(nome);
        assertTrue(listaCotacao.hashCode()!=0);
        listaCotacao.setNome(null);
        assertTrue(listaCotacao.hashCode()!=0);
        listaCotacao.setDataRealizada(new Date());
        assertTrue(listaCotacao.hashCode()!=0);
        listaCotacao.setDataRealizada(null);
        assertTrue(listaCotacao.hashCode()!=0);
        listaCotacao.setItensListaCotacao(cotacoesLivro);
        assertTrue(listaCotacao.hashCode()!=0);
        listaCotacao.setItensListaCotacao(null);
        assertTrue(listaCotacao.hashCode()!=0);
    }
}