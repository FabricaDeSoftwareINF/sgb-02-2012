package br.ufg.inf.es.web.controller.form;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import java.util.LinkedList;

/**
 * @author Diogo Gon&ccedil;alves Teodoro
 *
 */
@Component
@Scope("session")
public class CursoForm extends GenericForm<Curso> {

    private Disciplina disciplinaSelecionada;
    private Disciplina disciplinaToRemove;
    
    private LinkedList<Disciplina> listaDisc;
    private LinkedList<Disciplina> listaBanco;

  
    
     public LinkedList<Disciplina> getListaDisc() {
        if (listaDisc == null) {
            listaDisc = new LinkedList<Disciplina>();
        }
        return listaDisc;

    }
    
     public void addDisc(Disciplina disciplina){
         getListaDisc().add(disciplina);
     }

    public void setListaDisc(LinkedList<Disciplina> listaDisc) {
        this.listaDisc = listaDisc;
    }

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
      public LinkedList<Disciplina> getListaBanco() {
        return listaBanco;
    }

    public void setListaBanco(LinkedList<Disciplina> listaBanco) {
        this.listaBanco = listaBanco;
    }
}

