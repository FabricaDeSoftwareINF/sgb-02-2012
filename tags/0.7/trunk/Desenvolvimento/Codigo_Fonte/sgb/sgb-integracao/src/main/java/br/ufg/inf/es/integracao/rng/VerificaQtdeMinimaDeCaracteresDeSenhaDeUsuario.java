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
public class VerificaQtdeMinimaDeCaracteresDeSenhaDeUsuario implements Validation<Usuario> {

    private static final int QTD_CARACTERES_MIN = 6;
    
    public void validate(Usuario object) throws ValidationException {
        
        if (object.getSenha().length() < QTD_CARACTERES_MIN) {              
            
            throw new ValidationException("cadastro.usuario.msg.RNG002");
        }        
    }
}
