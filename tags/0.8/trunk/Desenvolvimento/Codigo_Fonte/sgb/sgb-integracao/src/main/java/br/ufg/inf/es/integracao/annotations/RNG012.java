/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.annotations;

import br.ufg.inf.es.base.validation.annotations.Validator;
import br.ufg.inf.es.integracao.rng.AutorValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interface de regra de negócio para validar inserção e alteração de Autor.
 * 
 * @author Cassio Augusto Silva de Freitas
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Validator(validatorClass = AutorValidator.class)
public @interface RNG012 {
    
}
