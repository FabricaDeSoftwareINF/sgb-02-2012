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
public class VerificaQtdeMinimaDeCaracteresDeSenhaDeUsuario extends Validation<Usuario> {

    private static final int QTD_CARACTERES_MIN = 6;

    /**
     * valida a senha do usuario
     * @param object
     * @throws ValidationException 
     */
    public void validate(Usuario object) throws ValidationException {

        if (isInvalid(object.getSenha())
                || object.getSenha().length() < QTD_CARACTERES_MIN) {
            throw new ValidationException("cadastro.usuario.msg.RNG002");
        }
    }
}
