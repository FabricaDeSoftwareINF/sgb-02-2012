package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.Disciplina;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class DisciplinaDataModelTest {

    private DisciplinaDataModel dataModel;
    private Disciplina object;
    private Long idValido = 1l;

    @Before
    public void setUp() {
        object = new Disciplina();
        object.setId(idValido);

        List dtos = Arrays.asList(object);
        dataModel = new DisciplinaDataModel(dtos);
    }

    /**
     * Test of getRowData method, of class DisciplinaDataModel.
     */
    @Test
    public void testGetRowData() {
        Disciplina expResult = object;
        Disciplina result = dataModel.getRowData(idValido.toString());
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowData method, of class DisciplinaDataModel.
     */
    @Test
    public void testGetRowDataIdInvalido() {
        Disciplina expResult = null;
        Disciplina result = dataModel.getRowData("idInvalido");
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowKey method, of class DisciplinaDataModel.
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
        assertNotNull(new DisciplinaDataModel());
    }
}
