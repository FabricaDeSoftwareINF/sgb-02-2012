package br.ufg.inf.es.web.controller.form;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import java.util.Collection;
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
    
    private Collection<Disciplina> listaDisciplinaAssociacao;
    
    private Collection<Disciplina> listaDisciplinaComboBox;

    public Collection<Disciplina> getListaDisciplinaAssociacao() {
        
        if (listaDisciplinaAssociacao == null) {
            
            listaDisciplinaAssociacao = new LinkedList<Disciplina>();
        }
        
        return listaDisciplinaAssociacao;
    }

    public void setListaDisciplinaAssociacao(Collection<Disciplina> listaDisciplinaAssociacao) {
        
        this.listaDisciplinaAssociacao = listaDisciplinaAssociacao;
    }

    public Disciplina getDisciplinaSelecionada() {
        
        if (this.disciplinaSelecionada == null) {
            
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

    public Collection<Disciplina> getListaDisciplinaComboBox() {

        return listaDisciplinaComboBox;
    }

    public void setListaDisciplinaComboBox(Collection<Disciplina> listaDisciplinaComboBox) {
        
        this.listaDisciplinaComboBox = listaDisciplinaComboBox;
    }
}
