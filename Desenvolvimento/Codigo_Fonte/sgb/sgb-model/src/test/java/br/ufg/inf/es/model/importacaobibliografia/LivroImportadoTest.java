package br.ufg.inf.es.model.importacaobibliografia;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Alunoinf_2, Victor Carvalho
 */
public class LivroImportadoTest {

    private LivroImportado livroImportado;
    private String titulo = "titulo";
    private String edicao = "edicao";
    private String anoPublicacao = "anoPublicacao";
    private String autor = "autor";
    private String isbn10 = "isbn10";
    private String isbn13 = "isbn13";

    @Before
    public void setUp() {
        livroImportado = new LivroImportado();

        livroImportado.setAnoPublicacao(anoPublicacao);
        livroImportado.setAutor(autor);
        livroImportado.setEdicao(edicao);
        livroImportado.setIsbn10(isbn10);
        livroImportado.setIsbn13(isbn13);
        livroImportado.setTitulo(titulo);

    }

    /**
     * Test of getTitulo method, of class LivroImportado.
     */
    @Test
    public void testGetTitulo() {
        String result = livroImportado.getTitulo();
        assertEquals(titulo, result);
    }

    /**
     * Test of getEdicao method, of class LivroImportado.
     */
    @Test
    public void testGetEdicao() {
        String result = livroImportado.getEdicao();
        assertEquals(edicao, result);
    }

    /**
     * Test of getAnoPublicacao method, of class LivroImportado.
     */
    @Test
    public void testGetAnoPublicacao() {
        String result = livroImportado.getAnoPublicacao();
        assertEquals(anoPublicacao, result);
    }

    /**
     * Test of getAutor method, of class LivroImportado.
     */
    @Test
    public void testGetAutor() {
        String result = livroImportado.getAutor();
        assertEquals(autor, result);
    }

    /**
     * Test of getIsbn10 method, of class LivroImportado.
     */
    @Test
    public void testgetIsbn10() {
        String result = livroImportado.getIsbn10();
        assertEquals(isbn10, result);
    }

    /**
     * Test of getIsbn13 method, of class LivroImportado.
     */
    @Test
    public void testGetIsbn13() {
        String result = livroImportado.getIsbn13();
        assertEquals(isbn13, result);
    }

    /**
     * Test of toString method, of class LivroImportado.
     */
    @Test
    public void testToString() {
        assertNotNull(livroImportado.toString());
    }

    /**
     * Test of getIsbn10 method, of class LivroImportado.
     */
    @Test
    public void testGetIsbn10() {
        String result = livroImportado.getIsbn10();
        assertEquals(isbn10, result);
    }
}