package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.model.ItemListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.persistencia.ItemListaCotacaoDAO;
import br.ufg.inf.es.persistencia.LivroDAO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Classe servidora para entidade Cotacao
 *
 * @author Marquete
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CotacaoService extends GenericService<ItemListaCotacao> {

    /**
     * Campo dao
     */
    @Autowired
    private ItemListaCotacaoDAO dao;
    @Autowired
    private LivroDAO livroDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public DAO<ItemListaCotacao, Long> getDAO() {
        return dao;
    }

    /**
     * Método que define o DAO da Cotacao.
     *
     * @param dao
     */
    public void setDao(ItemListaCotacaoDAO dao) {

        this.dao = dao;
    }

    /**
     * Lista os livros
     * @return  lista de livros
     */
    public Collection<Livro> listarLivros() {

        return this.getLivroDao().list();
    }

    /**
     * Obtem o dao da entidade Livro
     * @return 
     */
    public LivroDAO getLivroDao() {
        return livroDao;
    }

    /**
     * Define um dao para a entidade Livro
     * @param livroDao 
     */
    public void setLivroDao(LivroDAO livroDao) {
        this.livroDao = livroDao;
    }
}