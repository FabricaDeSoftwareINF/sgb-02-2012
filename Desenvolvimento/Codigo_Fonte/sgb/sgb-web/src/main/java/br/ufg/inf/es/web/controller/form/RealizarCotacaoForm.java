package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.datamodel.CotacaoDataModel;
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

    private Collection<Cotacao> tabelaCotacoes;
    
    private Collection<Livro> livrosCotacao;
    
    private Collection<Livro>  livrosSelecionados;
    
    private CotacaoDataModel cotacaoDataModel;
    
    private Cotacao[] cotacoesSelecionadas;
    
    private Collection<Cotacao> cotacoesRealizadas;

    public CotacaoDataModel getCotacaoDataModel() {

        List<Cotacao> cotacoes = new ArrayList<Cotacao>(this.getTabelaCotacoes());
        
        cotacaoDataModel = new CotacaoDataModel(cotacoes);

        return cotacaoDataModel;
    }

    public void setCotacaoDataModel(CotacaoDataModel cotacaoDataModel) {
    
        this.cotacaoDataModel = cotacaoDataModel;
    }
    
    public Cotacao[] getCotacoesSelecionadas() {
        
        Cotacao[] retorno = null;
        
        if(this.cotacoesSelecionadas != null) {
            
             retorno = this.cotacoesSelecionadas.clone();
        }
        
        return retorno;
    }

    public void setCotacoesSelecionadas(Cotacao[] cotacoesSelecionadas) {
        
        if(cotacoesSelecionadas != null) {
        
            this.cotacoesSelecionadas = (Cotacao[]) cotacoesSelecionadas.clone();
        }
    }

    public Collection<Cotacao> getTabelaCotacoes() {
        return tabelaCotacoes;
    }

    public void setTabelaCotacoes(Collection<Cotacao> tabelaCotacoes) {
        this.tabelaCotacoes = tabelaCotacoes;
    }

    public Collection<Livro> getLivrosCotacao() {
        
        return livrosCotacao;
    }

    public void setLivrosCotacao(Collection<Livro> livrosCotacao) {
     
        this.livrosCotacao = livrosCotacao;
    }

    public Collection<Livro>  getLivrosSelecionados() {
        
        return livrosSelecionados;
    }

    public void setLivrosSelecionados(Collection<Livro> livrosSelecionados) {
        
        this.livrosSelecionados = livrosSelecionados;
    }

    public Collection<Cotacao> getCotacoesRealizadas() {
        
        return cotacoesRealizadas;
    }

    public void setCotacoesRealizadas(Collection<Cotacao> cotacoesRealizadas) {
        
        this.cotacoesRealizadas = cotacoesRealizadas;
    }
    
}
