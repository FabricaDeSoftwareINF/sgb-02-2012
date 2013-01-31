/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.*;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rodrigo Andrade
 */
@Component
public class DisciplinaValidator implements Validation<Disciplina> {

    public void validate(Disciplina object) throws ValidationException {

        if ((object.getNome() == null || object.getNome().equals(""))
                && (object.getCodigo() == null || object.getCodigo().equals(""))
                && (object.getCurso() == null)) {

            throw new ValidationException("cadastro.disciplina.msg.RNG006.nomeCodigoCurso");

        } else if ((object.getNome() == null || object.getNome().equals(""))
                && (object.getCodigo() == null || object.getCodigo().equals(""))
                && (object.getCurso() != null)) {

            throw new ValidationException("cadastro.disciplina.msg.RNG006.nomeCodigo");

        } else if (object.getCurso() == null
                && (object.getNome() != null || !object.getNome().equals(""))
                && (object.getCodigo() == null || object.getCodigo().equals(""))) {

            throw new ValidationException("cadastro.disciplina.msg.RNG006.codigoCurso");

        } else if ((object.getNome() == null || object.getNome().equals(""))
                && (object.getCodigo() != null || !object.getCodigo().equals(""))
                && object.getCurso() == null) {

            throw new ValidationException("cadastro.disciplina.msg.RNG006.nomeCurso");

        } else if (object.getNome() == null || object.getNome().equals("")) {

            throw new ValidationException("cadastro.disciplina.msg.RNG006.nome");

        } else if (object.getCodigo() == null || object.getCodigo().equals("")) {

            throw new ValidationException("cadastro.disciplina.msg.RNG006.codigo");

        } else if (object.getCurso() == null) {

            throw new ValidationException("cadastro.disciplina.msg.RNG006.curso");

        }
    }
}