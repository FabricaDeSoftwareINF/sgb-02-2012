package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Usuario;
import org.springframework.stereotype.Component;

/**
 * Validador da entidade Usuario
 * 
 * @author Luã
 */
@Component
public class DadosUsuarioValidator extends Validation<Usuario> {

    /**
     * Valida os dados de um usuario
     * @param object
     * @throws ValidationException 
     */
    public void validate(Usuario object) throws ValidationException {

        if (isInvalid(object.getSenha())
                || isInvalid(object.getEmail())
                || isInvalid(object.getNome())
                || isInvalid(object.getSobrenome())) {
            throw new ValidationException("cadastro.usuario.msg.RNG001");
        }
    }
}
