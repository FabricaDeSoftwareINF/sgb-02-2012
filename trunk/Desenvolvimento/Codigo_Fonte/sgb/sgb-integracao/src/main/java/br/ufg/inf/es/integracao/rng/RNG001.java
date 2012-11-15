package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Usuario;
import org.springframework.stereotype.Component;

/**
 * @author Cézar Augusto Ferreira
 */
@Component
public class RNG001 implements Validation<Usuario> {

    public void validate(Usuario object) throws ValidationException {
        
        if (object.getSenha() == null || object.getSenha().length() < 8) {
            
            throw new ValidationException("message.rng001");
        }
    }   
}