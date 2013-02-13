package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Editora;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * Testes para a validacao da entidade Editora
 *
 * @author Victor Carvalho
 */
public class EditoraValidatorTest {

    private EditoraValidator validator;
    private Editora object;

    @Before
    public void init() {
        validator = new EditoraValidator();
        object = new Editora();
    }

    @Test(expected = ValidationException.class)
    public void editoraSemNomeDeveLancarExcecao() throws ValidationException {
        validator.validate(object);
    }

    @Test
    public void editoraValidaNaoDeveLancarExcecao() throws ValidationException {
        object.setNome("editora");
        validator.validate(object);
    }
}