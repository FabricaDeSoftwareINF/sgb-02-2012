package br.ufg.inf.es.integracao.exportacaodados;

import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Editora;
import br.ufg.inf.es.model.Livro;
import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author vinicius
 */
public class MarcParserTest {

    private static final String titulo = "Livro Teste";
    private static final Long ano = 2009l;
    private static final String autorNome1 = "Joao";
    private static final String autorNome2 = "Inacio";
    private static final String autorSobrenome1 = "Jose";
    private static final String autorSobrenome2 = "Silva";
    private static final String edicao = "2";
    private static final String isbn10 = "12345678901";
    private static final String isbn13 = "1234567890123";
    private static final String editoraNome = "Moderna";
    private static final List autores = new ArrayList();
    private static Livro livro = new Livro();

    public MarcParserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        Autor autor1 = new Autor();
        autor1.setNome(autorNome1);
        autor1.setSobrenome(autorSobrenome1);

        Autor autor2 = new Autor();
        autor2.setNome(autorNome2);
        autor2.setSobrenome(autorSobrenome2);

        autores.add(autor1);
        autores.add(autor2);

        Editora editora = new Editora();
        editora.setNome(editoraNome);

        livro.setTitulo(titulo);
        livro.setAno(ano);
        livro.setAutores(autores);
        livro.setEdicao(edicao);
        livro.setEditora(editora);
        livro.setIsbn10(isbn10);
        livro.setIsbn13(isbn13);        
    }

    /**
     * Test of livroToMarc method, of class MarcParser.
     */
    @Test
    public void testLivroToMarcTitulo() throws Exception {
        String livroMarc = MarcParser.livroToMarc(livro);
        assertTrue(livroMarc.contains(titulo));
    }
    
    /**
     * Test of livroToMarc method, of class MarcParser.
     */
    @Test
    public void testLivroToMarcAno() throws Exception {
        String livroMarc = MarcParser.livroToMarc(livro);
        assertTrue(livroMarc.contains(ano.toString()));
    }
    
    /**
     * Test of livroToMarc method, of class MarcParser.
     */
    @Test
    public void testLivroToMarcAutores() throws Exception  {
        String livroMarc = MarcParser.livroToMarc(livro);
        String autoresString = autores.toString().
                replace("[", "").replace("]", "");
        assertTrue(livroMarc.contains(autoresString));
    }
    
    /**
     * Test of livroToMarc method, of class MarcParser.
     */
    @Test
    public void testLivroToMarcEdicao() throws Exception  {
        String livroMarc = MarcParser.livroToMarc(livro);
        assertTrue(livroMarc.contains(edicao));
    }
    
    /**
     * Test of livroToMarc method, of class MarcParser.
     */
    @Test
    public void testLivroToMarcEditora() throws Exception  {
        String livroMarc = MarcParser.livroToMarc(livro);
        assertTrue(livroMarc.contains(editoraNome));
    }
    
    
    /**
     * Test of livroToMarc method, of class MarcParser.
     */
    @Test
    public void testLivroToMarcIsbn10() throws Exception  {
        String livroMarc = MarcParser.livroToMarc(livro);
        assertTrue(livroMarc.contains(isbn10));
    }
    
    /**
     * Test of livroToMarc method, of class MarcParser.
     */
    @Test
    public void testLivroToMarcIsbn13() throws Exception  {
        String livroMarc = MarcParser.livroToMarc(livro);
        assertTrue(livroMarc.contains(isbn13));
    }
    
}
