/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.DisciplinaService;
import br.ufg.inf.es.model.*;

/**
 *
 * @author Rodrigo Andrade
 */
public class DisciplinaValidator implements Validation<Disciplina> {

        public void validate(Disciplina object) throws ValidationException {

            if (object.getNome() == null) {
                throw new ValidationException("cadastro.disciplina.msg.RNG006.nome");
            } else if (object.getCodigo() == null) {
                throw new ValidationException("cadastro.disciplina.msg.RNG006.codigo");
                } else if (object.getCurso() == null) { 
                    throw new ValidationException("cadastro.disciplina.msg.RNG006.curso");
   
            }

        }
}