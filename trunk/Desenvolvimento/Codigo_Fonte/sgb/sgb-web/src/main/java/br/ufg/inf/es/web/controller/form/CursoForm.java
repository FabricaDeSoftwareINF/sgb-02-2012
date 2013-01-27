package br.ufg.inf.es.web.controller.form;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Diogo Gon&ccedil;alves Teodoro
 *
 */
@Component
@Scope("session")
public class CursoForm extends GenericForm<Curso> {

    private Disciplina disciplinaSelecionada;
    private Disciplina disciplinaToRemove;
    
    private List<Disciplina> listaDisc;
    private List<Disciplina> listaBanco;

  
    
     public List<Disciplina> getListaDisc() {
        if (listaDisc == null) {
            listaDisc = new LinkedList<Disciplina>();
        }
        return listaDisc;

    }
    
     public void addDisc(Disciplina disciplina){
         getListaDisc().add(disciplina);
     }

    public void setListaDisc(List<Disciplina> listaDisc) {
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
      public List<Disciplina> getListaBanco() {
        return listaBanco;
    }

    public void setListaBanco(List<Disciplina> listaBanco) {
        this.listaBanco = listaBanco;
    }
}

