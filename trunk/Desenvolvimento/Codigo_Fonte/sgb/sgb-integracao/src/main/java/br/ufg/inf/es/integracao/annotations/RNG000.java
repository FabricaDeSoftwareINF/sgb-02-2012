package br.ufg.inf.es.integracao.annotations;

import br.ufg.inf.es.base.validation.annotations.Validator;
import br.ufg.inf.es.integracao.rng.TituloLivroValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interface de regra de neg&oacute;cio para validar inser&ccedil;&atilde;o  
 * e altera&ccedil;&atilde;o de <code>Titulo do Livro</code>.
 * @author cezar
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Validator(validatorClass = TituloLivroValidator.class)
public @interface RNG000 {
    
}
