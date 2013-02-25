package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.AutorDTO;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class AutorDataModelTest {

    private AutorDataModel dataModel = new AutorDataModel();
    private AutorDTO object;
    private Long idValido = 1l;

    @Before
    public void setUp() {
        object = new AutorDTO();
        object.setId(idValido);

        List dtos = Arrays.asList(object);
        dataModel = new AutorDataModel(dtos);
    }

    /**
     * Test of getRowData method, of class AutorDataModel.
     */
    @Test
    public void testGetRowData() {
        AutorDTO expResult = object;
        AutorDTO result = dataModel.getRowData(idValido.toString());
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowData method, of class AutorDataModel.
     */
    @Test
    public void testGetRowDataIdInvalido() {
        AutorDTO expResult = null;
        AutorDTO result = dataModel.getRowData("idInvalido");
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowKey method, of class AutorDataModel.
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
        assertNotNull(new AutorDataModel());
    }
}
