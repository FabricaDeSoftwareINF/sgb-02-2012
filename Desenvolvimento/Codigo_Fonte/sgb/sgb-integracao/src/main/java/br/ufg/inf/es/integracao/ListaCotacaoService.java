
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.optimizer.DataQuotation;
import br.ufg.inf.es.integracao.optimizer.OptimizerQuote;
import br.ufg.inf.es.integracao.optimizer.Quotation;
import br.ufg.inf.es.model.CotacoesLivro;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.persistencia.CotacoesLivroDAO;
import br.ufg.inf.es.persistencia.ListaCotacaoDAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Classe Service para a entidade ListaCotacao
 * @author Bruno Marquete
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ListaCotacaoService extends GenericService<ListaCotacao> {

    /** Campo dao*/
    @Autowired
    private ListaCotacaoDAO dao;
    
    @Autowired
    private CotacoesLivroDAO cotacoesLivroDao;
    
    /** 
     * {@inheritDoc} 
     */
    @Override
    public ListaCotacaoDAO getDAO() {
        return dao;
    }

    /**
     * Método que define o DAO da ListaCotacao
     *
     * @author User
     *
     * @param dao
     */
    public void setDao(ListaCotacaoDAO dao) {
    	
        this.dao = dao;
    }

    public List<CotacoesLivro> gerarListaOtimizada(Double orcamento, ListaCotacao entity, boolean tipoOtimizacao) {
        
        List<DataQuotation> dadosCotacao = this.adapterDadosCotacao(entity.getCotacoesLivro());
        
        List<Quotation> listaOtimizada;
               
        if (!tipoOtimizacao) {
        
            listaOtimizada = OptimizerQuote.optimizeCost(dadosCotacao, BigDecimal.valueOf(orcamento));
            
        } else {
            
            listaOtimizada = OptimizerQuote.optimizeQuantity(dadosCotacao, BigDecimal.valueOf(orcamento));
        }
        
        return this.getCotacoes(listaOtimizada);
    }

    private List<DataQuotation> adapterDadosCotacao(Collection<CotacoesLivro> cotacoesLivro) {
        
        List<DataQuotation> dataQuotation = new ArrayList<DataQuotation>();
        
        for (CotacoesLivro cotacao : cotacoesLivro) {
            
            dataQuotation.add(DataQuotation.create(cotacao.getId(), BigDecimal.valueOf(cotacao.getValorMedio()), cotacao.getQuantidade()));
        }
        
        return dataQuotation;
    }

    private List<CotacoesLivro> getCotacoes(List<Quotation> listaOtimizada) {
        
        List<CotacoesLivro> cotacoes = new ArrayList<CotacoesLivro>();
        
        for (Quotation cotacao : listaOtimizada) {
            
            CotacoesLivro cl = this.cotacoesLivroDao.find((Long)cotacao.getProductId());
            
            cl.setQuantidade(cotacao.getQuantity());
            
            cotacoes.add(cl);
        }
        
        return cotacoes;
    }   
    public void editar(ListaCotacao listaCotacao) throws ValidationException {

        this.getDAO().update(listaCotacao);

    }
    
     public Collection<ListaCotacao> listByUser(Usuario user) {

         Collection<ListaCotacao> listasCotacaoUserLogado =
                 new ArrayList<ListaCotacao>();
         ArrayList<ListaCotacao> todasListasCotacao =
                 new ArrayList<ListaCotacao>(this.getDAO().list());
         
         for(ListaCotacao listaCotacao : todasListasCotacao) {
             
             if (listaCotacao.getUser().getEmail().equals(user.getEmail())) {
                 listasCotacaoUserLogado.add(listaCotacao);
             }
             
         }
         
        return (Collection) listasCotacaoUserLogado;
    }
    
}
