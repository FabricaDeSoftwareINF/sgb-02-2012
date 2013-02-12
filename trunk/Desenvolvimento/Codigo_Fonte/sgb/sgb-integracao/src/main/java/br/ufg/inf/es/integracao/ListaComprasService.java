package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.persistencia.ListaComprasDAO;
import br.ufg.inf.es.persistencia.LivroDAO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Classe Service para a ListaCompras.
 * @author Jackeline Neves
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ListaComprasService extends GenericService<ListaCompras> {

    /** Campo dao*/
    @Autowired
    private ListaComprasDAO dao;
    
    /** Campo livroDao*/
    @Autowired
    private LivroDAO livroDao;

    /**
     * Método que obtém o DAO do Livro.
     *
     * @return
     */
    public LivroDAO getLivroDao() {
        return livroDao;
    }

    /** 
     * {@inheritDoc} 
     */
    @Override
    public ListaComprasDAO getDAO() {
        return this.dao;
    }

    /**
     * Método que define o DAO da Lista de Compras
     *
     * @param dao
     */
    public void setDao(ListaComprasDAO dao) {
        this.dao = dao;
    }

    /**
     * Método que obtém os livros das listas de compras.
     * 
     * @param listaCompras 
     */
    public void carregarLivrosDaListaCompras(Collection<ListaCompras> listaCompras) {

        for (ListaCompras lc : listaCompras) {
            
            Collection<Livro> livros = this.getDAO().getLivros(lc.getId());
            
            //for(Livro l : livros) {
                
              //  l.setAutores(this.getLivroDao().getAutores(l.getId()));
                
            //}
            
            lc.setLivrosDaListaCompras(livros);
        
        }
    }
    
    /**
     * Método que busca todos os livros.
     * 
     * @param filtroTitulo
     * @return 
     * @author Jackeline
     */
    public Collection<Livro> buscaTodosLivros(String filtroTitulo) {

        return livroDao.list();

    }
    
}