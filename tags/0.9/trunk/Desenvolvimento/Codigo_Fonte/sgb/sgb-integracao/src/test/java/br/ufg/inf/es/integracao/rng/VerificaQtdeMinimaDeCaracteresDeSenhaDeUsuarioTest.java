package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Usuario;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * Testes para a validacao da quantidade de caracteres na senha do usuario
 * 
 * @author victor
 */
public class VerificaQtdeMinimaDeCaracteresDeSenhaDeUsuarioTest {

    VerificaQtdeMinimaDeCaracteresDeSenhaDeUsuario validator;
    Usuario usuario;

    @Before
    public void init() {
        validator = new VerificaQtdeMinimaDeCaracteresDeSenhaDeUsuario();
        usuario = new Usuario();
    }

    @Test(expected = ValidationException.class)
    public void usuarioSemSenhaDeveLancarExcecao() throws ValidationException {
        validator.validate(usuario);
    }

    @Test(expected = ValidationException.class)
    public void usuarioComSenhaMenorQueSeisDeveLancarExcecao() throws ValidationException {
        usuario.setSenha("12345");
        validator.validate(usuario);
    }

    @Test
    public void livroComTituloNaoDeveLancarExcecao() throws ValidationException {
        usuario.setSenha("123456");
        validator.validate(usuario);
    }
}
