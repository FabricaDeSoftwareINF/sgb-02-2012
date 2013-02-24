package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.Usuario;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class UsuarioDataModelTest {

    private UsuarioDataModel dataModel;
    private Usuario object;
    private Long idValido = 1l;

    @Before
    public void setUp() {
        object = new Usuario();
        object.setId(idValido);

        List dtos = Arrays.asList(object);
        dataModel = new UsuarioDataModel(dtos);
    }

    /**
     * Test of getRowData method, of class UsuarioDataModel.
     */
    @Test
    public void testGetRowData() {
        Usuario expResult = object;
        Usuario result = dataModel.getRowData(idValido.toString());
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowData method, of class UsuarioDataModel.
     */
    @Test
    public void testGetRowDataIdInvalido() {
        Usuario expResult = null;
        Usuario result = dataModel.getRowData("idInvalido");
        assertEquals(expResult, result);
    }

    /**
     * Test of getRowKey method, of class UsuarioDataModel.
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
        assertNotNull(new UsuarioDataModel());
    }
}
