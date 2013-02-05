/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Autor;
import org.springframework.stereotype.Component;

/**
 * Classe Validação com método contendo a regra de negócio a ser validada.
 * @author Cassio Augusto Silva de Freitas
 */
@Component
public class AutorValidator implements Validation<Autor> {

    /**
     * Método Responsável por validar os campos do a Entidade Autor
     * @param object a ser validado
     * @throws ValidationException 
     */
    public void validate(Autor object) throws ValidationException {
       
        if ((object.getNome() == null || object.getNome().trim().equals("")) && 
                (object.getSobrenome() == null || object.getSobrenome().trim().equals(""))) {
      
            throw new ValidationException("cadastro.autor.label.RNG012.nomeEsobreNome");
       
        } else if (object.getNome() == null || object.getNome().trim().equals("")) {
       
            throw new ValidationException("cadastro.autor.label.RNG012.nome");
      
        } else if (object.getSobrenome() == null || object.getSobrenome().trim().equals("")) {
       
            throw new ValidationException("cadastro.autor.label.RNG012.sobrenome");
       
        }
   
    }
}
