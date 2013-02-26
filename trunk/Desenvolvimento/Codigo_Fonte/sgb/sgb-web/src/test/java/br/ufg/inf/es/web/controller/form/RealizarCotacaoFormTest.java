package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.ItemListaCotacao;
import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.datamodel.ItemListaCotacaoDataModel;
import br.ufg.inf.es.web.datamodel.LivroDataModel;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class RealizarCotacaoFormTest {

    private RealizarCotacaoForm form;
    private LivroDataModel livrosCotacao;
    private LivroDataModel livroDataModel;
    private Livro[] livrosSelecionados;
    private ItemListaCotacao[] cotacoesSelecionadas;
    private ItemListaCotacaoDataModel cotacoesDataModel;
    private ListaCompras listaCompras;
    private String nomeLista;

    @Before
    public void setUp() {
        livrosCotacao = new LivroDataModel();
        livroDataModel = new LivroDataModel();
        livrosSelecionados = new Livro[0];
        cotacoesSelecionadas = new ItemListaCotacao[0];
        cotacoesDataModel = new ItemListaCotacaoDataModel();
        listaCompras = new ListaCompras();
        nomeLista = "nome lista";

        form = new RealizarCotacaoForm();
        form.setLivrosCotacao(livrosCotacao);
        form.setLivroDataModel(livroDataModel);
        form.setLivrosSelecionados(livrosSelecionados);
        form.setCotacoesSelecionadas(cotacoesSelecionadas);
        form.setCotacoesDataModel(cotacoesDataModel);
        form.setListaCompras(listaCompras);
        form.setNomeLista(nomeLista);
    }

    /**
     * Test of getCotacoesSelecionadas method, of class RealizarCotacaoForm.
     */
    @Test
    public void testGetCotacoesSelecionadas() {
        ItemListaCotacao[] result = form.getCotacoesSelecionadas();
        assertArrayEquals(cotacoesSelecionadas, result);
    }

    /**
     * Test of getLivrosCotacao method, of class RealizarCotacaoForm.
     */
    @Test
    public void testGetLivrosCotacao() {
        LivroDataModel result = form.getLivrosCotacao();
        assertEquals(livrosCotacao, result);
    }

    /**
     * Test of getLivrosSelecionados method, of class RealizarCotacaoForm.
     */
    @Test
    public void testGetLivrosSelecionados() {
        Livro[] result = form.getLivrosSelecionados();
        assertArrayEquals(livrosSelecionados, result);
    }

    /**
     * Test of getCotacoesDataModel method, of class RealizarCotacaoForm.
     */
    @Test
    public void testGetCotacoesDataModel() {
        ItemListaCotacaoDataModel result = form.getCotacoesDataModel();
        assertEquals(cotacoesDataModel, result);
    }

    /**
     * Test of getLivroDataModel method, of class RealizarCotacaoForm.
     */
    @Test
    public void testGetLivroDataModel() {
        LivroDataModel result = form.getLivroDataModel();
        assertEquals(livroDataModel, result);
    }

    /**
     * Test of getListaCompras method, of class RealizarCotacaoForm.
     */
    @Test
    public void testGetListaCompras() {
        ListaCompras result = form.getListaCompras();
        assertEquals(listaCompras, result);
    }

    /**
     * Test of getNomeLista method, of class RealizarCotacaoForm.
     */
    @Test
    public void testGetNomeLista() {
        String result = form.getNomeLista();
        assertEquals(nomeLista, result);
    }

    /**
     * Test of getValorTotal method, of class RealizarCotacaoForm.
     */
    @Test
    public void testGetValorTotal() {
        double result = form.getValorTotal();
        assertEquals(0.0, result, 0.0);
    }

    /**
     * Test of getValorTotal method, of class RealizarCotacaoForm.
     */
    @Test
    public void testGetValorTotalComCotacao() {
        cotacoesSelecionadas = new ItemListaCotacao[1];
        ItemListaCotacao item = new ItemListaCotacao();
        item.setQuantidade(1);
        item.setValorMedio(10.0);
        cotacoesSelecionadas[0] = item;
        form.setCotacoesSelecionadas(cotacoesSelecionadas);
        double result = form.getValorTotal();
        assertEquals(10.0, result, 0.0);
    }
}
