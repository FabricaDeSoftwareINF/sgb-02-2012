package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.exceptions.LivroParaCotacaoException;
import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.persistencia.*;
import br.ufg.inf.es.base.util.UtilObjeto;
import br.ufg.inf.es.model.dtos.LivroParaCotacao;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.biblioteca.BibliotecaServiceMock;
import br.ufg.inf.es.model.*;
import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;
import br.ufg.inf.es.persistencia.biblioteca.LivrosBibliotecaDAO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Service responsavel por buscar os livros que deverão ser cotados, de acordo
 * com a quantidade de vagas ofertadas e com a quantidade de exemplares
 * disponiveis na biblioteca
 *
 * @author Victor Carvalho
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LivroParaCotacaoService extends GenericService<LivroParaCotacao> {

    /**
     * TODO Remover o mock pelo serviço real da biblioteca Campo
     * bibliotecaService
     */
    @Autowired
    private BibliotecaServiceMock bibliotecaServiceMock;
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
     * obtem o service da biblioteca
     *
     * @return service da biblioteca
     */
    public BibliotecaServiceMock getBibliotecaService() {
        return bibliotecaServiceMock;
    }

    /**
     * define o service da biblioteca
     *
     * @param bibliotecaService
     */
    public void setBibliotecaService(BibliotecaServiceMock bibliotecaService) {
        this.bibliotecaServiceMock = bibliotecaService;
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
     * @return 
     */
    public ParametrosDAO getParametrosDAO() {
        return parametrosDao;
    }

    /**
     * Define um novo service dos parametros
     * @param parametrosDAO 
     */
    public void setParametrosDAO(ParametrosDAO parametrosDAO) {
        this.parametrosDao = parametrosDAO;
    }
    
    public Collection<LivroParaCotacao> obtemLivrosParaCotacao() throws ValidationException {
        Collection<LivroParaCotacao> livrosParaCotacao = new ArrayList<LivroParaCotacao>();
        try {
            
            List<Livro> livros = new ArrayList<Livro>(this.getLivroDao().list());
            List<Parametros> param = new ArrayList<Parametros>(this.getParametrosDao().list());
            
            int quantidadeLivrosPorAlunos = 0;
            
            if(UtilObjeto.isReferencia(param) && param.size() > 0){
                
                quantidadeLivrosPorAlunos = param.get(0).getParametroMEC();
            }
            
            for(Livro livro: livros) {
            
                Integer quantidadeBiblioteca = 0;
                
                Collection<LivroBiblioteca> livrosBiblioteca = this.getBibliotecaDao().getLivrosBibliotecaTitulo(livro.getTitulo());
                
                if(UtilObjeto.isReferencia(livrosBiblioteca) && livrosBiblioteca.size() > 0){
                                      
                    for(LivroBiblioteca livroBiblioteca : livrosBiblioteca){
                        
                        quantidadeBiblioteca += livroBiblioteca.getQuantidade();
                    }                    
                }
                
                Integer quantidadeAlunos = this.getLivroDao().obterQuantidadeDeAlunosPorLivro(livro.getId());
                
                LivroParaCotacao livroParaCotacao = new LivroParaCotacao();
                livroParaCotacao.setLivro(livro);
                livroParaCotacao.setParametroMec(quantidadeLivrosPorAlunos);
                livroParaCotacao.setQuantidadeExigida(quantidadeAlunos / quantidadeLivrosPorAlunos);
                livroParaCotacao.setQuantidadeLivrosDisponiveis(quantidadeBiblioteca);
                livrosParaCotacao.add(livroParaCotacao);
            }
            
        
        } catch (NotFoundException nfe) {
            throw new ValidationException("A configuração com a visão do banco de dados da Biblioteca não foi encontrada.");
        } catch (SQLException sqle) {
            Logger.getAnonymousLogger().log(Level.SEVERE, sqle.getMessage(), sqle);
        }
        
        return livrosParaCotacao;
    }
}