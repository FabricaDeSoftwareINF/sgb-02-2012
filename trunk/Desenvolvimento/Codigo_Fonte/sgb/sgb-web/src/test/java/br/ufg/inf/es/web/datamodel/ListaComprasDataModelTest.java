package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.ListaCompras;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class ListaComprasDataModelTest {

    private ListaComprasDataModel dataModel;
    private ListaCompras object;
    private Long idValido = 1l;

    @Before
    public void setUp() {
        object = new ListaCompras();
        object.setId(idValido);

        List dtos = Arrays.asList(object);
        dataModel = new ListaComprasDataModel(dtos);
    }

    /**
     * Test of getRowData method, of class ListaComprasDataModel.
     */
    @Test
    public void testGetRowData() {
        ListaCompras expResult = object;
        ListaCompras result = dataModel.getRowData(idValido.toString());
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowData method, of class ListaComprasDataModel.
     */
    @Test
    public void testGetRowDataIdInvalido() {
        ListaCompras expResult = null;
        ListaCompras result = dataModel.getRowData("idInvalido");
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowKey method, of class ListaComprasDataModel.
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
        assertNotNull(new ListaComprasDataModel());
    }
}
