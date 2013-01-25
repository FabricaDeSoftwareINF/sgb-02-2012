package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.exportacaodados.MarcParser;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Editora;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.controller.form.LivroForm;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author vinicius
 */
public class LivroControllerTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    private Livro criarLivroMock() {
        String titulo = "Livro Teste";
        Long ano = 2009l;
        String autorNome1 = "João";
        String autorNome2 = "Inácio";
        String autorSobrenome1 = "José";
        String autorSobrenome2 = "Silva";
        String edicao = "2";
        String isbn11 = "12345678901";
        String isbn13 = "1234567890123";
        String editoraNome = "Moderna";
        List autores = new ArrayList();
        Livro livro = new Livro();

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
        livro.setIsbn11(isbn11);
        livro.setIsbn13(isbn13);
        
        return livro;
    }

    /**
     * Test of exportarLivro method, of class LivroController.
     */
    @Test
    public void testExportarLivro() {
        LivroController livroController = new LivroController();
        Livro livroMock = criarLivroMock();
        LivroForm livroFormMock = new LivroForm();
        livroFormMock.setEntity(livroMock);
        livroController.setForm(livroFormMock);
        livroController.exportarLivro();
        assertNotNull(livroController.getFile());
    }

    /**
     * Test of exportarLivro method, of class LivroController.
     */
    @Test
    public void testExportarLivroNaoExportado() {
        LivroController instance = new LivroController();
        assertNull(instance.getFile());
    }
}
