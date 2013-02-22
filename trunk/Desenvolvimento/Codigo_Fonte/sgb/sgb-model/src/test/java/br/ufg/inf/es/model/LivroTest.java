package br.ufg.inf.es.model;

import br.ufg.inf.es.enuns.EnumTipoBibliografia;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Alunoinf_2, Victor Carvalho
 */
public class LivroTest {

    private Livro livro;
    private String titulo = "titulo";
    private Long ano = 0L;
    private String isbn10 = "isbn10";
    private String isbn13 = "isbn13";
    private String edicao = "edicao";
    private boolean estrangeiro = false;
    private Editora editora = new Editora();
    private Collection<Autor> autores = Arrays.asList(new Autor());
    private Collection<Bibliografia> bibliografias;
    private Collection<ListaCompras> listaCompras = Arrays.asList(new ListaCompras());
    private Collection<CotacoesLivro> cotacoesLivro = Arrays.asList(new CotacoesLivro());

    /**
     * setup
     */
    @Before
    public void setUp() {
        livro = new Livro();

        prepareBibliografias();

        livro.setTitulo(titulo);
        livro.setAno(ano);
        livro.setIsbn10(isbn10);
        livro.setIsbn13(isbn13);
        livro.setEdicao(edicao);
        livro.setEstrangeiro(estrangeiro);
        livro.setEditora(editora);
        livro.setAutores(autores);
        livro.setBibliografias(bibliografias);
        livro.setCotacoesLivro(cotacoesLivro);
    }

    /**
     * define uma bibliografia com disciplina e curso
     */
    private void prepareBibliografias() {
        Bibliografia bibliografia = new Bibliografia();
        Disciplina disciplina = new Disciplina();
        Curso curso = new Curso();
        bibliografia.setTipo(EnumTipoBibliografia.BASICA);
        curso.setNome("curso");
        disciplina.setNome("disciplina");
        disciplina.setCurso(curso);
        bibliografia.setDisciplina(disciplina);

        bibliografias = Arrays.asList(bibliografia);
    }

    /**
     * Test of getTitulo method, of class Livro.
     */
    @Test
    public void testGetTitulo() {
        assertEquals(titulo, livro.getTitulo());
    }

    /**
     * Test of getAno method, of class Livro.
     */
    @Test
    public void testGetAno() {
        assertEquals(ano, livro.getAno());
    }

    /**
     * Test of getIsbn10 method, of class Livro.
     */
    @Test
    public void testGetIsbn10() {
        assertEquals(isbn10, livro.getIsbn10());
    }

    /**
     * Test of getIsbn13 method, of class Livro.
     */
    @Test
    public void testGetIsbn13() {
        assertEquals(isbn13, livro.getIsbn13());
    }

    /**
     * Test of getEdicao method, of class Livro.
     */
    @Test
    public void testGetEdicao() {
        assertEquals(edicao, livro.getEdicao());
    }

    /**
     * Test of isEstrangeiro method, of class Livro.
     */
    @Test
    public void testIsEstrangeiro() {
        assertEquals(estrangeiro, livro.isEstrangeiro());
    }

    /**
     * Test of getEditora method, of class Livro.
     */
    @Test
    public void testGetEditora() {
        assertEquals(editora, livro.getEditora());
    }

    /**
     * Test of getAutores method, of class Livro.
     */
    @Test
    public void testGetAutores() {
        assertEquals(autores, livro.getAutores());
    }

    /**
     * Test of getBibliografias method, of class Livro.
     */
    @Test
    public void testGetBibliografias() {
        assertEquals(bibliografias, livro.getBibliografias());
    }

    /**
     * Test of getAutoresAsString method, of class Livro.
     */
    @Test
    public void testGetAutoresAsString() {
        assertNotNull(livro.getAutoresAsString());
    }

    /**
     * Test of getDisciplinasAsString method, of class Livro.
     */
    @Test
    public void testGetDisciplinasAsString() {
        assertNotNull(livro.getDisciplinasAsString());
    }

    /**
     * Test of getCotacoesLivro method, of class Livro.
     */
    @Test
    public void testGetCotacoesLivro() {
        assertEquals(cotacoesLivro, livro.getCotacoesLivro());
    }
    
    @Test
    public void testEquals() {
        assertFalse(livro.equals(""));
        Livro livro2 = new Livro();
        livro2.setTitulo("b");
        assertFalse(livro.equals(livro2));
        livro2.setTitulo(null);
        assertFalse(livro.equals(livro2));
        livro2.setAno(999L);
        assertFalse(livro.equals(livro2));
        livro2.setAno(null);
        assertFalse(livro.equals(livro2));
        livro2.setIsbn10("54545");
        assertFalse(livro.equals(livro2));
        livro2.setIsbn10(null);
        assertFalse(livro.equals(livro2));
        livro2.setIsbn13("8349534");
        assertFalse(livro.equals(livro2));
        livro2.setIsbn13(null);
        assertFalse(livro.equals(livro2));
        livro2.setEdicao("43254dfgaf");
        assertFalse(livro.equals(livro2));
        livro2.setEdicao(null);
        assertFalse(livro.equals(livro2));
        livro2.setEstrangeiro(false);
        assertFalse(livro.equals(livro2));
        livro2.setEdicao("9090");
        assertFalse(livro.equals(livro2));
        livro2.setEdicao(null);
        assertFalse(livro.equals(livro2));
        Editora ed = new Editora();
        ed.setNome("teste");
        livro2.setEditora(ed);
        assertFalse(livro.equals(livro2));
        livro2.setEditora(null);
        assertFalse(livro.equals(livro2));
        
        
    }
    @Test
    public void testHashCode() {
        setUp();
        assertTrue(livro.hashCode()!=0);
        livro.setTitulo(null);
        assertTrue(livro.hashCode()!=0);
        livro.setAno(null);
        assertTrue(livro.hashCode()!=0);
        livro.setIsbn10(null);
        assertTrue(livro.hashCode()!=0);
        livro.setIsbn13(null);
        assertTrue(livro.hashCode()!=0);
        livro.setEdicao(null);
        assertTrue(livro.hashCode()!=0);
        livro.setEstrangeiro(false);
        assertTrue(livro.hashCode()!=0);
        livro.setEditora(null);
        assertTrue(livro.hashCode()!=0);
        livro.setEdicao(null);
        assertTrue(livro.hashCode()!=0);
       
    }
    
}