package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.ParametrosBiblioteca;
import java.math.BigDecimal;

/**
 *
 * @author victor
 */
public class ParametrosFreteValidator implements Validation<ParametrosBiblioteca> {

    public void validate(ParametrosBiblioteca parametros) throws ValidationException {
        if (parametros.getValorFrete() == null
                || parametros.getValorFrete().compareTo(BigDecimal.ZERO) == -1) {
            throw new ValidationException("validacao.parametros.frete");
        }
    }
}
