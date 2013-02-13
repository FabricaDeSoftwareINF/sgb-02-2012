package br.ufg.inf.es.base.validation.annotations;

import br.ufg.inf.es.base.validation.Validation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation para validação;
 * 
 * @author Cézar Augusto Ferreira
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.ANNOTATION_TYPE})
public @interface Validator {

    /**
     * Método que define a classe utilizada para validação.
     *
     * @return Classe para validação
     */
    Class<? extends Validation> validatorClass();
}