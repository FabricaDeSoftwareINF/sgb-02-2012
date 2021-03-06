package br.ufg.inf.es.integracao.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.ufg.inf.es.base.validation.annotations.Validator;
import br.ufg.inf.es.integracao.rng.DisciplinaValidator;

/**
 * Interface de regra de neg&oacute;cio para validar inser&ccedil;&atilde;o 
 * e altera&ccedil;&atilde;o de <code>Disciplina</code>.
 * @author Rodrigo Andrade
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Validator(validatorClass = DisciplinaValidator.class)
public @interface RNG006 {

}
