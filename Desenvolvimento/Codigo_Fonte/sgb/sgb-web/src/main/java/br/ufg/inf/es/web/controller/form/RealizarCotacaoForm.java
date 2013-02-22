package br.ufg.inf.es.web.controller.form;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.ufg.inf.es.model.CotacoesLivro;
import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.datamodel.CotacoesLivroDataModel;
import br.ufg.inf.es.web.datamodel.LivroDataModel;

/**
 *
 * @author vinicius
 */
@Component
@Scope("session")
public class RealizarCotacaoForm extends GenericForm<ListaCotacao> {

    private LivroDataModel livrosCotacao;
    private LivroDataModel livroDataModel;
    private Livro[] livrosSelecionados;
    private CotacoesLivro[] cotacoesSelecionadas;
    private CotacoesLivroDataModel cotacoesDataModel;
    private ListaCompras listaCompras;
    private String nomeLista;

    public CotacoesLivro[] getCotacoesSelecionadas() {
        return this.cotacoesSelecionadas;
    }

    public void setCotacoesSelecionadas(CotacoesLivro[] cotacoesSelecionadas) {
        this.cotacoesSelecionadas = cotacoesSelecionadas;
    }

    public LivroDataModel getLivrosCotacao() {
        return livrosCotacao;
    }

    public void setLivrosCotacao(LivroDataModel livrosCotacao) {
        this.livrosCotacao = livrosCotacao;
    }

    public Livro[] getLivrosSelecionados() {

        return livrosSelecionados;
    }

    public void setLivrosSelecionados(Livro[] livrosSelecionados) {

        this.livrosSelecionados = livrosSelecionados;
    }

    public CotacoesLivroDataModel getCotacoesDataModel() {
        return cotacoesDataModel;
    }

    public void setCotacoesDataModel(CotacoesLivroDataModel cotacoesDataModel) {
        this.cotacoesDataModel = cotacoesDataModel;
    }

    public LivroDataModel getLivroDataModel() {
        return livroDataModel;
    }

    public void setLivroDataModel(LivroDataModel livroDataModel) {
        this.livroDataModel = livroDataModel;
    }

    public ListaCompras getListaCompras() {

        return this.listaCompras;
    }

    public void setListaCompras(final ListaCompras listaCompras) {

        this.listaCompras = listaCompras;
    }

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public double getValorTotal() {
        double valorTotal = 0;
        if (cotacoesSelecionadas != null) {
            for (CotacoesLivro cotacoesLivro : cotacoesSelecionadas) {
                valorTotal += cotacoesLivro.getValorMedio() * cotacoesLivro.getQuantidade();
            }
        }
        return valorTotal;
    }
}
