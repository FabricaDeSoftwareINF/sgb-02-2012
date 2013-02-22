package br.ufg.inf.es.integracao;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import br.ufg.inf.es.model.ItemListaCompras;
import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.persistencia.ItemListaComprasDAO;
import br.ufg.inf.es.persistencia.ListaComprasDAO;
import br.ufg.inf.es.persistencia.UsuarioDAO;

/**
 * Classe Service para a ListaCompras.
 *
 * @author Jackeline Neves
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ListaComprasService extends GenericService<ListaCompras> {

    /**
     * Campo dao
     */
    @Autowired
    private ListaComprasDAO dao;
    /**
     * Campo livroDao
     */
    @Autowired
    private ItemListaComprasDAO livroDao;
    
    @Autowired
    private UsuarioDAO usuarioDao;

    /**
     * Método que obtém o DAO do Livro.
     *
     * @return
     */
    public ItemListaComprasDAO getLivroDao() {
        return livroDao;
    }

    /**
     * Método que define o LivroDAO
     *
     * @param livroDao
     */
    public void setLivroDao(ItemListaComprasDAO livroDao) {
        this.livroDao = livroDao;
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
     * {@inheritDoc}
     */
    @Override
    public Collection<ListaCompras> list() {

        Collection<ListaCompras> listaCompras = super.list();

        this.carregarLivrosDaListaCompras(listaCompras);

        return listaCompras;
    }

    /**
     * Método que obtém os livros das listas de compras.
     *
     * @param listaCompras
     */
    public void carregarLivrosDaListaCompras(Collection<ListaCompras> listaCompras) {
        if (listaCompras != null) {
            for (ListaCompras lc : listaCompras) {
                Collection<ItemListaCompras> livros = this.getDAO().findLivrosListaCotacao(lc.getId());
                lc.setLivrosDaListaCompras(livros);

            }
        }
    }

    /**
     * Método que busca todos os livros.
     *
     * @param filtroTitulo
     * @return
     * @author Jackeline
     */
    public Collection<ItemListaCompras> buscaTodosLivros(String filtroTitulo) {

        return livroDao.list();

    }

    public UsuarioDAO getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDAO usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public void criaListaCompras(ListaCompras listaCompras) {
        this.getDAO().insert(listaCompras);
    }
    
    public void removerLivros(Collection<ItemListaCompras> livros) {
        this.getLivroDao().removeAll(livros);
    }
    
}