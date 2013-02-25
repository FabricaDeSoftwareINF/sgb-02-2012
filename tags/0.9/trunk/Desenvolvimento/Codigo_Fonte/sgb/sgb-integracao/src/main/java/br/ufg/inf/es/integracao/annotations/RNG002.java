package br.ufg.inf.es.integracao.annotations;

import br.ufg.inf.es.base.validation.annotations.Validator;
import br.ufg.inf.es.integracao.rng.VerificaQtdeMinimaDeCaracteresDeSenhaDeUsuario;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interface de regra de neg&oacute;cio para validar inser&ccedil;&atilde;o da 
 * quantidade m&iacute;nima de caracteres da senha de usu&aacute;rio.
 * @author Luã
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Validator(validatorClass = VerificaQtdeMinimaDeCaracteresDeSenhaDeUsuario.class)
public @interface RNG002 {
    
}
