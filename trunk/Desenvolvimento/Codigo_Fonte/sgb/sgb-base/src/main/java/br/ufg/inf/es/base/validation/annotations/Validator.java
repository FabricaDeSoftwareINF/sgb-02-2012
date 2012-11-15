package br.ufg.inf.es.base.validation.annotations;

import br.ufg.inf.es.base.validation.Validation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Cézar Augusto Ferreira
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface Validator {

    Class<? extends Validation> validatorClass();
}