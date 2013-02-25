package br.ufg.inf.es.model;

import java.util.Arrays;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Carvalho
 */
public class CursoTest {

    private Curso curso;
    private String nome = "nome";
    private Integer vagas = 60;
    private Collection<Disciplina> disciplinas = Arrays.asList(new Disciplina());

    /**
     * setup
     */
    @Before
    public void setUp() {
        curso = new Curso();

        curso.setNome(nome);
        curso.setVagas(vagas);
        curso.setDisciplinas(disciplinas);
    }

    /**
     * Test of getNome method, of class Curso.
     */
    @Test
    public void testGetNome() {
        assertEquals(nome, curso.getNome());
    }

    /**
     * Test of getVagas method, of class Curso.
     */
    @Test
    public void testGetVagas() {
        assertEquals(vagas, curso.getVagas());
    }

    /**
     * Test of getDisciplinas method, of class Curso.
     */
    @Test
    public void testGetDisciplinas() {
        assertEquals(disciplinas, curso.getDisciplinas());
    }
}