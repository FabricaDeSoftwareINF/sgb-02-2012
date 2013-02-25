package br.ufg.inf.es.model;

import java.util.Arrays;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Carvalho
 */
public class AutorTest {

    private Autor autor;
    private String nome = "nome";
    private String sobreNome = "sobrenome";
    private Collection<Livro> livros = Arrays.asList(new Livro());

    /**
     * setup
     */
    @Before
    public void setUp() {
        autor = new Autor();
        autor.setNome(nome);
        autor.setSobrenome(sobreNome);
        autor.setLivros(livros);
    }

    /**
     * Test of getNome method, of class Autor.
     */
    @Test
    public void testGetNome() {
        assertEquals(nome, autor.getNome());
    }

    /**
     * Test of getSobrenome method, of class Autor.
     */
    @Test
    public void testGetSobrenome() {
        assertEquals(sobreNome, autor.getSobrenome());
    }

    /**
     * Test of getLivros method, of class Autor.
     */
    @Test
    public void testGetLivros() {
        assertEquals(livros, autor.getLivros());
    }

    /**
     * Test of toString method, of class Autor.
     */
    @Test
    public void testToString() {
        String expResult = nome + " " + sobreNome;
        String result = autor.toString();
        assertEquals(expResult, result);
    }
}