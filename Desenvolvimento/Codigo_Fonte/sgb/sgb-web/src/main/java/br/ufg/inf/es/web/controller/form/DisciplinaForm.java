/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.enuns.EnumTipoBibliografia;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.Livro;
import java.util.Collection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Formulário responsável pela Estória de Usuário Manter Disciplina
 * @author Diogo, Marco Aurélio, Cássio Augusto Silva de Freitas
 */
@Component
@Scope("session")
public class DisciplinaForm extends GenericForm<Disciplina> {

    private Curso cursoSelecionado;
    
    private Collection<Curso> cursos;
    
    private Collection<EnumTipoBibliografia> tipoBibliografias;
    
    private EnumTipoBibliografia tipoBibliografiaSelecionado;
    
    private Collection<Livro> livrosSelecionados;
    
    private Collection<Bibliografia> bibliografiasAssociadas;
    
    private Boolean exibirDialogRemocao;
    
    private Collection<Livro> selecionadosAux;
    
    public Collection<Livro> getSelecionadosAux() {
        return selecionadosAux;
    }

    public void setSelecionadosAux(Collection<Livro> selecionadosAux) {
        this.selecionadosAux = selecionadosAux;
    }
  

    public Boolean getExibirDialogRemocao() {
        return exibirDialogRemocao;
    }

    public void setExibirDialogRemocao(Boolean exibirDialogRemocao) {
        this.exibirDialogRemocao = exibirDialogRemocao;
    }
    
    public Collection<Bibliografia> getBibliografiasAssociadas() {
        return bibliografiasAssociadas;
    }

    public void setBibliografiasAssociadas(Collection<Bibliografia> bibliografiasAssociadas) {
        this.bibliografiasAssociadas = bibliografiasAssociadas;
    }

    public Collection<Livro> getLivrosSelecionados() {
        return livrosSelecionados;
    }

    public void setLivrosSelecionados(Collection<Livro> livrosSelecionados) {
        this.livrosSelecionados = livrosSelecionados;
    }

    public EnumTipoBibliografia getTipoBibliografiaSelecionado() {
        return tipoBibliografiaSelecionado;
    }

    public void setTipoBibliografiaSelecionado(EnumTipoBibliografia tipoBibliografiaSelecionado) {
        this.tipoBibliografiaSelecionado = tipoBibliografiaSelecionado;
    }

    public Collection<EnumTipoBibliografia> getTipoBibliografias() {
        return tipoBibliografias;
    }

    public void setTipoBibliografias(Collection<EnumTipoBibliografia> tipoBibliografias) {
        this.tipoBibliografias = tipoBibliografias;
    }
    
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
