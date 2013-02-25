package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.ItemListaCompras;
import br.ufg.inf.es.web.datamodel.ItemListaCompraDataModel;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class ItemListaCompraFormTest {

    private ItemListaCompraForm form;
    private Collection<ItemListaCompras> todosLivros;
    private ItemListaCompraDataModel livroDM;

    @Before
    public void setUp() {
        todosLivros = new ArrayList<ItemListaCompras>();
        livroDM = new ItemListaCompraDataModel();
        form = new ItemListaCompraForm();

        form.setTodosLivros(todosLivros);
        form.setLivroDataModel(livroDM);
    }

    /**
     * Test of getTodosLivros method, of class ItemListaCompraForm.
     */
    @Test
    public void testGetTodosLivros() {
        Collection result = form.getTodosLivros();
        assertEquals(todosLivros, result);
    }

    /**
     * Test of getLivroDataModel method, of class ItemListaCompraForm.
     */
    @Test
    public void testGetLivroDataModel() {
        ItemListaCompraDataModel result = form.getLivroDataModel();
        assertNotNull(result);
    }
}
