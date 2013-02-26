package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.base.persistence.biblioteca.DBDriver;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class BibliotecaConfigFormTest {

    private BibliotecaConfigForm form;
    private Boolean exibirDialogExclusao;
    private DBDriver driver;

    @Before
    public void setUp() {
        driver = DBDriver.Derby;
        exibirDialogExclusao = true;

        form = new BibliotecaConfigForm();
        form.setDriver(driver);
        form.setExibirDialogExclusao(exibirDialogExclusao);
    }

    /**
     * Test of getDrivers method, of class DBBibliotecaConfigForm.
     */
    @Test
    public void testGetDrivers() {
        DBDriver[] result = form.getDrivers();
        assertArrayEquals(DBDriver.values(), result);
    }

    /**
     * Test of getExibirDialogExclusao method, of class DBBibliotecaConfigForm.
     */
    @Test
    public void testGetExibirDialogExclusao() {
        Boolean result = form.getExibirDialogExclusao();
        assertEquals(exibirDialogExclusao, result);
    }

    /**
     * Test of getDriver method, of class DBBibliotecaConfigForm.
     */
    @Test
    public void testGetDriver() {
        DBDriver result = form.getDriver();
        assertEquals(driver, result);
    }
}
