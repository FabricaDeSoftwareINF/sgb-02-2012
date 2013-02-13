package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Curso;
import org.junit.Before;
import org.junit.Test;

/**
 * Testes do validador da entidade curso
 * 
 * @author victor
 */
public class CursoValidatorTest {

    private CursoValidator validator;
    private Curso curso;

    @Before
    public void init() {
        validator = new CursoValidator();
        curso = new Curso();
    }

    @Test(expected = ValidationException.class)
    public void testValidarObjetoComNomeNuloDeveLancarExcecao() throws ValidationException {
        validator.validate(curso);
    }

    @Test(expected = ValidationException.class)
    public void testValidarObjetoComNomeEmBrancoDeveLancarExcecao() throws ValidationException {
        curso.setNome("  ");
        validator.validate(curso);
    }

    @Test(expected = ValidationException.class)
    public void testValidarObjetoComQtdeVagasZeradaDeveLancarExcecao() throws ValidationException {
        curso.setNome("ES");
        curso.setVagas(0);
        validator.validate(curso);
    }

    @Test(expected = ValidationException.class)
    public void testValidarObjetoComQtdeVagasNegativaDeveLancarExcecao() throws ValidationException {
        curso.setNome("ES");
        curso.setVagas(-1);
        validator.validate(curso);
    }

    @Test
    public void testValidarObjetoValidoNaoDeveLancarExcecao() throws ValidationException {
        curso.setNome("ES");
        curso.setVagas(1);
        validator.validate(curso);
    }
}
