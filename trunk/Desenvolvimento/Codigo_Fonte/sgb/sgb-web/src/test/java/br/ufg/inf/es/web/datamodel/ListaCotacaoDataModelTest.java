package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.ListaCotacao;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class ListaCotacaoDataModelTest {

    private ListaCotacaoDataModel dataModel;
    private ListaCotacao object;
    private Long idValido = 1l;

    @Before
    public void setUp() {
        object = new ListaCotacao();
        object.setId(idValido);

        List dtos = Arrays.asList(object);
        dataModel = new ListaCotacaoDataModel(dtos);
    }

    /**
     * Test of getRowData method, of class ListaCotacaoDataModel.
     */
    @Test
    public void testGetRowData() {
        ListaCotacao expResult = object;
        ListaCotacao result = dataModel.getRowData(idValido.toString());
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowData method, of class ListaCotacaoDataModel.
     */
    @Test
    public void testGetRowDataIdInvalido() {
        ListaCotacao expResult = null;
        ListaCotacao result = dataModel.getRowData("idInvalido");
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowKey method, of class ListaCotacaoDataModel.
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
        assertNotNull(new ListaCotacaoDataModel());
    }
}
