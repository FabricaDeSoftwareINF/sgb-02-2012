package br.ufg.inf.es.model.importacaobibliografia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Victor Carvalho
 */
public class DisciplinaImportadaTest {

    private DisciplinaImportada disciplinaImportada;
    private String nome = "nome";
    private String codigo = "codigo";
    private BibliografiaImportada bibliografia = new BibliografiaImportada();

    @Before
    public void setUp() {
        disciplinaImportada = new DisciplinaImportada();

        disciplinaImportada.setNome(nome);
        disciplinaImportada.setCodigo(codigo);
        disciplinaImportada.setBibliografia(bibliografia);
    }

    /**
     * Test of getNome method, of class DisciplinaImportada.
     */
    @Test
    public void testGetNome() {
        assertEquals(nome, disciplinaImportada.getNome());
    }

    /**
     * Test of getCodigo method, of class DisciplinaImportada.
     */
    @Test
    public void testGetCodigo() {
        assertEquals(codigo, disciplinaImportada.getCodigo());
    }

    /**
     * Test of getBibliografia method, of class DisciplinaImportada.
     */
    @Test
    public void testGetBibliografia() {
        assertEquals(bibliografia, disciplinaImportada.getBibliografia());
    }

    /**
     * Test of toString method, of class DisciplinaImportada.
     */
    @Test
    public void testToString() {
        assertNotNull(disciplinaImportada.toString());
    }
}