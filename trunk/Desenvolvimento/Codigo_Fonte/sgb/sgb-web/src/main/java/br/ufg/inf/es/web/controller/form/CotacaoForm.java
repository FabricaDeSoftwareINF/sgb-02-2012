package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.model.ItemListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.datamodel.ItemListaCotacaoDataModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Bruno Marquete
 */
@Component
@Scope("session")
public class CotacaoForm extends GenericForm<ItemListaCotacao> {

    private Collection<ItemListaCotacao> tabelaCotacoes;
    private Collection<Livro> livrosCotacao;
    private Livro[] livrosParaCotacao;
    private ItemListaCotacaoDataModel cotacaoDataModel;
    private Cotacao[] cotacoesSelecionadas;

    public ItemListaCotacaoDataModel getCotacaoDataModel() {

        List<ItemListaCotacao> cotacoes = new ArrayList<ItemListaCotacao>(this.getTabelaCotacoes());

        cotacaoDataModel = new ItemListaCotacaoDataModel(cotacoes);

        return cotacaoDataModel;
    }

    public void setCotacaoDataModel(ItemListaCotacaoDataModel cotacaoDataModel) {

        this.cotacaoDataModel = cotacaoDataModel;
    }

    public Cotacao[] getCotacoesSelecionadas() {

        Cotacao[] retorno = null;

        if (this.cotacoesSelecionadas != null) {

            retorno = this.cotacoesSelecionadas.clone();
        }

        return retorno;
    }

    public void setCotacoesSelecionadas(Cotacao[] cotacoesSelecionadas) {

        if (cotacoesSelecionadas != null) {

            this.cotacoesSelecionadas = (Cotacao[]) cotacoesSelecionadas.clone();
        }
    }

    public Collection<ItemListaCotacao> getTabelaCotacoes() {
        return tabelaCotacoes;
    }

    public void setTabelaCotacoes(Collection<ItemListaCotacao> tabelaCotacoes) {
        this.tabelaCotacoes = tabelaCotacoes;
    }

    public Collection<Livro> getLivrosCotacao() {

        return livrosCotacao;
    }

    public void setLivrosCotacao(Collection<Livro> livrosCotacao) {

        this.livrosCotacao = livrosCotacao;
    }

    public Livro[] getLivrosParaCotacao() {
        return livrosParaCotacao.clone();
    }

    public void setLivrosParaCotacao(Livro[] livrosParaCotacao) {
        this.livrosParaCotacao = livrosParaCotacao.clone();
    }
}
