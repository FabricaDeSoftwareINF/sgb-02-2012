package br.ufg.inf.es.base.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation para definição de classe validadoras
 * @author Cézar Augusto Ferreira
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Validators {
    
    /**
     * Método que obtém os validadores.
     *
     * @return Validator[]
     */
    Validator[] value();
}