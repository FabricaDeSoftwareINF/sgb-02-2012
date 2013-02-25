package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.enuns.EnumTipoBibliografia;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.datamodel.DisciplinaDataModel;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Formulário responsável pela Estória de Usuário Manter Disciplina
 *
 * @author Diogo, Marco Aurélio, Cássio Augusto Silva de Freitas
 */
@Component
@Scope("session")
public class DisciplinaForm extends GenericForm<Disciplina> {

    private Collection<Disciplina> disciplinasSelecionadas;
    private DisciplinaDataModel dataModelDisciplina;
    private Boolean exibirDialogDetalhe;
    private Disciplina disciplinaDetalhe;
    private Disciplina disciplinaEdicao;
    private Boolean isEditPage;
    private Bibliografia bibliografiaParaRemocao;
    private Curso cursoSelecionado;
    private Collection<Curso> cursos;
    private Collection<EnumTipoBibliografia> tipoBibliografias;
    private EnumTipoBibliografia tipoBibliografiaSelecionado;
    private Livro livroSelecionado;
    private Collection<Livro> selecionadosAux = new ArrayList<Livro>();
    private Bibliografia bibliografiaTmp = new Bibliografia();

    /**
     * Obtem o valor do atributo
     *
     * @return
     */
    public Collection<Disciplina> getDisciplinasSelecionadas() {
        return this.disciplinasSelecionadas;
    }

    /**
     * Define um novo valor para o atributo
     *
     * @param disciplinasSelecionadas
     */
    public void setDisciplinasSelecionadas(Collection<Disciplina> disciplinasSelecionadas) {
        this.disciplinasSelecionadas = disciplinasSelecionadas;
    }

    /**
     * Obtem o valor do atributo
     *
     * @return
     */
    public DisciplinaDataModel getDataModelDisciplina() {
        return dataModelDisciplina;
    }

    /**
     * Define um novo valor para o atributo
     *
     * @param disciplinasSelecionadas
     */
    public void setDataModelDisciplina(DisciplinaDataModel dataModelDisciplina) {
        this.dataModelDisciplina = dataModelDisciplina;
    }

    /**
     * Obtem o valor do atributo
     *
     * @return
     */
    public Boolean getExibirDialogDetalhe() {
        return exibirDialogDetalhe;
    }

    /**
     * Define um novo valor para o atributo
     *
     * @param disciplinasSelecionadas
     */
    public void setExibirDialogDetalhe(Boolean exibirDialogDetalhe) {
        this.exibirDialogDetalhe = exibirDialogDetalhe;
    }

    /**
     * Obtem o valor do atributo
     *
     * @return
     */
    public Disciplina getDisciplinaDetalhe() {
        return disciplinaDetalhe;
    }

    /**
     * Define um novo valor para o atributo
     *
     * @param disciplinasSelecionadas
     */
    public void setDisciplinaDetalhe(Disciplina disciplinaDetalhe) {
        this.disciplinaDetalhe = disciplinaDetalhe;
    }

    /**
     * Obtem o valor do atributo
     *
     * @return
     */
    public Boolean getIsEditPage() {
        return isEditPage;
    }

    /**
     * Define um novo valor para o atributo
     *
     * @param disciplinasSelecionadas
     */
    public void setIsEditPage(Boolean isEditPage) {
        this.isEditPage = isEditPage;
    }

    /**
     * Obtem o valor do atributo
     *
     * @return
     */
    public Disciplina getDisciplinaEdicao() {
        return disciplinaEdicao;
    }

    /**
     * Define um novo valor para o atributo
     *
     * @param disciplinasSelecionadas
     */
    public void setDisciplinaEdicao(Disciplina disciplinaEdicao) {
        this.disciplinaEdicao = disciplinaEdicao;
    }

    /**
     * Obtem o valor do atributo
     *
     * @return
     */
    public Bibliografia getBibliografiaParaRemocao() {
        return bibliografiaParaRemocao;
    }

    /**
     * Define um novo valor para o atributo
     *
     * @param disciplinasSelecionadas
     */
    public void setBibliografiaParaRemocao(Bibliografia bibliografiaParaRemocao) {
        this.bibliografiaParaRemocao = bibliografiaParaRemocao;
    }

    /**
     * Obtem o valor do atributo
     *
     * @return
     */
    public Collection<Livro> getSelecionadosAux() {
        return selecionadosAux;
    }

    /**
     * Define um novo valor para o atributo
     *
     * @param disciplinasSelecionadas
     */
    public void setSelecionadosAux(Collection<Livro> selecionadosAux) {
        this.selecionadosAux = selecionadosAux;
    }

    /**
     * Obtem o valor do atributo
     *
     * @return
     */
    public Livro getLivroSelecionado() {
        return livroSelecionado;
    }

    /**
     * Define um novo valor para o atributo
     *
     * @param disciplinasSelecionadas
     */
    public void setLivroSelecionado(Livro livroSelecionado) {
        this.livroSelecionado = livroSelecionado;
    }

    /**
     * Obtem o valor do atributo
     *
     * @return
     */
    public EnumTipoBibliografia getTipoBibliografiaSelecionado() {
        return tipoBibliografiaSelecionado;
    }

    /**
     * Define um novo valor para o atributo
     *
     * @param disciplinasSelecionadas
     */
    public void setTipoBibliografiaSelecionado(EnumTipoBibliografia tipoBibliografiaSelecionado) {
        this.tipoBibliografiaSelecionado = tipoBibliografiaSelecionado;
    }

    /**
     * Obtem o valor do atributo
     *
     * @return
     */
    public Collection<EnumTipoBibliografia> getTipoBibliografias() {
        return tipoBibliografias;
    }

    /**
     * Define um novo valor para o atributo
     *
     * @param disciplinasSelecionadas
     */
    public void setTipoBibliografias(Collection<EnumTipoBibliografia> tipoBibliografias) {
        this.tipoBibliografias = tipoBibliografias;
    }

    /**
     * Obtem o valor do atributo
     *
     * @return
     */
    public Curso getCursoSelecionado() {
        return cursoSelecionado;
    }

    /**
     * Define um novo valor para o atributo
     *
     * @param disciplinasSelecionadas
     */
    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }

    /**
     * Obtem o valor do atributo
     *
     * @return
     */
    public Collection<Curso> getCursos() {
        return cursos;
    }

    /**
     * Define um novo valor para o atributo
     *
     * @param disciplinasSelecionadas
     */
    public void setCursos(Collection<Curso> cursos) {
        this.cursos = cursos;
    }

    /**
     * Obtem o valor do atributo
     *
     * @return
     */
    public Bibliografia getBibliografiaTmp() {
        return bibliografiaTmp;
    }

    /**
     * Define um novo valor para o atributo
     *
     * @param disciplinasSelecionadas
     */
    public void setBibliografiaTmp(Bibliografia bibliografiaTmp) {
        this.bibliografiaTmp = bibliografiaTmp;
    }
}