package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.model.dtos.LivroParaCotacao;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.biblioteca.BibliotecaServiceMock;
import br.ufg.inf.es.model.*;
import br.ufg.inf.es.persistencia.LivroDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 * testes para o service que trata os livros para cotacao
 *
 * @author Victor Carvalho
 */
public class LivroParaCotacaoServiceTest {

    private LivroParaCotacaoService service;
    private BibliotecaServiceMock bibliotecaService;
    private ParametrosService parametrosService;
    private LivroService livroService;
    private BibliografiaService bibliografiaService;

    @Before
    public void setUp() {
        service = new LivroParaCotacaoService();

        bibliotecaService = Mockito.mock(BibliotecaServiceMock.class);
        parametrosService = Mockito.mock(ParametrosService.class);
        livroService = Mockito.mock(LivroService.class);
        bibliografiaService = Mockito.mock(BibliografiaService.class);

        service.setBibliotecaService(bibliotecaService);
        service.setParametrosService(parametrosService);
        service.setLivroService(livroService);
        service.setBibliografiaService(bibliografiaService);
    }

    /**
     * Test of obtenhaLivrosParaCotacao method, of class
     * LivroParaCotacaoService.
     *
     * quando nao há bibliografias no banco
     */
    @Test
    public void testObtenhaLivrosParaCotacaoQuandoNaoHaBibliografias() throws ValidationException {
        Mockito.when(bibliografiaService.list()).thenReturn(new ArrayList<Bibliografia>());

        Collection<LivroParaCotacao> result = service.obtenhaLivrosParaCotacao();
        assertEquals("Nao deveria ter nenhum livro para cotacao", 0, result.size());
    }

    /**
     * Test of obtenhaLivrosParaCotacao method, of class
     * LivroParaCotacaoService.
     *
     * quando há bibliografia cadastrada e há a necessidade de se adquirir mais
     * livros
     */
    @Test
    public void testObtenhaLivrosParaCotacaoQuandoHaBibliografia() throws ValidationException {
        Integer parametroMec = 5;
        String isbn10 = "isbn10";
        String nomeLivro = "nome";
        Integer qtdeVagas = 30;

        Curso curso = new Curso();
        curso.setVagas(qtdeVagas);

        Livro livro = new Livro();
        livro.setIsbn10(isbn10);
        livro.setTitulo(nomeLivro);

        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Integracao");
        disciplina.setCurso(curso);

        Bibliografia bibliografia = new Bibliografia();
        bibliografia.setDisciplina(disciplina);
        bibliografia.setLivro(livro);

        Mockito.when(bibliografiaService.list()).thenReturn(Arrays.asList(bibliografia));
        Mockito.when(parametrosService.obtenhaParametroMEC()).thenReturn(parametroMec);
        Mockito.when(bibliotecaService.obtenhaQuantidadeExistente(isbn10)).thenReturn(1);

        Collection<LivroParaCotacao> result = service.obtenhaLivrosParaCotacao();
        assertEquals("Deveria ter um livro para cotacao", 1, result.size());

        LivroParaCotacao livroParaCotacao = (LivroParaCotacao) result.toArray()[0];

        assertEquals(new Integer(5), livroParaCotacao.getQuantidadeLivrosFaltando());
        assertEquals(new Integer(1), livroParaCotacao.getQuantidadeLivrosDisponiveis());
        assertEquals(qtdeVagas, livroParaCotacao.getQuantidadeVagas());
        assertEquals(parametroMec, livroParaCotacao.getParametroMec());
        assertEquals(isbn10, livroParaCotacao.getIsbn());
        assertEquals(nomeLivro, livroParaCotacao.getNomeLivro());
    }

    /**
     * Test of obtenhaLivrosParaCotacao method, of class
     * LivroParaCotacaoService.
     *
     * quando há bibliografia cadastrada e há livros na biblioteca em quantidade
     * suficiente para atender o parametro do mec.
     */
    @Test
    public void testObtenhaLivrosParaCotacaoQuandoHaLivrosOSuficiente() throws ValidationException {
        Integer parametroMec = 5;
        String isbn10 = "isbn10";
        String nomeLivro = "nome";
        Integer qtdeVagas = 30;

        Curso curso = new Curso();
        curso.setVagas(qtdeVagas);

        Livro livro = new Livro();
        livro.setIsbn10(isbn10);
        livro.setTitulo(nomeLivro);

        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Integracao");
        disciplina.setCurso(curso);

        Bibliografia bibliografia = new Bibliografia();
        bibliografia.setDisciplina(disciplina);
        bibliografia.setLivro(livro);

        Mockito.when(bibliografiaService.list()).thenReturn(Arrays.asList(bibliografia));
        Mockito.when(parametrosService.obtenhaParametroMEC()).thenReturn(parametroMec);
        Mockito.when(bibliotecaService.obtenhaQuantidadeExistente(isbn10)).thenReturn(6);

        Collection<LivroParaCotacao> result = service.obtenhaLivrosParaCotacao();
        assertEquals("Nao deveria ter nehum livro para cotacao", 0, result.size());
    }

    /**
     * Test of getDAO method, of class LivroParaCotacaoService.
     */
    @Test
    public void testGetDAO() {
        LivroDAO daoEsperado = new LivroDAO();
        Mockito.when(livroService.getDAO()).thenReturn(daoEsperado);
        DAO result = service.getDAO();
        assertEquals(daoEsperado, result);
    }

    /**
     * Test of getBibliografiaService method, of class LivroParaCotacaoService.
     */
    @Test
    public void testGetBibliografiaService() {
        BibliografiaService result = service.getBibliografiaService();
        assertEquals(bibliografiaService, result);
    }

    /**
     * Test of setBibliografiaService method, of class LivroParaCotacaoService.
     */
    @Test
    public void testSetBibliografiaService() {
        service.setBibliografiaService(new BibliografiaService());
    }

    /**
     * Test of getBibliotecaService method, of class LivroParaCotacaoService.
     */
    @Test
    public void testGetBibliotecaService() {
        BibliotecaServiceMock result = service.getBibliotecaService();
        assertEquals(bibliotecaService, result);
    }

    /**
     * Test of setBibliotecaService method, of class LivroParaCotacaoService.
     */
    @Test
    public void testSetBibliotecaService() {
        service.setBibliotecaService(new BibliotecaServiceMock());
    }

    /**
     * Test of getLivroCursos method, of class LivroParaCotacaoService.
     */
    @Test
    public void testGetLivroCursos() {
        Map expResult = new HashMap();
        Map result = service.getLivroCursos();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLivroCursos method, of class LivroParaCotacaoService.
     */
    @Test
    public void testSetLivroCursos() {
        Map<Livro, List<Curso>> livroCursos = new HashMap<Livro, List<Curso>>();
        service.setLivroCursos(livroCursos);
    }

    /**
     * Test of getLivroService method, of class LivroParaCotacaoService.
     */
    @Test
    public void testGetLivroService() {
        LivroService result = service.getLivroService();
        assertEquals(livroService, result);
    }

    /**
     * Test of setLivroService method, of class LivroParaCotacaoService.
     */
    @Test
    public void testSetLivroService() {
        service.setLivroService(new LivroService());
    }

    /**
     * Test of getParametrosService method, of class LivroParaCotacaoService.
     */
    @Test
    public void testGetParametrosService() {
        ParametrosService result = service.getParametrosService();
        assertEquals(parametrosService, result);
    }

    /**
     * Test of setParametrosService method, of class LivroParaCotacaoService.
     */
    @Test
    public void testSetParametrosService() {
        service.setParametrosService(new ParametrosService());
    }
}