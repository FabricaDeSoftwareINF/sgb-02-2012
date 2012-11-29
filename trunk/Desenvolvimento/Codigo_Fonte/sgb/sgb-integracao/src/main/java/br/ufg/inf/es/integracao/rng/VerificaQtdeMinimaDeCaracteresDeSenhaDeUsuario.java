/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Usuario;

/**
 *
 * @author Luã
 */
public class VerificaQtdeMinimaDeCaracteresDeSenhaDeUsuario implements Validation<Usuario> {

    public void validate(Usuario object) throws ValidationException {
        
        if (object.getSenha().length() < 6) {              
            throw new ValidationException("cadastro.usuario.msg.RNG002");
        }
        
    }
    
}
