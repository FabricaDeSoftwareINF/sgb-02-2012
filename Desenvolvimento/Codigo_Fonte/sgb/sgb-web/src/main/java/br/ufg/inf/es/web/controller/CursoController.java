package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.CursoService;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.web.controller.form.CursoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Diogo Gon&ccedil;alves Teodoro
 *
 */
@Component
@Scope("session")
public class CursoController extends SGBController<Curso, CursoForm, CursoService> {

    @Autowired
    private CursoForm form;
    @Autowired
    private CursoService service;

    @Override
    public CursoForm getForm() {
        return form;
    }

    public void setForm(CursoForm form) {
        this.form = form;
    }

    @Override
    public CursoService getService() {
        return service;
    }

    public void setService(CursoService service) {
        this.service = service;
    }
}
