/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Disciplina;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Diogo
 */
@Component
@Scope("session")
public class DisciplinaForm extends GenericForm<Disciplina> {
    
}
