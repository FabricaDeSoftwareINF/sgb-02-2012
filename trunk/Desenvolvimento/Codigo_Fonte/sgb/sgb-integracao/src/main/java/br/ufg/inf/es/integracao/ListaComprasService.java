package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.persistencia.ListaComprasDAO;
import br.ufg.inf.es.persistencia.LivroDAO;
import br.ufg.inf.es.persistencia.UsuarioDAO;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

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
    private LivroDAO livroDao;
    @Autowired
    private UsuarioDAO usuarioDao;

    /**
     * Método que obtém o DAO do Livro.
     *
     * @return
     */
    public LivroDAO getLivroDao() {
        return livroDao;
    }

    /**
     * Método que define o LivroDAO
     *
     * @param livroDao
     */
    public void setLivroDao(LivroDAO livroDao) {
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

                Collection<Livro> livros = this.getDAO().getLivros(lc.getId());

                //for(Livro l : livros) {

                //  l.setAutores(this.getLivroDao().getAutores(l.getId()));

                //}

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
    public Collection<Livro> buscaTodosLivros(String filtroTitulo) {

        return livroDao.list();

    }

    public UsuarioDAO getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDAO usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public void criaListaCompras(Collection<Livro> livrosSelecionado) {

        ListaCompras listaCompras = new ListaCompras();

        listaCompras.setDataCriacao(new Date());

        listaCompras.setLivrosDaListaCompras(livrosSelecionado);

        listaCompras.setNome(listaCompras.getDataCriacao().toString());

        listaCompras.setUser(this.getUsuarioDao().find(1l));

        this.getDAO().insert(listaCompras);
    }
}