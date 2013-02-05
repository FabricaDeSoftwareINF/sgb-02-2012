
package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Editora;
import org.springframework.stereotype.Component;

/**
 * Classe de validação da Entidade Editora
 * @author Cássio Augusto Silva de Freitas
 */
@Component
public class EditoraValidator implements Validation<Editora> {
    
    public static final String KEY_RNG007 = "label.RNG07.nome";

    /**
     * Método responsável por validar o estados dos atributos do Objeto Editora
     * @param object Editora
     * @throws ValidationException 
     */
    public void validate(Editora object) throws ValidationException {
        
        if(object.getNome() == null || object.getNome().trim().equals("")) {
            
            throw new ValidationException(EditoraValidator.KEY_RNG007);
        
        }
    }
    
       
}
