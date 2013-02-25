package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.AutorDTO;
import br.ufg.inf.es.web.datamodel.AutorDataModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Cézar Augusto Ferreira, Cássio Augusto
 */
@Component
@Scope("session")
public class AutorForm extends GenericForm<Autor> {

    private String filtroNome;
    private AutorDataModel model;
    private AutorDTO autorEdicao;
    private Long idAutorEdicao;
    private AutorDTO[] autoresSelecionados;
    private Boolean exibirDialogExclusao;
    private Collection<String> nomesSelecionados;
    /*
     * Todos os autores
     */
    private Collection<AutorDTO> todosAutores;

    /**
     * obtem o valor
     *
     * @return valor
     */
    public Boolean getExibirDialogExclusao() {
        return exibirDialogExclusao;
    }

    /**
     * define um novo valor
     *
     * @param exibirDialogExclusao
     */
    public void setExibirDialogExclusao(Boolean exibirDialogExclusao) {
        this.exibirDialogExclusao = exibirDialogExclusao;
    }

    /**
     * obtem o valor
     *
     * @return valor
     */
    public Long getIdAutorEdicao() {
        return idAutorEdicao;
    }

    /**
     * define um novo valor
     *
     * @param idAutorEdicao
     */
    public void setIdAutorEdicao(Long idAutorEdicao) {
        this.idAutorEdicao = idAutorEdicao;
    }

    /**
     * obtem o valor
     *
     * @return valor
     */
    public AutorDTO getAutorEdicao() {
        return autorEdicao;
    }

    /**
     * define um novo valor
     *
     * @param autorEdicao
     */
    public void setAutorEdicao(AutorDTO autorEdicao) {
        this.autorEdicao = autorEdicao;
    }

    /**
     * obtem o valor
     *
     * @return valor
     */
    public String getFiltroNome() {
        return filtroNome;
    }

    /**
     * define um novo valor
     *
     * @param filtroNome
     */
    public void setFiltroNome(String filtroNome) {
        this.filtroNome = filtroNome;
    }

    /**
     * obtem o valor
     *
     * @return valor
     */
    public AutorDTO[] getAutoresSelecionados() {

        AutorDTO[] retorno = null;

        if (this.autoresSelecionados != null) {

            retorno = this.autoresSelecionados.clone();
        }

        return retorno;
    }

    /**
     * define um novo valor
     *
     * @param autoresSelecionados
     */
    public void setAutoresSelecionados(AutorDTO[] autoresSelecionados) {

        this.autoresSelecionados = (AutorDTO[]) autoresSelecionados.clone();
    }

    /**
     * obtem o valor
     *
     * @return valor
     */
    public AutorDataModel getModel() {

        List<AutorDTO> autores = new ArrayList<AutorDTO>(this.getTodosAutores());

        model = new AutorDataModel(autores);

        return model;
    }

    /**
     * define um novo valor
     *
     * @param model
     */
    public void setModel(AutorDataModel model) {
        this.model = model;
    }

    /**
     * obtem o valor
     *
     * @return valor
     */
    public Collection<AutorDTO> getTodosAutores() {
        return todosAutores;
    }

    /**
     * define um novo valor
     *
     * @param todosAutores
     */
    public void setTodosAutores(Collection<AutorDTO> todosAutores) {
        this.todosAutores = todosAutores;
    }

    /**
     * obtem o valor
     *
     * @return valor
     */
    public Collection<String> getNomesSelecionados() {
        return nomesSelecionados;
    }

    /**
     * define um novo valor
     *
     * @param nomesSelecionados
     */
    public void setNomesSelecionados(Collection<String> nomesSelecionados) {
        this.nomesSelecionados = nomesSelecionados;
    }
}