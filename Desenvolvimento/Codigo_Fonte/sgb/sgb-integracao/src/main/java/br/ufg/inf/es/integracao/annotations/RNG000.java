/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.annotations;

import br.ufg.inf.es.base.validation.annotations.Validator;
import br.ufg.inf.es.integracao.rng.TituloLivroValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author cezar
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Validator(validatorClass = TituloLivroValidator.class)
public @interface RNG000 {
    
}
