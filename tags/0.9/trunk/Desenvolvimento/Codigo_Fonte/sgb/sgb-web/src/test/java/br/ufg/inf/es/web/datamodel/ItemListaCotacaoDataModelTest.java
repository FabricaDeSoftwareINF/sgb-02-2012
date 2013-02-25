package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.ItemListaCotacao;
import br.ufg.inf.es.model.Livro;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class ItemListaCotacaoDataModelTest {

    private ItemListaCotacaoDataModel dataModel;
    private ItemListaCotacao object;
    private Long idValido = 1l;

    @Before
    public void setUp() {
        Livro livro = new Livro();
        livro.setId(idValido);
        object = new ItemListaCotacao();
        object.setLivro(livro);

        List dtos = Arrays.asList(object);
        dataModel = new ItemListaCotacaoDataModel(dtos);
    }

    /**
     * Test of getRowData method, of class ItemListaCotacaoDataModel.
     */
    @Test
    public void testGetRowData() {
        ItemListaCotacao expResult = object;
        ItemListaCotacao result = dataModel.getRowData(idValido.toString());
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowData method, of class ItemListaCotacaoDataModel.
     */
    @Test
    public void testGetRowDataIdInvalido() {
        ItemListaCotacao expResult = null;
        ItemListaCotacao result = dataModel.getRowData("idInvalido");
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowKey method, of class ItemListaCotacaoDataModel.
     */
    @Test
    public void testGetRowKey() {
        Object result = dataModel.getRowKey(object);
        assertEquals(object.getLivro(), result);
    }

    /**
     * Test of default constructor
     */
    @Test
    public void testDefaultConstructor() {
        assertNotNull(new ItemListaCotacaoDataModel());
    }
}
