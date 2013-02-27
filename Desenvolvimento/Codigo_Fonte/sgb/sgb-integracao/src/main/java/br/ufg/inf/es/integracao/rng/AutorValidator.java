package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Autor;
import org.springframework.stereotype.Component;

/**
 * Classe Validação com método contendo a regra de negócio a ser validada.
 *
 * @author Cassio Augusto Silva de Freitas
 */
@Component
public class AutorValidator extends Validation<Autor> {

    /**
     * Método Responsável por validar os campos do a Entidade Autor
     *
     * @param object a ser validado
     * @throws ValidationException
     */
    public void validate(Autor object) throws ValidationException {
        boolean nomeInvalido = isInvalid(object.getNome());
        boolean sobrenomeInvalido = isInvalid(object.getSobrenome());

        if (nomeInvalido && sobrenomeInvalido) {
            throw new ValidationException("cadastro.autor.label.RNG012.nomeEsobreNome");

        } 
        if (nomeInvalido) {
            throw new ValidationException("cadastro.autor.label.RNG012.nome");

        } 
        if (sobrenomeInvalido) {
            throw new ValidationException("cadastro.autor.label.RNG012.sobrenome");

        }
    }
}