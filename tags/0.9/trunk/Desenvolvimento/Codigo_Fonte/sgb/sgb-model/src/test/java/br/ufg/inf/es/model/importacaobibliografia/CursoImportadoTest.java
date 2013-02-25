package br.ufg.inf.es.model.importacaobibliografia;

import java.util.Arrays;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Carvalho
 */
public class CursoImportadoTest {

    private CursoImportado cursoImportado;
    private String nome = "nome";
    private int vagas = 60;
    private List<DisciplinaImportada> disciplinas = Arrays.asList(new DisciplinaImportada());

    @Before
    public void setUp() {
        cursoImportado = new CursoImportado();

        cursoImportado.setNome(nome);
        cursoImportado.setVagas(vagas);
        cursoImportado.setDisciplinas(disciplinas);
    }

    /**
     * Test of getNome method, of class CursoImportado.
     */
    @Test
    public void testGetNome() {
        assertEquals(nome, cursoImportado.getNome());
    }

    /**
     * Test of getVagas method, of class CursoImportado.
     */
    @Test
    public void testGetVagas() {
        assertEquals(vagas, cursoImportado.getVagas());
    }

    /**
     * Test of getDisciplinas method, of class CursoImportado.
     */
    @Test
    public void testGetDisciplinas() {
        assertEquals(disciplinas, cursoImportado.getDisciplinas());
    }

    /**
     * Test of toString method, of class CursoImportado.
     */
    @Test
    public void testToString() {
        assertNotNull(cursoImportado.toString());
    }
}