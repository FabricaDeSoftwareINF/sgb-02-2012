package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.ItemListaCompras;
import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.web.datamodel.ItemListaCompraDataModel;
import br.ufg.inf.es.web.datamodel.ListaComprasDataModel;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class ListaComprasFormTest {

    private ListaComprasForm form;
    private Collection<ListaCompras> listaCompras;
    private Collection<ItemListaCompras> livrosSelecionados;
    private Collection<ItemListaCompras> livrosAdicaoSelecionados;
    private Collection<ItemListaCompras> todosLivros;
    private ItemListaCompraDataModel itemListaComprasDataModel;
    private ItemListaCompraDataModel itensListaAdicao;
    private ListaComprasDataModel listaComprasDM;
    private String filtroTitulo;
    private String nomeLista;
    private ListaCompras listaComprasParaRemocao;

    @Before
    public void setUp() {
        listaCompras = new ArrayList<ListaCompras>();
        livrosSelecionados = new ArrayList<ItemListaCompras>();
        livrosAdicaoSelecionados = new ArrayList<ItemListaCompras>();
        todosLivros = new ArrayList<ItemListaCompras>();
        itemListaComprasDataModel = new ItemListaCompraDataModel();
        itensListaAdicao = new ItemListaCompraDataModel();
        listaComprasDM = new ListaComprasDataModel();
        filtroTitulo = "filtro";
        nomeLista = "nomeLista";
        listaComprasParaRemocao = new ListaCompras();

        form = new ListaComprasForm();
        form.setListaCompras(listaCompras);
        form.setLivrosSelecionados(livrosSelecionados);
        form.setLivrosAdicaoSelecionados(livrosAdicaoSelecionados);
        form.setTodosLivros(todosLivros);
        form.setItemListaDataModel(itemListaComprasDataModel);
        form.setItensListaAdicao(itensListaAdicao);
        form.setLivroDM(itemListaComprasDataModel);
        form.setLivrosAdicaoSelecionados(livrosAdicaoSelecionados);
        form.setLivrosSelecionados(todosLivros);
        form.setNomeLista(nomeLista);
        form.setFiltroTitulo(filtroTitulo);
        form.setListaComprasDM(listaComprasDM);
        form.setListaComprasParaRemocao(listaComprasParaRemocao);
    }

    /**
     * Test of getListaCompras method, of class ListaComprasForm.
     */
    @Test
    public void testGetListaCompras() {
        Collection result = form.getListaCompras();
        assertEquals(listaCompras, result);
    }

    /**
     * Test of getLivrosSelecionados method, of class ListaComprasForm.
     */
    @Test
    public void testGetLivrosSelecionados() {
        Collection result = form.getLivrosSelecionados();
        assertEquals(livrosSelecionados, result);
    }

    /**
     * Test of getLivrosAdicaoSelecionados method, of class ListaComprasForm.
     */
    @Test
    public void testGetLivrosAdicaoSelecionados() {
        Collection result = form.getLivrosAdicaoSelecionados();
        assertEquals(livrosAdicaoSelecionados, result);
    }

    /**
     * Test of getTodosLivros method, of class ListaComprasForm.
     */
    @Test
    public void testGetTodosLivros() {
        Collection result = form.getTodosLivros();
        assertEquals(todosLivros, result);
    }

    /**
     * Test of getItemListaDataModel method, of class ListaComprasForm.
     */
    @Test
    public void testGetItemListaDataModel() {
        ItemListaCompraDataModel result = form.getItemListaDataModel();
        assertEquals(itemListaComprasDataModel, result);
    }

    /**
     * Test of getItensListaAdicao method, of class ListaComprasForm.
     */
    @Test
    public void testGetItensListaAdicao() {
        ItemListaCompraDataModel result = form.getItensListaAdicao();
        assertEquals(itensListaAdicao, result);
    }

    /**
     * Test of getFiltroTitulo method, of class ListaComprasForm.
     */
    @Test
    public void testGetFiltroTitulo() {
        String result = form.getFiltroTitulo();
        assertEquals(filtroTitulo, result);
    }

    /**
     * Test of getListaComprasDM method, of class ListaComprasForm.
     */
    @Test
    public void testGetListaComprasDM() {
        ListaComprasDataModel result = form.getListaComprasDM();
        assertEquals(listaComprasDM, result);
    }

    /**
     * Test of getNomeLista method, of class ListaComprasForm.
     */
    @Test
    public void testGetNomeLista() {
        String result = form.getNomeLista();
        assertEquals(nomeLista, result);
    }

    /**
     * Test of getLivroDM method, of class ListaComprasForm.
     */
    @Test
    public void testGetLivroDM() {
        ItemListaCompraDataModel result = form.getLivroDM();
        assertEquals(itemListaComprasDataModel, result);
    }

    /**
     * Test of getListaComprasParaRemocao method, of class ListaComprasForm.
     */
    @Test
    public void testGetListaComprasParaRemocao() {
        ListaCompras result = form.getListaComprasParaRemocao();
        assertEquals(listaComprasParaRemocao, result);
    }
}