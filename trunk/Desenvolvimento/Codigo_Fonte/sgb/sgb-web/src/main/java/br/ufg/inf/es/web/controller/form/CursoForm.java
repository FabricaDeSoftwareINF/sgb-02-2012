package br.ufg.inf.es.web.controller.form;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import br.ufg.inf.es.web.datamodel.CursoDataModel;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Formulário utilizado pelo <code>CursoController</code> para trabalhar com os dados da tela.
 * 
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
    
    private boolean exibirDialog;
    
    private CursoDataModel cursoDataTableModel;
    
    private Curso[] cursosParaRemocao;
    
    private Curso cursoParaDetalhe;

    public Collection<Disciplina> getListaDisciplinaAssociacao() {
        
        if (listaDisciplinaAssociacao == null) {
            
            this.listaDisciplinaAssociacao = new LinkedList<Disciplina>();
        }
        
        return this.listaDisciplinaAssociacao;
    }

    public void setListaDisciplinaAssociacao(final Collection<Disciplina> listaDisciplinaAssociacao) {
        
        this.listaDisciplinaAssociacao = listaDisciplinaAssociacao;
    }

    public Disciplina getDisciplinaSelecionada() {
        
        if (this.disciplinaSelecionada == null) {
            
            this.disciplinaSelecionada = new Disciplina();
        }
        
        return this.disciplinaSelecionada;
    }

    public void setDisciplinaSelecionada(final Disciplina disciplinaSelecionada) {
        
        this.disciplinaSelecionada = disciplinaSelecionada;
    }

    public Disciplina getDisciplinaToRemove() {
        
        return this.disciplinaToRemove;
    }

    public void setDisciplinaToRemove(final Disciplina disciplinaToRemove) {
        
        this.disciplinaToRemove = disciplinaToRemove;
    }

    public Collection<Disciplina> getListaDisciplinaComboBox() {

        return this.listaDisciplinaComboBox;
    }

    public void setListaDisciplinaComboBox(final Collection<Disciplina> listaDisciplinaComboBox) {
        
        this.listaDisciplinaComboBox = listaDisciplinaComboBox;
    }

    public boolean isExibirDialog() {
        
        return exibirDialog;
    }

    public void setExibirDialog(final boolean exibirDialog) {
     
        this.exibirDialog = exibirDialog;
    }    
    
    public CursoDataModel getCursoDataTableModel() {
        
        return this.cursoDataTableModel;
    }

    public void setCursoDataTableModel(final CursoDataModel cursoDataModel) {
     
        this.cursoDataTableModel = cursoDataModel;
    }    

    public Curso[] getCursosParaRemocao() {
        
        return this.cursosParaRemocao;
    }

    public void setCursosParaRemocao(final Curso[] cursosParaRemocao) {
     
        this.cursosParaRemocao = cursosParaRemocao;
    }

    public Curso getCursoParaDetalhe() {
        
        return cursoParaDetalhe;
    }

    public void setCursoParaDetalhe(Curso cursoParaDetalhe) {
        
        this.cursoParaDetalhe = cursoParaDetalhe;
    }
}
