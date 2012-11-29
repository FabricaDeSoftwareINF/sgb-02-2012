/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.base.validation.annotations.Validator;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.Usuario;
import org.springframework.stereotype.Component;

/**
 *
 * @author cezar
 */
@Component
public class TituloLivroValidator implements Validation<Livro>{
    
    public void validate(Livro object) throws ValidationException {
        
        if (object.getTitulo() == null || object.getTitulo().isEmpty()) {
            
            throw new ValidationException("cadastro.livro.msg.RNG000");
        }
    }
    
}
