/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.model.CotacoesLivro;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.persistencia.CotacaoDAO;
import br.ufg.inf.es.persistencia.CotacoesLivroDAO;
import br.ufg.inf.es.persistencia.LivroDAO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Classe servidora para entidade Cotacao
 * @author Marquete
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CotacaoService extends GenericService<CotacoesLivro> {

    /** Campo dao*/
    @Autowired
    private CotacoesLivroDAO dao;
    
    @Autowired
    private LivroDAO livroDao;
    
    /** 
     * {@inheritDoc} 
     */
    @Override
    public DAO<CotacoesLivro, Long> getDAO() {
        return dao;
    }

    /**
     * Método que define o DAO da Cotacao.
     *
     * @param dao
     */
    public void setDao(CotacoesLivroDAO dao) {
    	
        this.dao = dao;
    }

    public Collection<Livro> listarLivros() {
        
        return this.getLivroDao().list();
    }

    public LivroDAO getLivroDao() {
        return livroDao;
    }

    public void setLivroDao(LivroDAO livroDao) {
        this.livroDao = livroDao;
    }
    
    
}
