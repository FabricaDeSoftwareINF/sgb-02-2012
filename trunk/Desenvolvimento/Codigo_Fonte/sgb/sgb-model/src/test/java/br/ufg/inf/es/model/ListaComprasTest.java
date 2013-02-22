package br.ufg.inf.es.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Victopr Carvalho
 */
public class ListaComprasTest {

    private ListaCompras listaCompras;
    private String nome = "nome";
    private Date dataCriacao = new Date();
    private Usuario user = new Usuario();
    private Collection<Livro> livrosDaListaCompras = Arrays.asList(new Livro());

    @Before
    public void setUp() {
        listaCompras = new ListaCompras();

        listaCompras.setNome(nome);
        listaCompras.setDataCriacao(dataCriacao);
        listaCompras.setUser(user);
    }

    /**
     * Test of getNome method, of class ListaCompras.
     */
    @Test
    public void testGetNome() {
        assertEquals(nome, listaCompras.getNome());
    }

    /**
     * Test of getDataCriacao method, of class ListaCompras.
     */
    @Test
    public void testGetDataCriacao() {
        assertEquals(dataCriacao, listaCompras.getDataCriacao());
    }

    /**
     * Test of getDataRealizada method, of class ListaCotacao.
     */
    @Test
    public void testGetDataRealizadaQuandoADataEstaNula() {
        assertNull(new ListaCompras().getDataCriacao());
    }

    /**
     * Test of setDataRealizada method, of class ListaCotacao.
     */
    @Test
    public void testSetDataRealizadaQuandoADataEstaNula() {
        ListaCompras other = new ListaCompras();
        other.setDataCriacao(null);
        assertNull(other.getDataCriacao());
    }

    /**
     * Test of getUser method, of class ListaCompras.
     */
    @Test
    public void testGetUser() {
        assertEquals(user, listaCompras.getUser());
    }

    /**
     * Test of getLivrosDaListaCompras method, of class ListaCompras.
     */
    @Test
    public void testGetLivrosDaListaCompras() {
        assertEquals(livrosDaListaCompras, listaCompras.getLivrosDaListaCompras());
    }
}