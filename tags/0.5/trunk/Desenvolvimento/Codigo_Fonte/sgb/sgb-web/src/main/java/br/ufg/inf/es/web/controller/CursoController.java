package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.CursoService;
import br.ufg.inf.es.integracao.DisciplinaService;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.web.controller.form.CursoForm;
import java.util.LinkedList;
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
    @Autowired
    private DisciplinaService disciplinaService;
    private LinkedList<Disciplina> listaDisc;
    
    public void salvarCurso() throws ValidationException{
        
        Curso curso;
        
        curso = this.getForm().getEntity();
            
        long id = service.insert(curso);
        
        curso = this.getService().find(id);
        for (Disciplina disc : listaDisc)  {
            disc.setCurso(curso);
            disciplinaService.update(disc);
        }      
//        curso.setDisciplinas(this.getForm().getListaDisc());
        
        service.update(curso);
           
    }

    public void addDisciplina() {

        long id = this.getForm().getDisciplinaSelecionada().getId();
        Disciplina disciplina = disciplinaService.find(id);
        disciplina.setCurso(this.getForm().getEntity());
        this.getForm().addDisc(disciplina);
//        this.getForm().getEntity().setDisciplinas(this.getForm().getListaDisc());


//this.getForm().getEntity().setDisciplinas(listaDisc);
        //        if(this.getForm().getEntity().getDisciplinas() == null){
        //            this.getForm().getEntity().setDisciplinas(new LinkedList<Disciplina>());
        //        }
        //        this.getForm().getEntity().getDisciplinas().add(
        //                this.getDisciplinaService().find(this.getForm().getDisciplinaSelecionada().getId()));
    }

    public void removeDisciplina() {
        this.getForm().getEntity().getDisciplinas().remove(this.getForm().getDisciplinaToRemove());
    }

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

    public DisciplinaService getDisciplinaService() {
        return disciplinaService;
    }

    public void setDisciplinaService(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

   
}
