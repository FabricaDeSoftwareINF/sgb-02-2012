package br.ufg.inf.es.model.importacaobibliografia;

import java.util.Arrays;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Carvalho
 */
public class BibliografiaImportadaTest {

    private BibliografiaImportada bibliografiaImportada;
    private List<LivroImportado> basica = Arrays.asList(new LivroImportado());
    private List<LivroImportado> complementar = Arrays.asList(new LivroImportado());
    private List<LivroImportado> sugerida = Arrays.asList(new LivroImportado());

    @Before
    public void setUp() {
        bibliografiaImportada = new BibliografiaImportada();
        bibliografiaImportada.setBasica(basica);
        bibliografiaImportada.setComplementar(complementar);
        bibliografiaImportada.setSugerida(sugerida);
    }

    /**
     * Test of getBasica method, of class BibliografiaImportada.
     */
    @Test
    public void testGetBasica() {
        assertEquals(basica, bibliografiaImportada.getBasica());
    }

    /**
     * Test of getComplementar method, of class BibliografiaImportada.
     */
    @Test
    public void testGetComplementar() {
        assertEquals(complementar, bibliografiaImportada.getComplementar());
    }

    /**
     * Test of getSugerida method, of class BibliografiaImportada.
     */
    @Test
    public void testGetSugerida() {
        assertEquals(sugerida, bibliografiaImportada.getSugerida());
    }

    /**
     * Test of toString method, of class BibliografiaImportada.
     */
    @Test
    public void testToString() {
        assertNotNull(bibliografiaImportada.toString());
    }
}
