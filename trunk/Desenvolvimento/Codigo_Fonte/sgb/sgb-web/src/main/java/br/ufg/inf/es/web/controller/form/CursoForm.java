package br.ufg.inf.es.web.controller.form;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;

/**
 * @author Diogo Gon&ccedil;alves Teodoro
 *
 */
@Component
@Scope("session")
public class CursoForm extends GenericForm<Curso> {

    private Disciplina disciplinaSelecionada;
    private Disciplina disciplinaToRemove;

    public Disciplina getDisciplinaSelecionada() {
        if(this.disciplinaSelecionada == null){
           disciplinaSelecionada = new Disciplina();
        }
        return disciplinaSelecionada;
    }

    public void setDisciplinaSelecionada(Disciplina disciplinaSelecionada) {
        this.disciplinaSelecionada = disciplinaSelecionada;
    }

    public Disciplina getDisciplinaToRemove() {
        return disciplinaToRemove;
    }

    public void setDisciplinaToRemove(Disciplina disciplinaToRemove) {
        this.disciplinaToRemove = disciplinaToRemove;
    }
}

