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
 * @author igor
 */
@Component
public class UsuarioValidador extends Validation<Usuario>{
    
    public void validate(Usuario object) throws ValidationException {

        if (isInvalid(object.getNome()))  {
            throw new ValidationException("usuario.RNG003.nome");
            
        } else if (isInvalid(object.getSobrenome()))  {
            throw new ValidationException("usuario.RNG003.sobrenome");
            
        } else if (isInvalid(object.getEmail()))  {
            throw new ValidationException("usuario.RNG003.email");
            
        } else if (isInvalid(object.getConfirmacaoEmail()))  {
            throw new ValidationException("usuario.RNG003.confirmaemail");
            
        } else if (isInvalid(object.getSenha()))  {
            throw new ValidationException("usuario.RNG003.senha");
            
        } else if (isInvalid(object.getPerfil()))  {
            throw new ValidationException("usuario.RNG003.perfil");
            
        }         
   }
}
