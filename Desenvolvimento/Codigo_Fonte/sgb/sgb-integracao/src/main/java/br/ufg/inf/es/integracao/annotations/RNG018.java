/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.annotations;

import br.ufg.inf.es.base.validation.annotations.Validator;
import br.ufg.inf.es.integracao.rng.DBBibliotecaConfigValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Classe que faz interface de regra de validação das regras da Configuração da 
 * integração do banco de dados da biblioteca
 * @author igor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Validator(validatorClass = DBBibliotecaConfigValidator.class)
public @interface RNG018 {
    
}

