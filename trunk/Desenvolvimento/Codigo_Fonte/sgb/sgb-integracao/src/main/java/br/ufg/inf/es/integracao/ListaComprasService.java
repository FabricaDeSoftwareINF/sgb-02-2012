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
 *
 * @author Jackeline Neves
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ListaComprasService extends GenericService<ListaCompras> {

    @Autowired
    private ListaComprasDAO dao;
    
    @Autowired
    private LivroDAO livroDao;

    public LivroDAO getLivroDao() {
        return livroDao;
    }

    @Override
    public ListaComprasDAO getDAO() {
        return this.dao;
    }

    public void setDao(ListaComprasDAO dao) {
        this.dao = dao;
    }

    /**
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
     * 
     * @param filtroTitulo
     * @return 
     * @author Jackeline
     */
    public Collection<Livro> buscaTodosLivros(String filtroTitulo) {

        return livroDao.list();

    }
    
}