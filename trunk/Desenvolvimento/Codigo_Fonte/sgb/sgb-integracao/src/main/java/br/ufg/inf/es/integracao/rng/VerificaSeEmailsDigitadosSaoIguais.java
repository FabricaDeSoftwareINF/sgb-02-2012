package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Usuario;
import org.springframework.stereotype.Component;

/**
 *
 * @author Luã
 */
@Component
public class VerificaSeEmailsDigitadosSaoIguais extends Validation<Usuario> {

    /**
     * Valida o email do usuario
     *
     * @param object
     * @throws ValidationException
     */
    public void validate(Usuario object) throws ValidationException {

        if (isInvalid(object.getEmail())
                || isInvalid(object.getConfirmacaoEmail())
                || !object.getEmail().equals(object.getConfirmacaoEmail())) {
            throw new ValidationException("cadastro.usuario.msg.RNG003");
        }

    }
}
