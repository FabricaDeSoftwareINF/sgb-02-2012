
package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.CursoService;
import br.ufg.inf.es.integracao.DisciplinaService;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.web.controller.form.CursoForm;
import br.ufg.inf.es.web.controller.form.DisciplinaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Diogo, Marco Aurélio
 */
@Component
@Scope("session")
public class DisciplinaController extends SGBController<Disciplina, DisciplinaForm, DisciplinaService> {
    
    private static final String KEY_SUCESSO = "arquitetura.msg.sucesso";

    @Autowired
    private DisciplinaForm form;
    
    @Autowired
    private DisciplinaService service;
    
    @Autowired
    private CursoService cursoService;
    
    @Autowired
    private CursoForm cursoForm;
    
    @Override
    public void initData() {
        
        super.initData();
     
        this.form.getEntity().setCurso(new Curso());
        
        this.getForm().setCursos(this.getCursoService().list());
   
    }

    
    @Override
    public DisciplinaForm getForm() {
        return form;
    }

    @Override
    public DisciplinaService getService() {
        return this.service;
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

    @Override
    public void insert() {
        
        try {
         
            this.service.insert(this.form.getEntity());
            
            this.addSuccessMessage(DisciplinaController.KEY_SUCESSO);
            
        } catch (ValidationException ex) {
      
            this.addWarningMessage(ex.getKeyMessage());
      
        }
    }

}
