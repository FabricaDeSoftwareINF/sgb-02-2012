package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Disciplina;
import org.springframework.stereotype.Component;

/**
 * Regra de valida&ccedil;&atilde;o da entidade <code>Disciplina</code>
 * @author Rodrigo Andrade, Victor Carvalho
 */
@Component
public class DisciplinaValidator extends Validation<Disciplina> {

    /**
     * Valida um objeto Disciplina
     * @param object
     * @throws ValidationException 
     */
    public void validate(Disciplina object) throws ValidationException {
        boolean nomeInvalido = isInvalid(object.getNome());
        boolean cursoInvalido = isInvalid(object.getCurso());
        boolean codigoInvalido = isInvalid(object.getCodigo());

        if (nomeInvalido && codigoInvalido && cursoInvalido) {
            throw new ValidationException("cadastro.disciplina.msg.RNG006.nomeCodigoCurso");

        } else if (nomeInvalido && codigoInvalido && !cursoInvalido) {
            throw new ValidationException("cadastro.disciplina.msg.RNG006.nomeCodigo");

        } else if (cursoInvalido && !nomeInvalido && codigoInvalido) {
            throw new ValidationException("cadastro.disciplina.msg.RNG006.codigoCurso");

        } else if (nomeInvalido && !codigoInvalido && cursoInvalido) {
            throw new ValidationException("cadastro.disciplina.msg.RNG006.nomeCurso");

        } else if (nomeInvalido) {
            throw new ValidationException("cadastro.disciplina.msg.RNG006.nome");

        } else if (codigoInvalido) {
            throw new ValidationException("cadastro.disciplina.msg.RNG006.codigo");

        } else if (cursoInvalido) {
            throw new ValidationException("cadastro.disciplina.msg.RNG006.curso");
        }
    }
}