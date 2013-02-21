package br.ufg.inf.es.integracao.annotations;

import br.ufg.inf.es.base.validation.annotations.Validator;
import br.ufg.inf.es.integracao.rng.VerificaSeEmailsDigitadosSaoIguais;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interface de regra de neg&oacute;cio para validar se os emails digitados
 * s&atilde;o iguais.
 * 
 * @author Luã
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Validator(validatorClass = VerificaSeEmailsDigitadosSaoIguais.class)
public @interface RNG003 {
    
}
