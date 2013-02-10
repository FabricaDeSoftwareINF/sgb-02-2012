package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.util.cripto.CriptoGeneric;
import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Comunicacao;
import java.util.Arrays;
import org.springframework.stereotype.Component;

/**
 *
 * @author igor
 */
@Component
public class ComunicacaoValidator extends Validation<Comunicacao> {

    /*
     * Método Responsável por validar os campos do a Entidade Comunicacao @param
     * object a ser validado @throws ValidationException
     */
    public void validate(Comunicacao object) throws ValidationException {
        byte[] vazioCripto = new CriptoGeneric().criptografa("");

        if (isInvalid(object.getService())) {
            throw new ValidationException("cadastro.autor.label.RNG001Parametros.service");

        } else if (isInvalid(object.getPort())) {
            throw new ValidationException("cadastro.autor.label.RNG001Parametros.port");

        } else if (isInvalid(object.getUsuario())) {
            throw new ValidationException("cadastro.autor.label.RNG001Parametros.usuario");

        } else if ((object.getSenha() == null) || (Arrays.equals(object.getSenha(), vazioCripto))) {
            throw new ValidationException("cadastro.autor.label.RNG001Parametros.senha");
        }
    }
}
