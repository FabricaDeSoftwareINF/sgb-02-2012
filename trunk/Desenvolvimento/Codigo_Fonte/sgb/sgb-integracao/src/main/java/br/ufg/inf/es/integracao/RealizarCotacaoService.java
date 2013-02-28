package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.util.UtilObjeto;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Cotacao;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.Parametros;
import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;
import br.ufg.inf.es.persistencia.CotacaoDAO;
import br.ufg.inf.es.persistencia.ListaCotacaoDAO;
import br.ufg.inf.es.persistencia.LivroDAO;
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
import br.ufg.inf.es.model.*;
import java.math.BigDecimal;
import java.util.Date;
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
    private ParametrosService parametrosService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ListaCotacaoDAO getDAO() {
        return this.dao;
    }

    public void setBibliotecaDao(LivrosBibliotecaDAO bibliotecaDao) {
        this.bibliotecaDao = bibliotecaDao;
    }

    public void setCotacaoDao(CotacaoDAO cotacaoDao) {
        this.cotacaoDao = cotacaoDao;
    }

    public void setLivroDao(LivroDAO livroDao) {
        this.livroDao = livroDao;
    }

    public void setParametrosService(ParametrosService parametrosDao) {
        this.parametrosService = parametrosDao;
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

    public ListaCotacao realizarCotacao(Collection<ItemListaCompras> livros) {
        Collection<ItemListaCotacao> cotacoes = new ArrayList<ItemListaCotacao>();
        Map<Livro, Collection<ResultadoCotacao>> resultados = buscarOfertas(livros);
        BigDecimal valorFrete = new BigDecimal(0.0);
        try {
            Parametros parametros = this.getParametrosService().find();
            valorFrete = parametros.getValorFrete();
        } catch (ValidationException ex) {
            Logger.getLogger(RealizarCotacaoService.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Livro livro : resultados.keySet()) {
            cotacoes.add(extraiCotacao(livro, resultados.get(livro), valorFrete));
        }

        this.obtemQuantidadeNecessariaLivros(cotacoes);

        ListaCotacao listaCotacao = new ListaCotacao();
        listaCotacao.setItensListaCotacao(cotacoes);
        return listaCotacao;
    }

    private Map<Livro, Collection<ResultadoCotacao>> buscarOfertas(Collection<ItemListaCompras> livros) {
        Map<Livro, Collection<ResultadoCotacao>> resultados = new HashMap();
        CotadorBuscape cotadorBuscape = new CotadorBuscape();
        CotadorGoogleShop cotadorGoogleShop = new CotadorGoogleShop();
        Cotador cotador;

        for (ItemListaCompras livroListaCotacao : livros) {
            if (livroListaCotacao.getLivro().isEstrangeiro()) {
                cotador = cotadorGoogleShop;
            } else {
                cotador = cotadorBuscape;
            }
            resultados.put(livroListaCotacao.getLivro(), cotador.buscarOfertas(livroListaCotacao.getLivro()));
        }
        return resultados;
    }

    public LivrosBibliotecaDAO getBibliotecaDao() {

        return this.bibliotecaDao;
    }

    public ParametrosService getParametrosService() {
        return this.parametrosService;
    }

    private ItemListaCotacao extraiCotacao(Livro livro, Collection<ResultadoCotacao> resultadosCotacao, BigDecimal valorFrete) {
        Collection<Cotacao> cotacoes = new ArrayList<Cotacao>();
        ItemListaCotacao cotacoesLivro = new ItemListaCotacao();
        for (ResultadoCotacao resultadoCotacao : resultadosCotacao) {
            Cotacao cotacao = new Cotacao();
            cotacao.setLivraria(resultadoCotacao.getLivraria());
            double preco = 0.0;
            try {
                preco = Double.parseDouble(resultadoCotacao.getOfertaLivro().getPrecoLivro());
            } catch (NumberFormatException nfe) {
                Logger.getAnonymousLogger().log(Level.SEVERE, nfe.getMessage(), nfe);
            }
            cotacao.setValor(preco);
            cotacoes.add(cotacao);
            if (cotacoesLivro.getUrlImagem() == null) {
                String urlImagem = resultadoCotacao.getOfertaLivro().getLinkImagemLivro();
                cotacoesLivro.setUrlImagem(urlImagem);
            }
        }
        cotacoesLivro.setLivro(livro);
        cotacoesLivro.setCotacoes(cotacoes);
        if (livro.isEstrangeiro()) {
            cotacoesLivro.setValorMedio(calculaPrecoMedio(cotacoesLivro) + valorFrete.doubleValue());
        } else {
            cotacoesLivro.setValorMedio(calculaPrecoMedio(cotacoesLivro));
        }
        return cotacoesLivro;
    }
    
    private double calculaPrecoMedio(ItemListaCotacao cotacoesLivro) {
        double somatorioPrecos = 0.0;
        double media = 0.0;
        if (cotacoesLivro != null) {
            Collection<Cotacao> cotacoes = cotacoesLivro.getCotacoes();
            for (Cotacao cotacao : cotacoes) {
                somatorioPrecos += cotacao.getValor();
            }
            media = somatorioPrecos / cotacoes.size();
        }
        if (Double.isNaN(media)) {
            media = 0.0;
        }
        return media;
    }

    private Collection<Cotacao> obtemQuantidadeNecessariaLivros(Collection<ItemListaCotacao> cotacoes) {

        try {

            List<Parametros> param = new ArrayList<Parametros>(this.getParametrosService().list());

            int quantidadeLivrosPorAlunos = 0;

            if (UtilObjeto.isReferencia(param) && param.size() > 0) {

                quantidadeLivrosPorAlunos = param.get(0).getParametroMEC();
            }

            for (ItemListaCotacao cotacao : cotacoes) {

                Integer quantidadeBiblioteca = 0;

                Collection<LivroBiblioteca> livrosBiblioteca = new ArrayList<LivroBiblioteca>();

                Livro livro = cotacao.getLivro();
                
                Collection<Long> idLivrosBibliotecaRelacionados = this.livroDao.findLivrosBiblioteca(livro.getId());

                for (Long idLivroBiblioteca : idLivrosBibliotecaRelacionados) {
                    LivroBiblioteca livroBiblioteca = this.getBibliotecaDao().getLivroBibliotecaCodigo(idLivroBiblioteca);
                    if (UtilObjeto.isReferencia(livroBiblioteca)) {
                        livrosBiblioteca.add(livroBiblioteca);
                    }
                }

                if (UtilObjeto.isReferencia(livrosBiblioteca) && livrosBiblioteca.size() > 0) {

                    for (LivroBiblioteca livroBiblioteca : livrosBiblioteca) {

                        quantidadeBiblioteca += livroBiblioteca.getQuantidade();
                    }
                }

                Integer quantidadeAlunos = this.getLivroDao().obterQuantidadeDeAlunosPorLivro(cotacao.getLivro().getId());

                int quantidadeExigida = 0;
                if (quantidadeLivrosPorAlunos > 0) {
                    quantidadeExigida = (int) Math.ceil(((double) quantidadeAlunos) / quantidadeLivrosPorAlunos);
                }

                cotacao.setQuantidadeAComprar(Math.max(0, quantidadeExigida - quantidadeBiblioteca));
                cotacao.setQuantidadeExigida(Math.max(0, quantidadeExigida));
                cotacao.setQuantidadeLivrosDisponiveis(quantidadeBiblioteca);
            }


        } catch (NotFoundException nfe) {

            Logger.getAnonymousLogger().log(Level.SEVERE, "Livro não encontrado");

        } catch (SQLException sqle) {

            Logger.getAnonymousLogger().log(Level.SEVERE, sqle.getMessage(), sqle);
        }

        return null;
    }

    public void salvarListaCotacao(Collection<ItemListaCotacao> cotacoesSelecionadas, String nome, Usuario autor) {
        ListaCotacao listaCotacao = new ListaCotacao();
        listaCotacao.setItensListaCotacao(cotacoesSelecionadas);
        listaCotacao.setDataRealizada(new Date());
        listaCotacao.setNome(nome);
        listaCotacao.setUser(autor);
        this.getDAO().insert(listaCotacao);
    }
}