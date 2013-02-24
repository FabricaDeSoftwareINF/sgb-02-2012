package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.Livro;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class LivroDataModelTest {

    private LivroDataModel dataModel;
    private Livro object;
    private Long idValido = 1l;

    @Before
    public void setUp() {
        object = new Livro();
        object.setId(idValido);

        List dtos = Arrays.asList(object);
        dataModel = new LivroDataModel(dtos);
    }

    /**
     * Test of getRowData method, of class LivroDataModel.
     */
    @Test
    public void testGetRowData() {
        Livro expResult = object;
        Livro result = dataModel.getRowData(idValido.toString());
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowData method, of class LivroDataModel.
     */
    @Test
    public void testGetRowDataIdInvalido() {
        Livro expResult = null;
        Livro result = dataModel.getRowData("idInvalido");
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowKey method, of class LivroDataModel.
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
        assertNotNull(new LivroDataModel());
    }
}
