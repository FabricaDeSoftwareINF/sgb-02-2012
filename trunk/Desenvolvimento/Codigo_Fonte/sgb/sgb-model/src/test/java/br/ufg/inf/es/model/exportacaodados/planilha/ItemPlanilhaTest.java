package br.ufg.inf.es.model.exportacaodados.planilha;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Carvalho
 */
public class ItemPlanilhaTest {

    private ItemPlanilha itemPlanilha;
    private int numItem = 0;
    private String nomeAutor = "nomeAutor";
    private String tituloObra = "tituloObra";
    private String edicao = "edicao";
    private String editora = "editora";
    private String local = "local";
    private String ano = "ano";
    private String colecao = "colecao";
    private String volume = "volume";
    private String matriculaSophiaConselheiro = "matriculaSophiaConselheiro";
    private String cursoDestino = "cursoDestino";
    private String unidadeMedida = "unidadeMedida";
    private double valorMedioUnitario = 0.0;
    private int quantExemplares = 0;
    private String areaConhecimento = "areaConhecimento";

    @Before
    public void setUp() {
        itemPlanilha = new ItemPlanilha();

        itemPlanilha.setNumItem(numItem);
        itemPlanilha.setNomeAutor(nomeAutor);
        itemPlanilha.setTituloObra(tituloObra);
        itemPlanilha.setEdicao(edicao);
        itemPlanilha.setEditora(editora);
        itemPlanilha.setLocal(local);
        itemPlanilha.setAno(ano);
        itemPlanilha.setColecao(colecao);
        itemPlanilha.setVolume(volume);
        itemPlanilha.setMatriculaSophiaConselheiro(matriculaSophiaConselheiro);
        itemPlanilha.setCursoDestino(cursoDestino);
        itemPlanilha.setUnidadeMedida(unidadeMedida);
        itemPlanilha.setValorMedioUnitario(valorMedioUnitario);
        itemPlanilha.setQuantExemplares(quantExemplares);
        itemPlanilha.setAreaConhecimento(areaConhecimento);
    }

    /**
     * Test of getNumItem method, of class ItemPlanilha.
     */
    @Test
    public void testGetNumItem() {
        assertEquals(numItem, itemPlanilha.getNumItem());
    }

    /**
     * Test of getNomeAutor method, of class ItemPlanilha.
     */
    @Test
    public void testGetNomeAutor() {
        assertEquals(nomeAutor, itemPlanilha.getNomeAutor());
    }

    /**
     * Test of getTituloObra method, of class ItemPlanilha.
     */
    @Test
    public void testGetTituloObra() {
        assertEquals(tituloObra, itemPlanilha.getTituloObra());
    }

    /**
     * Test of getEdicao method, of class ItemPlanilha.
     */
    @Test
    public void testGetEdicao() {
        assertEquals(edicao, itemPlanilha.getEdicao());
    }

    /**
     * Test of getEditora method, of class ItemPlanilha.
     */
    @Test
    public void testGetEditora() {
        assertEquals(editora, itemPlanilha.getEditora());
    }

    /**
     * Test of getLocal method, of class ItemPlanilha.
     */
    @Test
    public void testGetLocal() {
        assertEquals(local, itemPlanilha.getLocal());
    }

    /**
     * Test of getAno method, of class ItemPlanilha.
     */
    @Test
    public void testGetAno() {
        assertEquals(ano, itemPlanilha.getAno());
    }

    /**
     * Test of getColecao method, of class ItemPlanilha.
     */
    @Test
    public void testGetColecao() {
        assertEquals(colecao, itemPlanilha.getColecao());
    }

    /**
     * Test of getVolume method, of class ItemPlanilha.
     */
    @Test
    public void testGetVolume() {
        assertEquals(volume, itemPlanilha.getVolume());
    }

    /**
     * Test of getMatriculaSophiaConselheiro method, of class ItemPlanilha.
     */
    @Test
    public void testGetMatriculaSophiaConselheiro() {
        assertEquals(matriculaSophiaConselheiro, itemPlanilha.getMatriculaSophiaConselheiro());
    }

    /**
     * Test of getCursoDestino method, of class ItemPlanilha.
     */
    @Test
    public void testGetCursoDestino() {
        assertEquals(cursoDestino, itemPlanilha.getCursoDestino());
    }

    /**
     * Test of getUnidadeMedida method, of class ItemPlanilha.
     */
    @Test
    public void testGetUnidadeMedida() {
        assertEquals(unidadeMedida, itemPlanilha.getUnidadeMedida());
    }

    /**
     * Test of getValorMedioUnitario method, of class ItemPlanilha.
     */
    @Test
    public void testGetValorMedioUnitario() {
        assertEquals(valorMedioUnitario, itemPlanilha.getValorMedioUnitario(), 0.0);
    }

    /**
     * Test of getQuantExemplares method, of class ItemPlanilha.
     */
    @Test
    public void testGetQuantExemplares() {
        assertEquals(quantExemplares, itemPlanilha.getQuantExemplares());
    }

    /**
     * Test of getAreaConhecimento method, of class ItemPlanilha.
     */
    @Test
    public void testGetAreaConhecimento() {
        assertEquals(areaConhecimento, itemPlanilha.getAreaConhecimento());
    }

    /**
     * Test of getNumColunas method, of class ItemPlanilha.
     */
    @Test
    public void testGetNumColunas() {
        assertNotNull(itemPlanilha.getNumColunas());
    }
}