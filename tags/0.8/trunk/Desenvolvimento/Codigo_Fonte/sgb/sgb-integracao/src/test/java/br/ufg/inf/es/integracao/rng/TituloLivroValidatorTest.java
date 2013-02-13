package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Livro;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * Testes para a validacao do titulo do livro
 * 
 * @author victor
 */
public class TituloLivroValidatorTest {

    TituloLivroValidator validator;
    Livro livro;

    @Before
    public void init() {
        validator = new TituloLivroValidator();
        livro = new Livro();
    }

    @Test(expected = ValidationException.class)
    public void livroSemTituloDeveLancarExcecao() throws ValidationException {
        validator.validate(livro);
    }

    @Test
    public void livroComTituloNaoDeveLancarExcecao() throws ValidationException {
        livro.setTitulo("titulo");
        validator.validate(livro);
    }
}
