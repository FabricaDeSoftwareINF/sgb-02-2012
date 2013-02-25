package br.ufg.inf.es.integracao.annotations;

import br.ufg.inf.es.base.validation.annotations.Validator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import br.ufg.inf.es.integracao.rng.LivroValidator;
import java.lang.annotation.ElementType;

/**
 * Interface de regra de neg&oacute;cio para validar inser&ccedil;&atilde;o 
 * e altera&ccedil;&atilde;o de <code>Livro</code>.
 * @author vinicius
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Validator(validatorClass = LivroValidator.class)
public @interface RNG002Livro {
    
}
