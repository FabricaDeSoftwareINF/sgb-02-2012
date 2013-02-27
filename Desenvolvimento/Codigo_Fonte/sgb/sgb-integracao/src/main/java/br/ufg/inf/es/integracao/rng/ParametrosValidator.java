package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Parametros;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

/**
 * Classe de valida&ccedil;&atilde;o de parametros.
 * @author Victor Carvalho
 */
@Component
public class ParametrosValidator extends Validation<Parametros> {

    /**
     * Valida a entidade que representa os parametros do sistema
     * @param parametros
     * @throws ValidationException 
     */
    public void validate(Parametros parametros) throws ValidationException {
        if (parametros.getValorFrete() == null){
            throw new ValidationException("validacao.parametros.frete");
        } else if ( parametros.getValorFrete().compareTo(BigDecimal.ZERO) == -1){
            throw new ValidationException("validacao.parametros.frete");
        } else if (parametros.getParametroMEC() <= 0){
           throw new ValidationException("validacao.parametros.mec");
        }
    }
}
