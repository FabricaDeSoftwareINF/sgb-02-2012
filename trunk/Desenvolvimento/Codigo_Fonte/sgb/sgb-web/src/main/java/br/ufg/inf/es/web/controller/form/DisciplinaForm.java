/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import java.util.Collection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Diogo, Marco Aurélio
 */
@Component
@Scope("session")
public class DisciplinaForm extends GenericForm<Disciplina> {
    
    private Curso cursoSelecionado;

    private Collection<Curso> cursos;
    
    public Curso getCursoSelecionado() {
        return cursoSelecionado;
    }

    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }

    public Collection<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Collection<Curso> cursos) {
        this.cursos = cursos;
    }
}
