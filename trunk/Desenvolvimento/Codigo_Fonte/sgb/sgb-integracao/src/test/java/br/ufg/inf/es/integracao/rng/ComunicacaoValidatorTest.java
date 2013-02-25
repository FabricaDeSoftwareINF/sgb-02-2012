package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.util.cripto.CriptoGeneric;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Comunicacao;
import org.junit.Before;
import org.junit.Test;

/**
 * Testes do validador da entidade comunicacao
 *
 * @author Victor Carvalho
 */
public class ComunicacaoValidatorTest {

    private ComunicacaoValidator validator;
    private Comunicacao comunicacao;
    private final String SERVICE = "service";
    private final String PORT = "service";
    private final String USUARIO = "service";

    @Before
    public void init() {
        validator = new ComunicacaoValidator();
        comunicacao = new Comunicacao();
    }

    @Test(expected = ValidationException.class)
    public void testValidarObjetoComServiceNuloDeveLancarExcecao() throws ValidationException {
        validator.validate(comunicacao);
    }

    @Test(expected = ValidationException.class)
    public void testValidarObjetoComServiceEmBrancoDeveLancarExcecao() throws ValidationException {
        comunicacao.setService("  ");
        validator.validate(comunicacao);
    }

    @Test(expected = ValidationException.class)
    public void testValidarObjetoComPortaNulaDeveLancarExcecao() throws ValidationException {
        comunicacao.setService(SERVICE);
        validator.validate(comunicacao);
    }

    @Test(expected = ValidationException.class)
    public void testValidarObjetoComPortaEmBrancoDeveLancarExcecao() throws ValidationException {
        comunicacao.setService(SERVICE);
        comunicacao.setPort("  ");
        validator.validate(comunicacao);
    }

    @Test(expected = ValidationException.class)
    public void testValidarObjetoComUsuarioNuloDeveLancarExcecao() throws ValidationException {
        comunicacao.setService(SERVICE);
        comunicacao.setPort(PORT);
        validator.validate(comunicacao);
    }

    @Test(expected = ValidationException.class)
    public void testValidarObjetoComUsuarioEmBrancoDeveLancarExcecao() throws ValidationException {
        comunicacao.setService(SERVICE);
        comunicacao.setPort(PORT);
        comunicacao.setUsuario("  ");
        validator.validate(comunicacao);
    }

    @Test(expected = ValidationException.class)
    public void testValidarObjetoComSenhaNulaDeveLancarExcecao() throws ValidationException {
        comunicacao.setService(SERVICE);
        comunicacao.setPort(PORT);
        comunicacao.setUsuario(USUARIO);
        validator.validate(comunicacao);
    }

    @Test
    public void testValidarObjetoValidoNaoDeveLancarExcecao() throws ValidationException {
        comunicacao.setService(SERVICE);
        comunicacao.setPort(PORT);
        comunicacao.setUsuario(USUARIO);
        comunicacao.setSenha(new CriptoGeneric().criptografa("senha"));
        validator.validate(comunicacao);
    }
}