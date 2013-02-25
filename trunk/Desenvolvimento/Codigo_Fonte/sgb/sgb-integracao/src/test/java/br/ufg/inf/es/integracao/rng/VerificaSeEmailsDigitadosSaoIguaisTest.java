package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Usuario;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * Testes para a validacao da confirmacao de email do usuario
 *
 * @author victor
 */
public class VerificaSeEmailsDigitadosSaoIguaisTest {

    VerificaSeEmailsDigitadosSaoIguais validator;
    Usuario usuario;

    @Before
    public void init() {
        validator = new VerificaSeEmailsDigitadosSaoIguais();
        usuario = new Usuario();
    }

    @Test(expected = ValidationException.class)
    public void usuarioSemEmailDeveLancarExcecao() throws ValidationException {
        validator.validate(usuario);
    }

    @Test(expected = ValidationException.class)
    public void usuarioSemConfirmacaoDeEmailDeveLancarExcecao() throws ValidationException {
        usuario.setEmail("email");
        validator.validate(usuario);
    }

    @Test(expected = ValidationException.class)
    public void usuarioEmailDiferenteDaConfirmacaoDeveLancarExcecao() throws ValidationException {
        usuario.setEmail("email");
        usuario.setConfirmacaoEmail("emailNaoConfirmado");
        validator.validate(usuario);
    }

    @Test
    public void usuarioComConfirmacaoDeEmailCorretaNaoDeveLancarExcecao() throws ValidationException {
        usuario.setEmail("email");
        usuario.setConfirmacaoEmail("email");
        validator.validate(usuario);
    }
}
