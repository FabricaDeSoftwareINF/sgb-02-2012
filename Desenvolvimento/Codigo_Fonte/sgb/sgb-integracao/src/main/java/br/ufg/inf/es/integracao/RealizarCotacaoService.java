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
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import br.ufg.inf.es.integracao.cotacao.Cotador;
import br.ufg.inf.es.integracao.cotacao.CotadorBuscape;
import br.ufg.inf.es.integracao.cotacao.CotadorGoogleShop;
import br.ufg.inf.es.integracao.cotacao.ResultadoCotacao;
import br.ufg.inf.es.model.CotacoesLivro;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vinicius
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RealizarCotacaoService extends GenericService<ListaCotacao> {

    /**
     * Campo dao
     */
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


    public ListaCotacao realizarCotacao(Collection<Livro> livros) {
        Collection<CotacoesLivro> cotacoes = new ArrayList<CotacoesLivro>();
        Map<Livro, Collection<ResultadoCotacao>> resultados = buscarOfertas(livros);

        for (Livro livro : resultados.keySet()) {
            cotacoes.add(extraiCotacao(livro, resultados.get(livro)));
        }

        ListaCotacao listaCotacao = new ListaCotacao();
        listaCotacao.setCotacoesLivro(cotacoes);
        return listaCotacao;
    }

    private double calculaPrecoMedio(CotacoesLivro cotacoesLivro) {
        double somatorioPrecos = 0.0;
        double media = -1.0; //-1 indica que não foram encontradas cotações
        if (cotacoesLivro != null) {
            Collection<Cotacao> cotacoes = cotacoesLivro.getCotacaoes();
            for (Cotacao cotacao : cotacoes) {
                somatorioPrecos += cotacao.getValor();
            }
            media = somatorioPrecos / cotacoes.size();
        }
        return media;
    }

    private Map<Livro, Collection<ResultadoCotacao>> buscarOfertas(Collection<Livro> livros) {
        Map<Livro, Collection<ResultadoCotacao>> resultados = new HashMap();
        CotadorBuscape cotadorBuscape = new CotadorBuscape();
        CotadorGoogleShop cotadorGoogleShop = new CotadorGoogleShop();
        Cotador cotador;

        for (Livro livro : livros) {
            if (livro.isEstrangeiro()) {
                cotador = cotadorGoogleShop;
            } else {
                cotador = cotadorBuscape;
            }
            resultados.put(livro, cotador.buscarOfertas(livro));
        }
        return resultados;
    }


    public LivrosBibliotecaDAO getBibliotecaDao() {
        
        return this.bibliotecaDao;
    }

    public ParametrosDAO getParametrosDao() {
    
        return this.parametrosDao;
    }
        

    private CotacoesLivro extraiCotacao(Livro livro, Collection<ResultadoCotacao> resultadosCotacao) {
        Collection<Cotacao> cotacoes = new ArrayList<Cotacao>();
        for (ResultadoCotacao resultadoCotacao : resultadosCotacao) {
            Cotacao cotacao = new Cotacao();
            cotacao.setLivraria(resultadoCotacao.getLivraria());
            double preco = -1.0;
            try {
                preco = Double.parseDouble(resultadoCotacao.getOfertaLivro().getPrecoLivro());
            } catch (NumberFormatException nfe) {
                System.err.println(nfe.getMessage());
            }
            cotacao.setValor(preco);
            cotacoes.add(cotacao);
        }
        CotacoesLivro cotacoesLivro = new CotacoesLivro();
        cotacoesLivro.setLivro(livro);
        cotacoesLivro.setCotacoes(cotacoes);
        cotacoesLivro.setValorMedio(calculaPrecoMedio(cotacoesLivro));
        return cotacoesLivro;
    }

    
    private Collection<Cotacao> obtemQuantidadeNecessariaLivros(Collection<CotacoesLivro> cotacoes){
        
        try {
            
            List<Parametros> param = new ArrayList<Parametros>(this.getParametrosDao().list());
            
            int quantidadeLivrosPorAlunos = 0;
            
            if(UtilObjeto.isReferencia(param) && param.size() > 0){
                
                quantidadeLivrosPorAlunos = param.get(0).getParametroMEC();
            }
            
            for(CotacoesLivro cotacao: cotacoes) {
            
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

    public void salvarListaCotacao(Collection<CotacoesLivro> cotacoesSelecionadas) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}