package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.model.ItemListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.datamodel.ItemListaCotacaoDataModel;
import java.util.Arrays;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class CotacaoFormTest {

    private CotacaoForm form;
    private Collection<ItemListaCotacao> tabelaCotacoes;
    private Collection<Livro> livrosCotacao;
    private Livro[] livrosParaCotacao;
    private ItemListaCotacaoDataModel cotacaoDataModel;
    private Cotacao[] cotacoesSelecionadas;

    @Before
    public void setUp() {
        tabelaCotacoes = Arrays.asList(new ItemListaCotacao());
        livrosCotacao = Arrays.asList(new Livro());

        livrosParaCotacao = new Livro[0];

        cotacaoDataModel = new ItemListaCotacaoDataModel();
        cotacoesSelecionadas = new Cotacao[0];

        form = new CotacaoForm();

        form.setCotacaoDataModel(cotacaoDataModel);
        form.setCotacoesSelecionadas(cotacoesSelecionadas);
        form.setLivrosCotacao(livrosCotacao);
        form.setLivrosParaCotacao(livrosParaCotacao);
        form.setTabelaCotacoes(tabelaCotacoes);
    }

    /**
     * Test of getCotacaoDataModel method, of class CotacaoForm.
     */
    @Test
    public void testGetCotacaoDataModel() {
        ItemListaCotacaoDataModel result = form.getCotacaoDataModel();
        assertTrue(result instanceof ItemListaCotacaoDataModel);
    }

    /**
     * Test of getCotacoesSelecionadas method, of class CotacaoForm.
     */
    @Test
    public void testGetCotacoesSelecionadas() {
        Cotacao[] result = form.getCotacoesSelecionadas();
        assertArrayEquals(cotacoesSelecionadas, result);
    }

    /**
     * Test of getTabelaCotacoes method, of class CotacaoForm.
     */
    @Test
    public void testGetTabelaCotacoes() {
        Collection result = form.getTabelaCotacoes();
        assertEquals(tabelaCotacoes, result);
    }

    /**
     * Test of getLivrosCotacao method, of class CotacaoForm.
     */
    @Test
    public void testGetLivrosCotacao() {
        Collection result = form.getLivrosCotacao();
        assertEquals(livrosCotacao, result);
    }

    /**
     * Test of getLivrosParaCotacao method, of class CotacaoForm.
     */
    @Test
    public void testGetLivrosParaCotacao() {
        Livro[] result = form.getLivrosParaCotacao();
        assertArrayEquals(livrosParaCotacao, result);
    }
}