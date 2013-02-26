package br.ufg.inf.es.integracao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.base.util.UtilObjeto;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.ItemListaCompras;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.Parametros;
import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;
import br.ufg.inf.es.persistencia.LivroDAO;
import br.ufg.inf.es.persistencia.ParametrosDAO;
import br.ufg.inf.es.persistencia.biblioteca.LivrosBibliotecaDAO;

/**
 *
 * @author vinicius
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ItemListaCompraService extends GenericService<ItemListaCompras> {

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
     * Campo livroService
     */
    @Autowired
    private LivroDAO livroDao;
    /**
     * Campo bibliografiaService
     */
    @Autowired
    private BibliografiaService bibliografiaService;
    /**
     * Campo livroCursos
     */
    private Map<Livro, List<Curso>> livroCursos = new HashMap<Livro, List<Curso>>();

    /**
     * Método que realiza a divisão com arrendondamento para cima.
     *
     * @param valor
     * @param divisor
     * @return
     */
    private Integer dividaComRoundUp(Integer valor, Integer divisor) {
        BigDecimal valorBigDecimal = new BigDecimal(valor);
        BigDecimal divisorBigDecimal = new BigDecimal(divisor);
        return valorBigDecimal.divide(divisorBigDecimal, RoundingMode.UP).intValue();
    }

    /**
     * obtem o dao do livroService
     *
     * @return dao do livroService
     */
    @Override
    public DAO getDAO() {
        return livroDao;
    }

    public BibliografiaService getBibliografiaService() {
        return bibliografiaService;
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

    public LivroDAO getLivroDao() {
        return livroDao;
    }

    public void setLivroDao(LivroDAO livroDao) {
        this.livroDao = livroDao;
    }

    /**
     * Define o service da bibliografia
     *
     * @param bibliografiaService
     */
    public void setBibliografiaService(BibliografiaService bibliografiaService) {
        this.bibliografiaService = bibliografiaService;
    }

    /**
     * Obtem o mapa de cursos por livro
     *
     * @return mapa de cursos por livro
     */
    public Map<Livro, List<Curso>> getLivroCursos() {
        return livroCursos;
    }

    /**
     * Define o mapa de cursos por livro
     *
     * @param livroCursos
     */
    public void setLivroCursos(Map<Livro, List<Curso>> livroCursos) {
        this.livroCursos = livroCursos;
    }

    /**
     * obtem o service dos parametros
     *
     * @return
     */
    public ParametrosDAO getParametrosDAO() {
        return parametrosDao;
    }

    /**
     * Define um novo service dos parametros
     *
     * @param parametrosDAO
     */
    public void setParametrosDAO(ParametrosDAO parametrosDAO) {
        this.parametrosDao = parametrosDAO;
    }

    public Collection<ItemListaCompras> obtemLivrosParaCotacao() throws ValidationException {
        Collection<ItemListaCompras> livrosParaCotacao = new ArrayList<ItemListaCompras>();

        List<Livro> livros = new ArrayList<Livro>(this.getLivroDao().list());
        List<Parametros> param = new ArrayList<Parametros>(this.getParametrosDao().list());

        int quantidadeLivrosPorAlunos = 0;

        if (UtilObjeto.isReferencia(param) && param.size() > 0) {

            quantidadeLivrosPorAlunos = param.get(0).getParametroMEC();
        }

        for (Livro livro : livros) {

            Integer quantidadeBiblioteca = 0;

            try {
                
                Collection<LivroBiblioteca> livrosBiblioteca = new ArrayList<LivroBiblioteca>();
                
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

            Integer quantidadeAlunos = this.getLivroDao().obterQuantidadeDeAlunosPorLivro(livro.getId());

            ItemListaCompras livroParaCotacao = new ItemListaCompras();
            livroParaCotacao.setLivro(livro);
            int quantidadeExigida = 0;
            if (quantidadeLivrosPorAlunos > 0) {
                quantidadeExigida = (int) Math.ceil(((double) quantidadeAlunos) / quantidadeLivrosPorAlunos);
            }
            livroParaCotacao.setQuantidadeExigida(quantidadeExigida);
            livroParaCotacao.setQuantidadeLivrosDisponiveis(quantidadeBiblioteca);
            
            int quantidadeAComprar = quantidadeExigida - quantidadeBiblioteca;
            
            livroParaCotacao.setQuantidadeAComprar(Math.max(0, quantidadeAComprar));
            livrosParaCotacao.add(livroParaCotacao);
        }
        return livrosParaCotacao;
    }
}
