
package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.model.CotacoesLivro;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.datamodel.CotacaoDataModel;
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
public class CotacaoForm extends GenericForm<CotacoesLivro> {

    private Collection<CotacoesLivro> tabelaCotacoes;
    
    private Collection<Livro> livrosCotacao;
    
    private Livro[] livrosParaCotacao;
    
    private CotacaoDataModel cotacaoDataModel;
    
    private Cotacao[] cotacoesSelecionadas;

    public CotacaoDataModel getCotacaoDataModel() {

        List<CotacoesLivro> cotacoes = new ArrayList<CotacoesLivro>(this.getTabelaCotacoes());
        
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

    public Collection<CotacoesLivro> getTabelaCotacoes() {
        return tabelaCotacoes;
    }

    public void setTabelaCotacoes(Collection<CotacoesLivro> tabelaCotacoes) {
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
