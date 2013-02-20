package br.ufg.inf.es.integracao;

import br.ufg.inf.es.persistencia.CotacoesLivroDAO;
import br.ufg.inf.es.persistencia.LivroDAO;
import br.ufg.inf.es.model.Livro;
import java.util.Arrays;
import java.util.Collection;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * Testes para o servico de cotacao
 *
 * @author Victor Carvalho
 */
public class CotacaoServiceTest {

    private CotacaoService cotacaoService;
    private CotacoesLivroDAO cotacoesLivroDAO;
    private LivroDAO livroDao;
    private Collection<Livro> livros = Arrays.asList(new Livro());

    /**
     * setup
     */
    @Before
    public void setUp() {
        cotacoesLivroDAO = mock(CotacoesLivroDAO.class);
        livroDao = mock(LivroDAO.class);

        cotacaoService = new CotacaoService();
        cotacaoService.setDao(cotacoesLivroDAO);
        cotacaoService.setLivroDao(livroDao);
    }

    /**
     * Test of getDAO method, of class CotacaoService.
     */
    @Test
    public void testGetDAO() {
        assertEquals(cotacoesLivroDAO, cotacaoService.getDAO());
    }

    /**
     * Test of listarLivros method, of class CotacaoService.
     */
    @Test
    public void testListarLivros() {
        when(livroDao.list()).thenReturn(livros);
        Collection result = cotacaoService.listarLivros();
        assertEquals(livros, result);
    }

    /**
     * Test of getLivroDao method, of class CotacaoService.
     */
    @Test
    public void testGetLivroDao() {
        assertEquals(livroDao, cotacaoService.getLivroDao());
    }
}