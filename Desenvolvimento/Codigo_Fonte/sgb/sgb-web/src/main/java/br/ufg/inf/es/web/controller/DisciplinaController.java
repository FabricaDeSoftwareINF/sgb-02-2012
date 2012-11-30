/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.CursoService;
import br.ufg.inf.es.integracao.DisciplinaService;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.web.controller.form.CursoForm;
import br.ufg.inf.es.web.controller.form.DisciplinaForm;
import br.ufg.inf.es.web.controller.form.LivroForm;
import com.lowagie.tools.concat_pdf;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    @Autowired
    private CursoForm cursoForm;
    
    @Autowired
    private LivroForm livroForm;

    @Override
    public void initData() {
        super.initData();
        this.form.getEntity().setCurso(new Curso());
   
    }

    
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

    public CursoForm getCursoForm() {
        return cursoForm;
    }

    public void setCursoForm(CursoForm cursoForm) {
        this.cursoForm = cursoForm;
    }

    public LivroForm getLivroForm() {
        return livroForm;
    }

    public void setLivroForm(LivroForm livroForm) {
        this.livroForm = livroForm;
    }

    @Override
    public void insert() {
        try {
            this.service.insert(this.form.getEntity());
            
        } catch (ValidationException ex) {
            this.addWarningMessage(ex.getKeyMessage());
        }
    }

    @Override
    public String openInitialPage() {
        return super.openInitialPage();
    }
    
    
    
    
    
}
