/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class DadosUsuarioValidator extends Validation<Usuario> {

    public void validate(Usuario object) throws ValidationException {

        if (isInvalid(object.getSenha())
                || isInvalid(object.getEmail())
                || isInvalid(object.getNome())
                || isInvalid(object.getSobrenome())) {
            throw new ValidationException("cadastro.usuario.msg.RNG001");
        }
    }
}
