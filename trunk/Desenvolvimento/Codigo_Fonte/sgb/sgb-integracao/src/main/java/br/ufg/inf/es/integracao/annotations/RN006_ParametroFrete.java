package br.ufg.inf.es.integracao.annotations;

import br.ufg.inf.es.base.validation.annotations.Validator;
import br.ufg.inf.es.integracao.rng.ParametrosFreteValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Victor Carvalho
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Validator(validatorClass = ParametrosFreteValidator.class)
public @interface RN006_ParametroFrete {
}
