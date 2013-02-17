package br.ufg.inf.es.model;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Victor Carvalho
 */
public class EditoraTest {

    private Editora editora;
    private String nome = "nome";
    private Collection<Livro> livros = Arrays.asList(new Livro());

    @Before
    public void setUp() {
        editora = new Editora();
        
        editora.setNome(nome);
        editora.setLivros(livros);
    }

    /**
     * Test of getNome method, of class Editora.
     */
    @Test
    public void testGetNome() {
        assertEquals(nome, editora.getNome());
    }

    /**
     * Test of getLivros method, of class Editora.
     */
    @Test
    public void testGetLivros() {
        assertEquals(livros, editora.getLivros());
    }
}