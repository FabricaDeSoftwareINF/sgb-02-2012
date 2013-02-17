package br.ufg.inf.es.model;

import java.util.Arrays;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Carvalho
 */
public class DisciplinaTest {

    private Disciplina disciplina;
    private String nome = "nome";
    private String codigo = "codigo";
    private Curso curso = new Curso();
    private Collection<Bibliografia> bibliografias = Arrays.asList(new Bibliografia());

    @Before
    public void setUp() {
        disciplina = new Disciplina();
        
        disciplina.setNome(nome);
        disciplina.setCodigo(codigo);
        disciplina.setCurso(curso);
        disciplina.setBibliografias(bibliografias);
    }

    /**
     * Test of getNome method, of class Disciplina.
     */
    @Test
    public void testGetNome() {
        assertEquals(nome, disciplina.getNome());
    }

    /**
     * Test of getCodigo method, of class Disciplina.
     */
    @Test
    public void testGetCodigo() {
        assertEquals(codigo, disciplina.getCodigo());
    }

    /**
     * Test of getCurso method, of class Disciplina.
     */
    @Test
    public void testGetCurso() {
        assertEquals(curso, disciplina.getCurso());
    }

    /**
     * Test of getBibliografias method, of class Disciplina.
     */
    @Test
    public void testGetBibliografias() {
        assertEquals(bibliografias, disciplina.getBibliografias());
    }

    /**
     * Test of toString method, of class Disciplina.
     */
    @Test
    public void testToString() {
        assertNotNull(disciplina.toString());
    }
}