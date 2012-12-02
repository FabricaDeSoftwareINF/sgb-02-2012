package br.ufg.inf.es.integracao.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.ufg.inf.es.base.validation.annotations.Validator;
import br.ufg.inf.es.integracao.rng.CursoValidator;

/**
 * @author Diogo Gon&ccedil;alves Teodoro
 * 
 *         US4 - Rotina de Cadastro de Curso
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Validator(validatorClass = CursoValidator.class)
public @interface RNG004 {

}
