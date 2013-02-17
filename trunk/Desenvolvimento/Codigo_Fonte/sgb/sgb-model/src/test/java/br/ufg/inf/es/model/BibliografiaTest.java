package br.ufg.inf.es.model;

import br.ufg.inf.es.enuns.EnumTipoBibliografia;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Carvalho
 */
public class BibliografiaTest {

    private Bibliografia bibliografia;
    private EnumTipoBibliografia tipo = EnumTipoBibliografia.BASICA;
    private Livro livro = new Livro();
    private Disciplina disciplina = new Disciplina();
    private Long id = Long.MIN_VALUE;

    @Before
    public void setUp() {
        bibliografia = new Bibliografia();
        bibliografia.setDisciplina(disciplina);
        bibliografia.setLivro(livro);
        bibliografia.setTipo(tipo);
        bibliografia.setId(id);
    }

    /**
     * Test of getTipo method, of class Bibliografia.
     */
    @Test
    public void testGetTipo() {
        assertEquals(tipo, bibliografia.getTipo());
    }

    /**
     * Test of getLivro method, of class Bibliografia.
     */
    @Test
    public void testGetLivro() {
        assertEquals(livro, bibliografia.getLivro());
    }

    /**
     * Test of getDisciplina method, of class Bibliografia.
     */
    @Test
    public void testGetDisciplina() {
        assertEquals(disciplina, bibliografia.getDisciplina());
    }

    /**
     * Test of toString method, of class Bibliografia.
     */
    @Test
    public void testToString() {
        assertNotNull(bibliografia.toString());
    }

    /**
     * Test of equals method, of class Bibliografia.
     */
    @Test
    public void testEquals() {
        Bibliografia instance = new Bibliografia();
        boolean expResult = false;
        boolean result = bibliografia.equals(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Bibliografia.
     */
    @Test
    public void testEqualsDeveRetornarTrue() {
        boolean expResult = true;
        boolean result = bibliografia.equals(bibliografia);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Bibliografia.
     */
    @Test
    public void testEqualsParaTiposDiferentes() {
        boolean expResult = false;
        boolean result = bibliografia.equals(livro);
        assertEquals(expResult, result);
    }
    

    /**
     * Test of hashCode method, of class Bibliografia.
     */
    @Test
    public void testHashCode() {
        assertTrue(bibliografia.hashCode() > 0);
    }
}