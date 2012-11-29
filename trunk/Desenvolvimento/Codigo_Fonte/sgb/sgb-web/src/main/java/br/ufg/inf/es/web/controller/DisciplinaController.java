/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.CursoService;
import br.ufg.inf.es.integracao.DisciplinaService;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.web.controller.form.CursoForm;
import br.ufg.inf.es.web.controller.form.DisciplinaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Diogo
 */
@Component
@Scope("session")
public class DisciplinaController extends SGBController<Disciplina, DisciplinaForm, DisciplinaService> {

    @Autowired
    private DisciplinaForm form;
    @Autowired
    private DisciplinaService service;
    @Autowired
    private CursoService cursoService;

    @Override
    public DisciplinaForm getForm() {
        return form;
    }

    @Override
    public DisciplinaService getService() {
        return service;
    }

    public void setService(DisciplinaService service) {
        this.service = service;
    }

    public void setForm(DisciplinaForm form) {
        this.form = form;
    }

    public CursoService getCursoService() {
        return cursoService;
    }

    public void setCursoService(CursoService cursoService) {
        this.cursoService = cursoService;
    }
}
