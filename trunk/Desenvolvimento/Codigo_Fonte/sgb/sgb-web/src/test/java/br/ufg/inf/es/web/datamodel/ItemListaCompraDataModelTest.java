package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.ItemListaCompras;
import br.ufg.inf.es.model.Livro;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class ItemListaCompraDataModelTest {

    private ItemListaCompraDataModel dataModel;
    private ItemListaCompras object;
    private Long idValido = 1l;

    @Before
    public void setUp() {
        Livro livro = new Livro();
        livro.setId(idValido);
        object = new ItemListaCompras();
        object.setLivro(livro);

        List dtos = Arrays.asList(object);
        dataModel = new ItemListaCompraDataModel(dtos);
    }

    /**
     * Test of getRowData method, of class ItemListaCompraDataModel.
     */
    @Test
    public void testGetRowData() {
        ItemListaCompras expResult = object;
        ItemListaCompras result = dataModel.getRowData(idValido.toString());
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowData method, of class ItemListaCompraDataModel.
     */
    @Test
    public void testGetRowDataIdInvalido() {
        ItemListaCompras expResult = null;
        ItemListaCompras result = dataModel.getRowData("idInvalido");
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowKey method, of class ItemListaCompraDataModel.
     */
    @Test
    public void testGetRowKey() {
        Object result = dataModel.getRowKey(object);
        assertEquals(idValido, result);
    }

    /**
     * Test of default constructor
     */
    @Test
    public void testDefaultConstructor() {
        assertNotNull(new ItemListaCompraDataModel());
    }
}
