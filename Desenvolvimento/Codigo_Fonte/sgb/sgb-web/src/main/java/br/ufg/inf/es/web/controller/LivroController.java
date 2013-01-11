/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.AutorService;
import br.ufg.inf.es.integracao.CursoService;
import br.ufg.inf.es.integracao.DisciplinaService;
import br.ufg.inf.es.integracao.EditoraService;
import br.ufg.inf.es.integracao.LivroService;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.controller.form.LivroForm;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author cezar
 */
@Component
@Scope("session")
public class LivroController extends SGBController<Livro, LivroForm, LivroService> {

    @Autowired
    private LivroForm form;
    
    @Autowired
    private LivroService service;
    
    @Autowired
    private DisciplinaService disciplinaService;
    
    @Autowired
    private CursoService cursoService;
    
    @Autowired
    private EditoraService editoraService;
    
    @Autowired
    private AutorService autorService;
    
    private Curso cursoSelecionado;

    public Curso getCursoSelecionado() {
        return cursoSelecionado;
    }

    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }
    
    private Collection<Curso> cursos;

    public LivroForm getForm() {
        return form;
    }

    public void setForm(LivroForm form) {
        this.form = form;
    }

    public LivroService getService() {
        return service;
    }

    public void setService(LivroService service) {
        this.service = service;
    }
    
    public void limparDisciplina(){
        this.form.getBibliografiaTemp().setDisciplina(new Disciplina());
    }

    public DisciplinaService getDisciplinaService() {
        return disciplinaService;
    }

    public void setDisciplinaService(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    public CursoService getCursoService() {
        return cursoService;
    }

    public void setCursoService(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    public Collection<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Collection<Curso> cursos) {
        this.cursos = cursos;
    }

    public EditoraService getEditoraService() {
        return editoraService;
    }

    public void setEditoraService(EditoraService editoraService) {
        this.editoraService = editoraService;
    }

    public AutorService getAutorService() {
        return autorService;
    }

    public void setAutorService(AutorService autorService) {
        this.autorService = autorService;
    }
    
    public void associarDisciplina(){
        this.form.getEntity().getBibliografia().add(this.form.getBibliografiaTemp());
    }
    public Collection<Livro> complete(String query) {  
        Collection<Livro> results = new ArrayList<Livro>();
          
        for(Livro autor : form.getCollectionEntities()){
            if(autor.getTitulo().contains(query)){
                results.add(autor);
            }
        }
          
        return results;  
    }
    @Override
    public String openInsertPage() {
        
        cursos = cursoService.list();
     
        this.openInsertView();
        return "/cadastro/livro/inclusao.xhtml";
        //return this.getRootNavigation() + "insertPage";
    }
    
    public void voltar() {

        
    }
}
