package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.util.UtilObjeto;
import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.Parametros;
import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;
import br.ufg.inf.es.persistencia.CotacaoDAO;
import br.ufg.inf.es.persistencia.ListaCotacaoDAO;
import br.ufg.inf.es.persistencia.LivroDAO;
import br.ufg.inf.es.persistencia.ParametrosDAO;
import br.ufg.inf.es.persistencia.biblioteca.LivrosBibliotecaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author vinicius
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RealizarCotacaoService extends GenericService<ListaCotacao> {

    /** Campo dao*/
    @Autowired
    private ListaCotacaoDAO dao;
    
    @Autowired
    private LivroDAO livroDao;
    
    @Autowired
    private CotacaoDAO cotacaoDao;
    
    @Autowired
    private LivrosBibliotecaDAO bibliotecaDao;
    
    @Autowired
    private ParametrosDAO parametrosDao; 
    
    /** 
     * {@inheritDoc} 
     */
    @Override
    public ListaCotacaoDAO getDAO() {
        return this.dao;
    }

    /**
     * Método que define o DAO do Livro.
     *
     * @param dao
     */
    public void setDao(ListaCotacaoDAO dao) {

        this.dao = dao;
    }

    public ListaCotacaoDAO getDao() {
        
        return this.dao;
    }

    public LivroDAO getLivroDao() {
        
        return this.livroDao;
    }

    public CotacaoDAO getCotacaoDao() {
        
        return this.cotacaoDao;
    }

    public LivrosBibliotecaDAO getBibliotecaDao() {
        
        return this.bibliotecaDao;
    }

    public ParametrosDAO getParametrosDao() {
    
        return this.parametrosDao;
    }
        
    public Collection<Cotacao> realizarCotacao(Collection<Livro> livros) {
        Collection<Cotacao> cotacoes = new ArrayList<Cotacao>();
        for (Livro livro : livros) {
            Cotacao cotacao = new Cotacao();
            cotacao.setLivro(livro);
            cotacoes.add(cotacao);
        }
        
        this.obtemQuantidadeNecessariaLivros(cotacoes);
        
        return cotacoes;
    }
    
    private Collection<Cotacao> obtemQuantidadeNecessariaLivros(Collection<Cotacao> cotacoes){
        
        try {
            
            List<Parametros> param = new ArrayList<Parametros>(this.getParametrosDao().list());
            
            int quantidadeLivrosPorAlunos = 0;
            
            if(UtilObjeto.isReferencia(param) && param.size() > 0){
                
                quantidadeLivrosPorAlunos = param.get(0).getParametroMEC();
            }
            
            for(Cotacao cotacao: cotacoes) {
            
                Integer quantidadeBiblioteca = 0;
                
                Collection<LivroBiblioteca> livros = this.getBibliotecaDao().getLivrosBibliotecaTitulo(cotacao.getLivro().getTitulo());
                
                if(UtilObjeto.isReferencia(livros) && livros.size() > 0){
                                      
                    for(LivroBiblioteca livro : livros){
                        
                        quantidadeBiblioteca += livro.getQuantidade();
                    }                    
                }
                
                Integer quantidadeAlunos = this.getLivroDao().obterQuantidadeDeAlunosPorLivro(cotacao.getLivro().getId());
                
                cotacao.setQuantidade((quantidadeAlunos / quantidadeLivrosPorAlunos) - quantidadeBiblioteca);
            }
            
        
        } catch (NotFoundException nfe) {
            
            Logger.getAnonymousLogger().log(Level.SEVERE, "Livro não encontrado");
        
        } catch (SQLException sqle) {
            
            Logger.getAnonymousLogger().log(Level.SEVERE, sqle.getMessage(), sqle);
        }
        
        return null;
    }

    public void salvarListaCotacao(Cotacao[] cotacoesSelecionadas) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}