package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.datamodel.CotacaoDataModel;
import br.ufg.inf.es.web.datamodel.LivroDataModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
    private Cotacao[] cotacoesSelecionadas;
    private CotacaoDataModel cotacoesDataModel;
    
    public Cotacao[] getCotacoesSelecionadas() {
        return this.cotacoesSelecionadas;
    }

    public void setCotacoesSelecionadas(Cotacao[] cotacoesSelecionadas) {
        this.cotacoesSelecionadas = cotacoesSelecionadas;
    }

    public LivroDataModel getLivrosCotacao() {
        return livrosCotacao;
    }

    public void setLivrosCotacao(LivroDataModel livrosCotacao) {
        this.livrosCotacao = livrosCotacao;
    }

    public Livro[]  getLivrosSelecionados() {
        
        return livrosSelecionados;
    }

    public void setLivrosSelecionados(Livro[] livrosSelecionados) {
        
        this.livrosSelecionados = livrosSelecionados;
    }

    public CotacaoDataModel getCotacoesDataModel() {
        return cotacoesDataModel;
    }

    public void setCotacoesDataModel(CotacaoDataModel cotacoesDataModel) {
        this.cotacoesDataModel = cotacoesDataModel;
    }
    
    public LivroDataModel getLivroDataModel() {
        return livroDataModel;
    }

    public void setLivroDataModel(LivroDataModel livroDataModel) {
        this.livroDataModel = livroDataModel;
    }
    
}
