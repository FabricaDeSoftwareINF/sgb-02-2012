package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * Testes para a validacao da entidade disciplina
 * 
 * @author Victor Carvalho
 */
public class DisciplinaValidatorTest {

    private DisciplinaValidator validator;
    private Disciplina object;
    private final Curso CURSO = new Curso();

    @Before
    public void init() {
        validator = new DisciplinaValidator();
        object = new Disciplina();
    }

    @Test(expected = ValidationException.class)
    public void objetoVazioDeveLancarExcecao() throws ValidationException {
        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComNomeECodigoNulosDeveLancarExcecao() throws ValidationException {
        object.setCurso(CURSO);
        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComCodigoECursoNulosDeveLancarExcecao() throws ValidationException {
        object.setNome("nome");
        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComNomeECursoNulosDeveLancarExcecao() throws ValidationException {
        object.setCodigo("codigo");
        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComNomeNuloDeveLancarExcecao() throws ValidationException {
        object.setCodigo("codigo");
        object.setCurso(CURSO);

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComCodigoNuloDeveLancarExcecao() throws ValidationException {
        object.setNome("nome");
        object.setCurso(CURSO);

        validator.validate(object);
    }

    @Test(expected = ValidationException.class)
    public void objetoComCursoNuloDeveLancarExcecao() throws ValidationException {
        object.setNome("nome");
        object.setCodigo("codigo");

        validator.validate(object);
    }

    @Test
    public void objetoValidoNaoDeveLancarExcecao() throws ValidationException {
        object.setNome("nome");
        object.setCodigo("codigo");
        object.setCurso(CURSO);

        validator.validate(object);
    }
}
