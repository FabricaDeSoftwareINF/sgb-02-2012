package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Usuario;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * Testes para a validacao da entidade Usuario
 * 
 * @author victor
 */
public class DadosUsuarioValidatorTest {

    private DadosUsuarioValidator validator;
    private Usuario object;

    @Before
    public void init() {
        validator = new DadosUsuarioValidator();
        object = new Usuario();
    }

    @Test(expected = ValidationException.class)
    public void objetoSemSenhaDeveLancarExcecao() throws ValidationException {
        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComSenhaEmBrancoDeveLancarExcecao() throws ValidationException {
        object.setSenha("  ");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoSemEmailDeveLancarExcecao() throws ValidationException {
        object.setSenha("senha");
        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComEmailEmBrancoDeveLancarExcecao() throws ValidationException {
        object.setSenha("senha");
        object.setEmail("   ");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoSemNomeDeveLancarExcecao() throws ValidationException {
        object.setSenha("senha");
        object.setEmail("email");
        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComNomeEmBrancoDeveLancarExcecao() throws ValidationException {
        object.setSenha("senha");
        object.setEmail("email");
        object.setNome("  ");

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoSemSobreNomeDeveLancarExcecao() throws ValidationException {
        object.setSenha("senha");
        object.setEmail("email");
        object.setNome("nome");
        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComSobreNomeEmBrancoDeveLancarExcecao() throws ValidationException {
        object.setSenha("senha");
        object.setEmail("email");
        object.setNome("nome");
        object.setSobrenome("  ");

        validator.validate(object);
    }

    @Test
    public void objetoValidoNaoDeveLancarExcecao() throws ValidationException {
        object.setSenha("senha");
        object.setEmail("email");
        object.setNome("nome");
        object.setSobrenome("sobrenome");

        validator.validate(object);
    }
}
