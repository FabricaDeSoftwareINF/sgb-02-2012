package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Editora;
import br.ufg.inf.es.model.Livro;
import java.util.Arrays;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * Testes para a validacao da entidade Livro
 *
 * @author victor
 */
public class LivroValidatorTest {

    private LivroValidator validator;
    private Livro livro;
    private final Long ANO = 2000L;
    private final Editora EDITORA = new Editora();
    private final Collection<Autor> AUTORES = Arrays.asList(new Autor());
    private final String ISBN_10 = "8580572185";
    private final String ISBN_13 = "9788580572186";

    @Before
    public void init() {
        validator = new LivroValidator();
        livro = new Livro();
    }

    @Test(expected = ValidationException.class)
    public void livroVazioDeveLancarExcecao() throws ValidationException {
        validator.validate(livro);
    }

    @Test(expected = ValidationException.class)
    public void livroComAnoMaiorQueOAtualDeveLancarExcecao() throws ValidationException {
        livro.setAno(9999L);
        validator.validate(livro);
    }

    @Test(expected = ValidationException.class)
    public void livroSemAutorDeveLancarExcecao() throws ValidationException {
        livro.setAno(ANO);

        livro.setAutores(null);

        validator.validate(livro);
    }

    @Test(expected = ValidationException.class)
    public void livroSemEditoraDeveLancarExcecao() throws ValidationException {
        livro.setAno(ANO);
        livro.setAutores(AUTORES);

        livro.setEditora(null);

        validator.validate(livro);
    }

    @Test(expected = ValidationException.class)
    public void livroSemTituloDeveLancarExcecao() throws ValidationException {
        livro.setAno(ANO);
        livro.setAutores(AUTORES);
        livro.setEditora(EDITORA);

        livro.setTitulo(null);

        validator.validate(livro);
    }

    @Test(expected = ValidationException.class)
    public void livroSemEdicaoDeveLancarExcecao() throws ValidationException {
        livro.setAno(ANO);
        livro.setAutores(AUTORES);
        livro.setEditora(EDITORA);
        livro.setTitulo("titulo");

        livro.setEdicao(null);

        validator.validate(livro);
    }

    @Test(expected = ValidationException.class)
    public void livroSemIsbn10DeveLancarExcecao() throws ValidationException {
        livro.setAno(ANO);
        livro.setAutores(AUTORES);
        livro.setEditora(EDITORA);
        livro.setTitulo("titulo");
        livro.setEdicao("edicao");

        livro.setIsbn10(null);

        validator.validate(livro);
    }

    @Test(expected = ValidationException.class)
    public void livroComIsbn10InvalidoDeveLancarExcecao() throws ValidationException {
        livro.setAno(ANO);
        livro.setAutores(AUTORES);
        livro.setEditora(EDITORA);
        livro.setTitulo("titulo");
        livro.setEdicao("edicao");

        livro.setIsbn10("1234567890");

        validator.validate(livro);
    }

    @Test(expected = ValidationException.class)
    public void livroSemIsbn13DeveLancarExcecao() throws ValidationException {
        livro.setAno(ANO);
        livro.setAutores(AUTORES);
        livro.setEditora(EDITORA);
        livro.setTitulo("titulo");
        livro.setEdicao("edicao");
        livro.setIsbn10(ISBN_10);

        livro.setIsbn13(null);

        validator.validate(livro);
    }

    @Test(expected = ValidationException.class)
    public void livroComIsbn13InvalidoDeveLancarExcecao() throws ValidationException {
        livro.setAno(ANO);
        livro.setAutores(AUTORES);
        livro.setEditora(EDITORA);
        livro.setTitulo("titulo");
        livro.setEdicao("edicao");
        livro.setIsbn10(ISBN_10);

        livro.setIsbn13("1234567890123");

        validator.validate(livro);
    }

    @Test
    public void livroValidoNaoDeveLancarExcecao() throws ValidationException {
        livro.setAno(ANO);
        livro.setAutores(AUTORES);
        livro.setEditora(EDITORA);
        livro.setTitulo("titulo");
        livro.setEdicao("edicao");
        livro.setIsbn10(ISBN_10);
        livro.setIsbn13(ISBN_13);

        validator.validate(livro);
    }
}
