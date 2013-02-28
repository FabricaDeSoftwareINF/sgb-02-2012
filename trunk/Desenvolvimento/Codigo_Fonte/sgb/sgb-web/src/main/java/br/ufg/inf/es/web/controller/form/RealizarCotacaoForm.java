package br.ufg.inf.es.web.controller.form;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.ufg.inf.es.model.ItemListaCotacao;
import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.datamodel.ItemListaCotacaoDataModel;
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
    private ItemListaCotacao[] cotacoesSelecionadas;
    private ItemListaCotacaoDataModel cotacoesDataModel;
    private ListaCompras listaCompras;
    private String nomeLista;

    public ItemListaCotacao[] getCotacoesSelecionadas() {
        return this.cotacoesSelecionadas;
    }

    public void setCotacoesSelecionadas(ItemListaCotacao[] cotacoesSelecionadas) {
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

    public ItemListaCotacaoDataModel getCotacoesDataModel() {
        return cotacoesDataModel;
    }

    public void setCotacoesDataModel(ItemListaCotacaoDataModel cotacoesDataModel) {
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
            for (ItemListaCotacao cotacoesLivro : cotacoesSelecionadas) {
                valorTotal += cotacoesLivro.getValorMedio() * cotacoesLivro.getQuantidadeAComprar();
            }
        }
        return valorTotal;
    }
}
