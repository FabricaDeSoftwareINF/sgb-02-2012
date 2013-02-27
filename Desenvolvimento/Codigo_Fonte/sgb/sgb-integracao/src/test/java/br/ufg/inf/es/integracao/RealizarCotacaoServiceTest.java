package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.cotacao.*;
import br.ufg.inf.es.model.*;
import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;
import br.ufg.inf.es.persistencia.CotacaoDAO;
import br.ufg.inf.es.persistencia.ListaCotacaoDAO;
import br.ufg.inf.es.persistencia.LivroDAO;
import br.ufg.inf.es.persistencia.ParametrosDAO;
import br.ufg.inf.es.persistencia.biblioteca.LivrosBibliotecaDAO;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * Testes do servico de cotacao
 * 
 * @author Marco Aurélio Camargo Oliveira, Victor Carvalho
 */
public class RealizarCotacaoServiceTest {

    private ListaCotacaoDAO dao;
    private RealizarCotacaoService service;
    private LivroDAO livroDao;
    private CotacaoDAO cotacaoDao;
    private LivrosBibliotecaDAO bibliotecaDao;
    private ParametrosService parametrosService;

    @Before
    public void setUp() throws ValidationException {
        service = new RealizarCotacaoService();
        dao = mock(ListaCotacaoDAO.class);
        livroDao = mock(LivroDAO.class);
        cotacaoDao = mock(CotacaoDAO.class);
        bibliotecaDao = mock(LivrosBibliotecaDAO.class);
        parametrosService = mock(ParametrosService.class);
        Parametros parametros = new Parametros();
        parametros.setValorFrete(new BigDecimal(100));
        when(parametrosService.find()).thenReturn(parametros);

        service.setDao(dao);
        service.setLivroDao(livroDao);
        service.setBibliotecaDao(bibliotecaDao);
        service.setCotacaoDao(cotacaoDao);
        service.setParametrosService(parametrosService);
    }

    /**
     * Test of getDAO method, of class RealizarCotacaoService.
     */
    @Test
    public void testGetDAO() {
        ListaCotacaoDAO result = service.getDAO();
        assertEquals(dao, result);
    }

    /**
     * Test of setDao method, of class RealizarCotacaoService.
     */
    @Test
    public void testSetDao() {
        ListaCotacaoDAO novoDao = new ListaCotacaoDAO();
        service.setDao(novoDao);
        ListaCotacaoDAO result = service.getDAO();
        assertEquals(novoDao, result);
    }

    /**
     * Test of getDao method, of class RealizarCotacaoService.
     */
    @Test
    public void testGetDao() {
        ListaCotacaoDAO result = service.getDao();
        assertEquals(dao, result);
    }

    /**
     * Test of getLivroDao method, of class RealizarCotacaoService.
     */
    @Test
    public void testGetLivroDao() {
        LivroDAO result = service.getLivroDao();
        assertEquals(livroDao, result);
    }

    /**
     * Test of getCotacaoDao method, of class RealizarCotacaoService.
     */
    @Test
    public void testGetCotacaoDao() {
        CotacaoDAO result = service.getCotacaoDao();
        assertEquals(cotacaoDao, result);
    }

    /**
     * Test of realizarCotacao method, of class RealizarCotacaoService.
     */
//    @Test
//    public void testRealizarCotacaoEstrangeira() throws NotFoundException, SQLException {
//        
//        Collection<ItemListaCompras> livros = prepareMocks(false);
//        ListaCotacao lista = service.realizarCotacao(livros);
//        assertNotNull(lista);
//        assertEquals(1, lista.getItensListaCotacao().size());
//    }
//
//    @Test
//    public void testRealizarCotacaoNacional() throws NotFoundException, SQLException {
//        Collection livros = prepareMocks(true);
//        ListaCotacao lista = service.realizarCotacao(livros);
//        assertNotNull(lista);
//        assertEquals(1, lista.getItensListaCotacao().size());
//    }

    private Collection prepareMocks(boolean isNacional) throws NotFoundException, SQLException {
        Long idLivro = 1L;
        Livraria livraria = new Livraria();
        OfertaLivro oferta = new OfertaLivro();
        oferta.setPrecoLivro("10");
        oferta.setLinkImagemLIvro("link");

        ResultadoCotacao rc = new ResultadoCotacao(livraria, oferta);

        Livro livro = new Livro();
        livro.setId(idLivro);
        livro.setTitulo("titulo");

        Cotador cotadorGoogleShop;
        if (isNacional) {
            livro.setEstrangeiro(false);
            cotadorGoogleShop = mock(CotadorBuscape.class);
        } else {
            livro.setEstrangeiro(true);
            cotadorGoogleShop = mock(CotadorGoogleShop.class);
        }
        when(cotadorGoogleShop.buscarOfertas(livro)).thenReturn(Arrays.asList(rc));

        LivroBiblioteca livroBiblioteca = new LivroBiblioteca();
        livroBiblioteca.setQuantidade(1);

        when(parametrosService.list()).thenReturn(Arrays.asList(new Parametros()));
        when(bibliotecaDao.getLivrosBibliotecaTitulo(livro.getTitulo())).thenReturn(Arrays.asList(livroBiblioteca));

        when(livroDao.obterQuantidadeDeAlunosPorLivro(idLivro)).thenReturn(60);
        ItemListaCompras item = new ItemListaCompras();
        item.setLivro(livro);
        return Arrays.asList(item);
    }

    /**
     * Test of getBibliotecaDao method, of class RealizarCotacaoService.
     */
    @Test
    public void testGetBibliotecaDao() {
        LivrosBibliotecaDAO result = service.getBibliotecaDao();
        assertEquals(bibliotecaDao, result);
    }

    /**
     * Test of getParametrosDao method, of class RealizarCotacaoService.
     */
    @Test
    public void testGetParametrosDao() {
        ParametrosService result = service.getParametrosService();
        assertEquals(parametrosService, result);
    }

    /**
     * Test of salvarListaCotacao method, of class RealizarCotacaoService.
     */
    @Test
    public void testSalvarListaCotacao() throws Exception {
        Long idEsperado = 1L;
        ListaCotacao listaCotacao = new ListaCotacao();

        when(dao.insert(listaCotacao)).thenReturn(idEsperado);
        Long result = service.insert(listaCotacao);

        assertEquals(idEsperado, result);
    }
}
