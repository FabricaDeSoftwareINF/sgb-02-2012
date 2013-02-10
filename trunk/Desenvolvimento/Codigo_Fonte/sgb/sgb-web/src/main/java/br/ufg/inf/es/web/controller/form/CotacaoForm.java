
package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Cotacao;
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
public class CotacaoForm extends GenericForm<Cotacao> {

    private Collection<Cotacao> tabelaCotacoes;
    
    private CotacaoDataModel cotacaoDataModel;
    
    private Cotacao[] cotacoesSelecionadas;

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

}
