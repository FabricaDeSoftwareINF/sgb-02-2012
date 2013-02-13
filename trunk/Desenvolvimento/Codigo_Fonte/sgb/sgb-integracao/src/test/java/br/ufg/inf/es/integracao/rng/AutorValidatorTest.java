package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Autor;
import org.junit.Before;
import org.junit.Test;

/**
 * Testes para o validador da entidade autor
 *
 * @author Victor Carvalho
 * @see Autor
 * @see AutorValidator
 */
public class AutorValidatorTest {

    private AutorValidator validator;
    private Autor autor;
    private final String NOME = "nome";
    private final String SOBRE_NOME = "sobrenome";

    /**
     * instancia os dados para os testes
     */
    @Before
    public void init() {
        validator = new AutorValidator();
        autor = new Autor();
    }

    @Test(expected = ValidationException.class)
    public void testValidarAutorComNomeESobrenomeNulos() throws ValidationException {
        validator.validate(autor);
    }

    @Test(expected = ValidationException.class)
    public void testValidarAutorComNomeNulo() throws ValidationException {
        autor.setSobrenome(SOBRE_NOME);
        validator.validate(autor);
    }

    @Test(expected = ValidationException.class)
    public void testValidarAutorComSobrenomeNulo() throws ValidationException {
        autor.setNome(NOME);
        validator.validate(autor);
    }

    @Test(expected = ValidationException.class)
    public void testValidarAutorComNomeEmBranco() throws ValidationException {
        autor.setNome("  ");
        autor.setSobrenome(SOBRE_NOME);
        validator.validate(autor);
    }

    @Test(expected = ValidationException.class)
    public void testValidarAutorComSobrenomeEmBranco() throws ValidationException {
        autor.setNome(NOME);
        autor.setSobrenome("  ");
        validator.validate(autor);
    }

    @Test
    public void testValidarAutorValidoNaoDeveLancarExcecao() throws ValidationException {
        autor.setNome(NOME);
        autor.setSobrenome(SOBRE_NOME);
        validator.validate(autor);
    }
}