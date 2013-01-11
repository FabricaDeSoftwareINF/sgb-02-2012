package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.excptions.LivroParaCotacaoException;
import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.biblioteca.BibliotecaServiceMock;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.LivroParaCotacao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Service responsavel por buscar os livros que deverão ser cotados, de acordo
 * com a quantidade de vagas ofertadas e com a quantidade de exemplares disponiveis
 * na biblioteca
 * 
 * @author Victor Carvalho
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LivroParaCotacaoService extends GenericService<LivroParaCotacao> {

    // TODO Remover o mock pelo serviço real da biblioteca
    @Autowired
    private BibliotecaServiceMock bibliotecaService; 
    
    @Autowired
    private ParametrosService parametrosService;
    
    @Autowired
    private LivroService livroService;

    /**
     * Obtem uma listagem dos livros necessários para cotação
     *
     * @return lista de livros para cotacao
     */
    public Collection<LivroParaCotacao> obtenhaLivrosParaCotacao() {
        Collection<Livro> livros = livroService.list();
        Collection<LivroParaCotacao> dtos = new ArrayList<LivroParaCotacao>();

        for (Livro l : livros) {
            Integer quantidadeVagas = obtenhaQuantidadeDeVagas(l);

            if (quantidadeVagas > 0) {
                try {
                    dtos.add(obtenhaLivroParaCotacao(l, quantidadeVagas));
                } catch (LivroParaCotacaoException lpce) {
                    Logger.getLogger(LivroParaCotacaoService.class.getName()).log(Level.WARNING, null, lpce);
                } catch (ValidationException ve) {
                    Logger.getLogger(LivroParaCotacaoService.class.getName()).log(Level.WARNING, null, ve);
                }
            }
        }
         
        return dtos;
    }

    /**
     * Gera a partir de um livro um dto com as informações para cotacao do
     * mesmo. Estas informacoes consistem na quantidade necessaria para compra,
     * a quantidade existente hoje na bibliote, a quantidade de vagas que
     * precisam do livro, o parametro do mec, entre outras informações úteis (
     *
     * @see LivrosParaCotacaoDTO).
     *
     * @param livro livro a partir do qual será feita a busca
     * @param quantidadeVagas quantidade de vagas que utilizam o livro.
     *
     * @return DTO com as informacoes para cotacao
     *
     * @throws ValidationException
     * @throws LivroParaCotacaoException Caso a quantidade existente do livro
     * seja suficiente para as vagas existentes.
     */
    private LivroParaCotacao obtenhaLivroParaCotacao(Livro livro,
            Integer quantidadeVagas) throws ValidationException, LivroParaCotacaoException {
        Integer parametroMec = parametrosService.obtenhaParametroMEC();

        Integer qtdeLivrosNecessarios = quantidadeVagas / parametroMec;
        Integer qtdeLivrosDisponiveis = bibliotecaService.obtenhaQuantidadeExistente(livro.getIsbn11());

        if (qtdeLivrosDisponiveis < qtdeLivrosNecessarios) {
            Integer qtdeParaCotacao = qtdeLivrosDisponiveis - qtdeLivrosNecessarios;

            return new LivroParaCotacao(quantidadeVagas, parametroMec,
                    qtdeLivrosDisponiveis, qtdeParaCotacao, livro.getTitulo(), livro.getIsbn11());
        } else {
            throw new LivroParaCotacaoException("Existem livros em quantidade "
                    + "suficiente para atender à todas as vagas");
        }
    }

    private Integer obtenhaQuantidadeDeVagas(Livro livro) {
        List<Disciplina> disciplinas = obtenhaDisciplinasVinculadas(livro);
        Integer quantidadeVagas = 0;

        for (Disciplina d : disciplinas) {
            quantidadeVagas += d.getCurso().getVagas();
        }
        return quantidadeVagas;
    }

    private List<Disciplina> obtenhaDisciplinasVinculadas(Livro livro) {
        Collection<Bibliografia> bibliografias = livro.getBibliografia();
        List<Disciplina> disciplinas = new ArrayList<Disciplina>();

        for (Bibliografia b : bibliografias) {
            disciplinas.add(b.getDisciplina());
        }

        return disciplinas;
    }
    
    @Override
    public DAO getDAO() {
        return livroService.getDAO();
    }

    public BibliotecaServiceMock getBibliotecaService() {
        return bibliotecaService;
    }

    public LivroService getLivroService() {
        return livroService;
    }

    public ParametrosService getParametrosService() {
        return parametrosService;
    }
}