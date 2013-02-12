package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.excptions.LivroParaCotacaoException;
import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.biblioteca.BibliotecaServiceMock;
import br.ufg.inf.es.model.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    // TODO Remover o mock pelo serviço real da biblioteca
    /** Campo bibliotecaService*/
    @Autowired
    private BibliotecaServiceMock bibliotecaService;
    
    /** Campo parametrosService*/
    @Autowired
    private ParametrosService parametrosService;
    
    /** Campo livroService*/
    @Autowired
    private LivroService livroService;
    
    /** Campo bibliografiaService*/
    @Autowired
    private BibliografiaService bibliografiaService;
    
    /** Campo livroCursos*/
    private Map<Livro, List<Curso>> livroCursos = new HashMap<Livro, List<Curso>>();

    /**
     * Obtem uma listagem dos livros necessários para cotação
     *
     * @return lista de livros para cotacao
     */
    public Collection<LivroParaCotacao> obtenhaLivrosParaCotacao() {
        Collection<Bibliografia> bibliografias = bibliografiaService.list();
        adicioneCursosELivros(bibliografias);
        Collection<LivroParaCotacao> dtos = new ArrayList<LivroParaCotacao>();

        for (Entry<Livro, List<Curso>> entry : livroCursos.entrySet()) {
            Livro livro = entry.getKey();
            List<Curso> cursos = entry.getValue();
            Integer quantidadeVagas = 0;

            for (Curso curso : cursos) {
                quantidadeVagas += curso.getVagas();
            }

            if (quantidadeVagas > 0) {
                try {
                    dtos.add(obtenhaLivroParaCotacao(livro, quantidadeVagas));
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
     * Método que adiciona os livros e cursos para a lista a ser enviada para a cotação.
     *
     * @param bibliografias
     */
    private void adicioneCursosELivros(Collection<Bibliografia> bibliografias) {
        for (Bibliografia b : bibliografias) {
            Livro livro = b.getLivro();
            Disciplina disciplina = b.getDisciplina();

            adicioneLivro(livro, disciplina.getCurso());
        }
    }

    /**
     * Adiciona os livros e cursos para a lista de cursos da classe.
     * @param livro
     * @param curso
     */
    private void adicioneLivro(Livro livro, Curso curso) {
        List<Curso> cursos = new ArrayList<Curso>();
        if (livroCursos.containsKey(livro)) {
            cursos = livroCursos.get(livro);

            if (!cursos.contains(curso)) {
                livroCursos.remove(livro);
                cursos.add(curso);
                livroCursos.put(livro, cursos);
            }

        } else {
            cursos.add(curso);
            livroCursos.put(livro, cursos);
        }
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

        Integer qtdeLivrosNecessarios = dividaComRoundUp(quantidadeVagas, parametroMec);

        Integer qtdeLivrosDisponiveis = bibliotecaService.obtenhaQuantidadeExistente(livro.getIsbn10());

        if (qtdeLivrosDisponiveis < qtdeLivrosNecessarios) {
            Integer qtdeParaCotacao = qtdeLivrosNecessarios - qtdeLivrosDisponiveis;

            return new LivroParaCotacao(quantidadeVagas, parametroMec,
                    qtdeLivrosDisponiveis, qtdeParaCotacao, livro.getTitulo(), livro.getIsbn10());
        } else {
            throw new LivroParaCotacaoException("Existem livros em quantidade "
                    + "suficiente para atender à todas as vagas");
        }
    }

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
     * @return dao do livroService
     */
    @Override
    public DAO getDAO() {
        return livroService.getDAO();
    }
}