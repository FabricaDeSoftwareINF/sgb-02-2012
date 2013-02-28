package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.optimizer.DataQuotation;
import br.ufg.inf.es.integracao.optimizer.OptimizerQuote;
import br.ufg.inf.es.integracao.optimizer.Quotation;
import br.ufg.inf.es.model.ItemListaCotacao;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.persistencia.ItemListaCotacaoDAO;
import br.ufg.inf.es.persistencia.LivroDAO;
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
 *
 * @author Bruno Marquete
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ListaCotacaoService extends GenericService<ListaCotacao> {

    /**
     * Campo dao
     */
    @Autowired
    private ListaCotacaoDAO dao;
    @Autowired
    private ItemListaCotacaoDAO cotacoesLivroDao;
    
    @Autowired
    private LivroDAO livroDao;

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

    public List<ItemListaCotacao> gerarListaOtimizada(Double orcamento, ListaCotacao entity, boolean tipoOtimizacao) {

        List<DataQuotation> dadosCotacao = this.adapterDadosCotacao(entity.getItensListaCotacao());

        List<Quotation> listaOtimizada;

        if (!tipoOtimizacao) {

            listaOtimizada = OptimizerQuote.optimizeCost(dadosCotacao, BigDecimal.valueOf(orcamento));

        } else {

            listaOtimizada = OptimizerQuote.optimizeQuantity(dadosCotacao, BigDecimal.valueOf(orcamento));
        }

        return this.getCotacoes(listaOtimizada);
    }

    private List<DataQuotation> adapterDadosCotacao(Collection<ItemListaCotacao> cotacoesLivro) {

        List<DataQuotation> dataQuotation = new ArrayList<DataQuotation>();

        for (ItemListaCotacao cotacao : cotacoesLivro) {

            dataQuotation.add(DataQuotation.create(cotacao.getId(), BigDecimal.valueOf(cotacao.getValorMedio()), cotacao.getQuantidadeAComprar()));
        }

        return dataQuotation;
    }

    private List<ItemListaCotacao> getCotacoes(List<Quotation> listaOtimizada) {

        List<ItemListaCotacao> cotacoes = new ArrayList<ItemListaCotacao>();

        for (Quotation cotacao : listaOtimizada) {

            ItemListaCotacao cl = this.cotacoesLivroDao.find((Long) cotacao.getProductId());
            
            Livro livro = cl.getLivro();
            
            livro.setAutores(this.livroDao.getAutores(livro.getId()));
            
            livro.setBibliografias(this.livroDao.getBibliografia(livro.getId()));
            
            cl.setQuantidadeAComprar(cotacao.getQuantity());

            cotacoes.add(cl);
        }

        return cotacoes;
    }

    public void editar(ListaCotacao listaCotacao) throws ValidationException {

        this.getDAO().update(listaCotacao);

    }

    public Collection<ListaCotacao> listByUser(Usuario user) {
        return this.getDAO().findListaByUser(user);
    }
}
