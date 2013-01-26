/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.annotations;

import br.ufg.inf.es.base.validation.annotations.Validator;
import br.ufg.inf.es.integracao.rng.AutorValidator;
import br.ufg.inf.es.integracao.rng.EditoraValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interface de Regra de negócio de inserção e alteração de Editora.
 * 
 * @author Cássio Augusto Silva de Freitas
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Validator(validatorClass = EditoraValidator.class)
public @interface RNG007 {
    
}
    
