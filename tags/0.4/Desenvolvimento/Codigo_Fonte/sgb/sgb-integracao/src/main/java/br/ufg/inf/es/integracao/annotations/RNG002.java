/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.annotations;

import br.ufg.inf.es.base.validation.annotations.Validator;
import br.ufg.inf.es.integracao.rng.DadosUsuarioValidator;
import br.ufg.inf.es.integracao.rng.VerificaQtdeMinimaDeCaracteresDeSenhaDeUsuario;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Luã
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Validator(validatorClass = VerificaQtdeMinimaDeCaracteresDeSenhaDeUsuario.class)
public @interface RNG002 {
    
}
