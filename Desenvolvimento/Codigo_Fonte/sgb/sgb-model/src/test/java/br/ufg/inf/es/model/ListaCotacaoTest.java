package br.ufg.inf.es.model;

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
    private Collection<CotacoesLivro> cotacoesLivro = Arrays.asList(new CotacoesLivro());

    /**
     * setup
     */
    @Before
    public void setUp() {
        listaCotacao = new ListaCotacao();

        listaCotacao.setNome(nome);
        listaCotacao.setDataRealizada(dataRealizada);
        listaCotacao.setCotacoesLivro(cotacoesLivro);
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
     * Test of getCotacoesLivro method, of class ListaCotacao.
     */
    @Test
    public void testGetCotacoesLivro() {
        assertEquals(cotacoesLivro, listaCotacao.getCotacoesLivro());
    }
}