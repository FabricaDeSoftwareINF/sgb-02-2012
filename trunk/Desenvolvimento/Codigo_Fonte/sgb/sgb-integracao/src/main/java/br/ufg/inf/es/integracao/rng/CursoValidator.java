package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG004;
import br.ufg.inf.es.model.Curso;
import org.springframework.stereotype.Component;

/**
 * @author Diogo Gon&ccedil;alves Teodoro
 *
 * Validador da US4 - {@link RNG004}
 *
 * Nome do curso n&atilde;o aceita vazio. Quantidade de vagas s&oacute; permite
 * n&uacute;mero maior que zero (0)
 */
@Component
public class CursoValidator extends Validation<Curso> {

    public void validate(Curso curso) throws ValidationException {

        if (isInvalid(curso.getNome())) {
            throw new ValidationException("cadastro.curso.msg.RNG004.nome");

        } else if (curso.getVagas() <= 0) {
            throw new ValidationException("cadastro.curso.msg.RNG004.vagas");
        }
    }
}