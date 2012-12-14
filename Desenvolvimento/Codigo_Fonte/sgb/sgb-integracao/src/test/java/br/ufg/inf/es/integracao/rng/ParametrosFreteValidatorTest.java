package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.ParametrosBiblioteca;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

/**
 * Testes para a validacao dos parametros da biblioteca
 * 
 * @author Victor Carvalho
 */
public class ParametrosFreteValidatorTest {

    ParametrosFreteValidator p;
    ParametrosBiblioteca parametros;

    @Before
    public void init() {
        p = new ParametrosFreteValidator();
        parametros = new ParametrosBiblioteca();
    }

    @Test(expected = ValidationException.class)
    public void testValidarObjetoComFreteNulo() throws ValidationException {
        parametros.setValorFrete(null);
        p.validate(parametros);
    }

    @Test(expected = ValidationException.class)
    public void testValidarObjetoComFreteNegativo() throws ValidationException {
        parametros.setValorFrete(new BigDecimal("-1"));
        p.validate(parametros);
    }

    @Test
    public void testValidarObjetoCorreto() throws ValidationException {
        p.validate(parametros);
    }
}