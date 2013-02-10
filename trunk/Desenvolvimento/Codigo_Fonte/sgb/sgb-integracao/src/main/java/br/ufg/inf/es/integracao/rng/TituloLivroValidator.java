package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Livro;
import org.springframework.stereotype.Component;

/**
 *
 * @author cezar
 */
@Component
public class TituloLivroValidator extends Validation<Livro> {

    public void validate(Livro object) throws ValidationException {

        if (isInvalid(object.getTitulo())) {
            throw new ValidationException("cadastro.livro.msg.RNG000");
        }
    }
}
