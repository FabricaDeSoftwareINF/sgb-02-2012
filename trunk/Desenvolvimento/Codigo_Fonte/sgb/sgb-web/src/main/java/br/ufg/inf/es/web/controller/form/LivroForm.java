package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.enuns.EnumTipoBibliografia;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;

import br.ufg.inf.es.web.datamodel.LivroDataModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author cezar
 */
@Component
@Scope("session")
public class LivroForm extends GenericForm<Livro> {

    private Bibliografia bibliografiaTemp = new Bibliografia();
    private Livro[] selectedLivros;
    private Livro livroSelecionado;
    private LivroDataModel livroDM;
    private Collection<Livro> todosLivros;
    private String filtroTitulo;
    private Curso cursoSelecionado;
    private Collection<Autor> autoresAdicionados = new ArrayList<Autor>();
    private Bibliografia bibliografiaRemocao = new Bibliografia();
    private LivroBiblioteca livroBiblioteca = new LivroBiblioteca();
    private LivroBiblioteca livroBibliotecaRemocao = new LivroBiblioteca();
    private Collection<LivroBiblioteca> livrosAssociados = new ArrayList<LivroBiblioteca>();

    public Bibliografia getBibliografiaTemp() {
        return bibliografiaTemp;
    }

    public void setBibliografiaTemp(Bibliografia bibliografiaTemp) {
        this.bibliografiaTemp = bibliografiaTemp;
    }

    public Livro[] getSelectedLivros() {

        Livro[] retorno = null;
        if (this.selectedLivros != null) {
            retorno = this.selectedLivros.clone();
        }

        return retorno;
    }

    public void setSelectedLivros(Livro[] selectedLivros) {
        this.selectedLivros = (Livro[]) selectedLivros.clone();
    }

    public Livro getLivroSelecionado() {
        return livroSelecionado;
    }

    public void setLivroSelecionado(Livro livroSelecionado) {
        this.livroSelecionado = livroSelecionado;
    }

    public LivroDataModel getLivroDM() {
        List<Livro> livros = new ArrayList<Livro>(this.getTodosLivros());
        livroDM = new LivroDataModel(livros);
        return livroDM;
    }

    public void setLivroDM(LivroDataModel livroDM) {
        this.livroDM = livroDM;
    }

    public Collection<Livro> getTodosLivros() {
        return todosLivros;
    }

    public void setTodosLivros(Collection<Livro> todosLivros) {
        this.todosLivros = todosLivros;
    }

    public String getFiltroTitulo() {
        return filtroTitulo;
    }

    public void setFiltroTitulo(String filtroTitulo) {
        this.filtroTitulo = filtroTitulo;
    }

    public List<EnumTipoBibliografia> getTiposBibliografia() {
        return Arrays.asList(EnumTipoBibliografia.values());
    }

    public void setTipoBibliografia(EnumTipoBibliografia tipoBibliografia) {
        bibliografiaTemp.setTipo(tipoBibliografia);
    }

    public EnumTipoBibliografia getTipoBibliografia() {
        return bibliografiaTemp.getTipo();
    }

    public Curso getCursoSelecionado() {
        return this.cursoSelecionado;
    }

    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }

    public Collection<Autor> getAutoresAdicionados() {
        return autoresAdicionados;
    }

    public void setAutoresAdicionados(Collection<Autor> autoresAdicionados) {
        this.autoresAdicionados = autoresAdicionados;
    }

    public Bibliografia getBibliografiaRemocao() {
        return bibliografiaRemocao;
    }

    public void setBibliografiaRemocao(Bibliografia bibliografiaRemocao) {
        this.bibliografiaRemocao = bibliografiaRemocao;
    }

    public LivroBiblioteca getLivroBiblioteca() {
        return livroBiblioteca;
    }

    public void setLivroBiblioteca(LivroBiblioteca livroBiblioteca) {
        this.livroBiblioteca = livroBiblioteca;
    }

    public LivroBiblioteca getLivroBibliotecaRemocao() {
        return livroBibliotecaRemocao;
    }

    public void setLivroBibliotecaRemocao(LivroBiblioteca livroBibliotecaRemocao) {
        this.livroBibliotecaRemocao = livroBibliotecaRemocao;
    }

    public Collection<LivroBiblioteca> getLivrosAssociados() {
        return livrosAssociados;
    }

    public void setLivrosAssociados(Collection<LivroBiblioteca> livrosAssociados) {
        this.livrosAssociados = livrosAssociados;
    }
}
