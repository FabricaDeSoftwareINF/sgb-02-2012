package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.ItemListaCotacao;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.web.datamodel.ListaCotacaoDataModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class ListaCotacaoFormTest {

    private ListaCotacaoForm form;
    private Collection<ListaCotacao> tabelaListaCotacoes;
    private ListaCotacaoDataModel listaCotacaoDataModel;
    private Collection<ListaCotacao> listasSelecionadas = new ArrayList<ListaCotacao>();
    private Boolean exibirDialogExclusao;
    private List<ItemListaCotacao> listaOtimizada;
    private boolean tipoOtimizacao;
    private Double valorOrcamento;

    @Before
    public void setUp() {
        tabelaListaCotacoes = new ArrayList<ListaCotacao>();
        listaCotacaoDataModel = new ListaCotacaoDataModel();
        exibirDialogExclusao = true;
        listaOtimizada = new ArrayList<ItemListaCotacao>();
        tipoOtimizacao = false;
        valorOrcamento = 10.0;

        form = new ListaCotacaoForm();

        form.setTabelaListaCotacoes(tabelaListaCotacoes);
        form.setListaCotacaoDataModel(listaCotacaoDataModel);
        form.setListasSelecionadas(listasSelecionadas);
        form.setExibirDialogExclusao(exibirDialogExclusao);
        form.setListaOtimizada(listaOtimizada);
        form.setTipoOtimizacao(tipoOtimizacao);
        form.setValorOrcamento(valorOrcamento);

    }

    /**
     * Test of getListaCotacaoDataModel method, of class ListaCotacaoForm.
     */
    @Test
    public void testGetListaCotacaoDataModel() {
        ListaCotacaoDataModel result = form.getListaCotacaoDataModel();
        assertTrue(result instanceof ListaCotacaoDataModel);
    }

    /**
     * Test of getListaCotacaoDataModel method, of class ListaCotacaoForm.
     */
    @Test
    public void testGetListaCotacaoDataModelWhenListaCotacoesIsNull() {
        form.setTabelaListaCotacoes(null);
        ListaCotacaoDataModel result = form.getListaCotacaoDataModel();
        assertEquals(listaCotacaoDataModel, result);
    }

    /**
     * Test of getListasSelecionadas method, of class ListaCotacaoForm.
     */
    @Test
    public void testGetListasSelecionadas() {
        Collection result = form.getListasSelecionadas();
        assertEquals(listasSelecionadas, result);
    }

    /**
     * Test of getTabelaListaCotacoes method, of class ListaCotacaoForm.
     */
    @Test
    public void testGetTabelaListaCotacoes() {
        Collection result = form.getTabelaListaCotacoes();
        assertEquals(tabelaListaCotacoes, result);
    }

    /**
     * Test of getExibirDialogExclusao method, of class ListaCotacaoForm.
     */
    @Test
    public void testGetExibirDialogExclusao() {
        Boolean result = form.getExibirDialogExclusao();
        assertEquals(exibirDialogExclusao, result);
    }

    /**
     * Test of isTipoOtimizacao method, of class ListaCotacaoForm.
     */
    @Test
    public void testIsTipoOtimizacao() {
        boolean result = form.isTipoOtimizacao();
        assertEquals(tipoOtimizacao, result);
    }

    /**
     * Test of getValorOrcamento method, of class ListaCotacaoForm.
     */
    @Test
    public void testGetValorOrcamento() {
        Double result = form.getValorOrcamento();
        assertEquals(valorOrcamento, result);
    }

    /**
     * Test of getListaOtimizada method, of class ListaCotacaoForm.
     */
    @Test
    public void testGetListaOtimizada() {
        List result = form.getListaOtimizada();
        assertEquals(listaOtimizada, result);
    }
}
