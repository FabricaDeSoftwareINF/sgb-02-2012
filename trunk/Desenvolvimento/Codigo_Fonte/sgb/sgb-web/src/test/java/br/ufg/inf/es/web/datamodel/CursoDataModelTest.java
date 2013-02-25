package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.Curso;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class CursoDataModelTest {

    private CursoDataModel dataModel;
    private Curso object;
    private Long idValido = 1l;

    @Before
    public void setUp() {
        object = new Curso();
        object.setId(idValido);

        List dtos = Arrays.asList(object);
        dataModel = new CursoDataModel(dtos);
    }

    /**
     * Test of getRowData method, of class CursoDataModel.
     */
    @Test
    public void testGetRowData() {
        Curso expResult = object;
        Curso result = dataModel.getRowData(idValido.toString());
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowData method, of class CursoDataModel.
     */
    @Test
    public void testGetRowDataIdInvalido() {
        Curso expResult = null;
        Curso result = dataModel.getRowData("idInvalido");
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowKey method, of class CursoDataModel.
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
        assertNotNull(new CursoDataModel());
    }
}
