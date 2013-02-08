package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.util.cripto.CriptoGeneric;
import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Comunicacao;
import org.springframework.stereotype.Component;

/**
 *
 * @author igor
 */
@Component
public class ComunicacaoValidator implements Validation<Comunicacao>{
    
    /* Método Responsável por validar os campos do a Entidade Comunicacao
     * @param object a ser validado
     * @throws ValidationException 
     */
    public void validate(Comunicacao object) throws ValidationException {
        byte[] vazioCripto = new CriptoGeneric().criptografa("");
        
        if ((object.getService() == null || object.getService().trim().equals(""))) {
      
            throw new ValidationException("cadastro.autor.label.RNG001Parametros.service");
       
        } else if ((object.getPort() == null || object.getPort().trim().equals(""))) {
      
            throw new ValidationException("cadastro.autor.label.RNG001Parametros.port");
       
        } else if ((object.getUsuario() == null || object.getUsuario().trim().equals(""))) {
      
            throw new ValidationException("cadastro.autor.label.RNG001Parametros.usuario");
       
        } else if ((object.getSenha() == null) || (object.getSenha().equals(vazioCripto))) {
      
            throw new ValidationException("cadastro.autor.label.RNG001Parametros.senha");
       
        }
    }       
}
