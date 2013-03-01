package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.util.UtilObjeto;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import br.ufg.inf.es.model.ItemListaCompras;
import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.Parametros;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;
import br.ufg.inf.es.persistencia.ItemListaComprasDAO;
import br.ufg.inf.es.persistencia.ListaComprasDAO;
import br.ufg.inf.es.persistencia.LivroDAO;
import br.ufg.inf.es.persistencia.ParametrosDAO;
import br.ufg.inf.es.persistencia.UsuarioDAO;
import br.ufg.inf.es.persistencia.biblioteca.LivrosBibliotecaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.NotFoundException;

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
    private ItemListaComprasDAO itemListaComprasDao;
    @Autowired
    private UsuarioDAO usuarioDao;
    
    /**
     * Campo parametrosDao
     */
    @Autowired
    private ParametrosDAO parametrosDao;
    
    /**
     * Campo bibliotecaDao
     */
    @Autowired
    private LivrosBibliotecaDAO bibliotecaDao;
    
    /**
     * Campo livroDao
     */
    @Autowired
    private LivroDAO livroDao;

    /**
     * Método que obtém o DAO do ItemListaCompras.
     *
     * @return itemListaComprasDao
     */
    public ItemListaComprasDAO getItemListaComprasDao() {
        return itemListaComprasDao;
    }

    /**
     * Método que define o ItemListaComprasDAO
     *
     * @param itemListaComprasDao
     */
    public void getItemListaComprasDao(ItemListaComprasDAO livroDao) {
        this.itemListaComprasDao = livroDao;
    }
    
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
                Collection<ItemListaCompras> livros = this.getDAO().findLivrosListaCompras(lc.getId());
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

        return itemListaComprasDao.list();

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
        this.getItemListaComprasDao().removeAll(livros);
    }

    public ParametrosDAO getParametrosDao() {
        return parametrosDao;
    }

    public void setParametrosDao(ParametrosDAO parametrosDao) {
        this.parametrosDao = parametrosDao;
    }

    public LivrosBibliotecaDAO getBibliotecaDao() {
        return bibliotecaDao;
    }

    public void setBibliotecaDao(LivrosBibliotecaDAO bibliotecaDao) {
        this.bibliotecaDao = bibliotecaDao;
    }
    
    public void atualizarItens(Collection<ItemListaCompras> itens) {
        List<Parametros> param = new ArrayList<Parametros>(this.getParametrosDao().list());
        int quantidadeLivrosPorAlunos = 0;
        if (UtilObjeto.isReferencia(param) && param.size() > 0) {
            quantidadeLivrosPorAlunos = param.get(0).getParametroMEC();
        }
        for (ItemListaCompras item : itens) {

            Integer quantidadeBiblioteca = 0;

            try {
                Collection<LivroBiblioteca> livrosBiblioteca = new ArrayList<LivroBiblioteca>();
                Livro livro = item.getLivro();
                Collection<Long> idLivrosBibliotecaRelacionados = livro.getCodigosLivrosBiblioteca();
                
                for (Long idLivroBiblioteca : idLivrosBibliotecaRelacionados){
                    LivroBiblioteca livroBiblioteca = this.getBibliotecaDao().getLivroBibliotecaCodigo(idLivroBiblioteca);
                    if (UtilObjeto.isReferencia(livroBiblioteca) ){
                        livrosBiblioteca.add(livroBiblioteca);
                    }
                }

                if (UtilObjeto.isReferencia(livrosBiblioteca) && livrosBiblioteca.size() > 0) {

                    for (LivroBiblioteca livroBiblioteca : livrosBiblioteca) {

                        quantidadeBiblioteca += livroBiblioteca.getQuantidade();
                    }
                }

            } catch (NotFoundException ex) {
                Logger.getLogger(ItemListaCompraService.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            } catch (SQLException ex) {
                Logger.getLogger(ItemListaCompraService.class.getName()).log(Level.SEVERE, null, ex.getMessage());
            }

            Integer quantidadeAlunos = this.getLivroDao().obterQuantidadeDeAlunosPorLivro(item.getLivro().getId());

            int quantidadeExigida = 0;
            if (quantidadeLivrosPorAlunos > 0) {
                quantidadeExigida = (int) Math.ceil(((double) quantidadeAlunos) / quantidadeLivrosPorAlunos);
            }
            item.setQuantidadeExigida(quantidadeExigida);
            item.setQuantidadeLivrosDisponiveis(quantidadeBiblioteca);
        }
    }
    
    public Collection<ListaCompras> listByUser(Usuario user) {
        return this.getDAO().findListaByUser(user);
    }
    
}
