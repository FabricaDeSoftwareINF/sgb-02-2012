/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.ItemListaCompras;
import br.ufg.inf.es.model.ItemListaCotacao;
import br.ufg.inf.es.model.Livro;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.stubbing.OngoingStubbing;

/**
 *
 * @author Cássio Augusto Silva de Freitas
 */
public class LivroDAOTest {

    private SessionFactory factory;
    private Session session;
    private Criteria criteria;
    private Connection connection;
    private LivroDAO dao;
    private LivroDAO spyDAO;

    @Before
    public void setUp() {

        dao = new LivroDAO();
        this.factory = mock(SessionFactory.class);
        this.session = mock(Session.class);
        this.criteria = mock(Criteria.class);
        this.connection = mock(Connection.class);
        dao.setSessionFactory(factory);
        spyDAO = spy(new LivroDAO());
        spyDAO.setSessionFactory(factory);
    }

    /**
     * Método responsável por definir o retorno do Método openSesison() do mock
     * SessionFactory
     */
    public void preparaSessionFactoryMock() {
        when((factory.openSession())).thenReturn(session);
    }

    /**
     * Test of setSessionFactory method, of class DisciplinaDAO.
     */
    @Test
    public void testSetSessionFactory() {

        this.dao.setSessionFactory(this.factory);

        assertEquals(factory, dao.getSessionFactory());

    }

    /**
     * Test of getSessionFactory method, of class LivroDAO.
     */
    @Test
    public void testGetSessionFactory() {
        assertEquals(factory, dao.getSessionFactory());
    }

    /**
     * Test of getAutores method, of class LivroDAO.
     */
    @Test
    public void testGetAutores() {

        preparaSessionFactoryMock();
        
        Livro livro =  new Livro();
        livro.setTitulo("Titulo1");
        Autor autor1 =  new Autor();
        Autor autor2 =  new Autor();
        autor1.setNome("autor1");
        autor2.setNome("autor2");
        List<Autor> autores = Arrays.asList(autor1, autor2);
        livro.setAutores(autores);
        
        when(session.createCriteria(eq(Livro.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.setFetchMode(anyString(), any(FetchMode.class))).thenReturn(criteria);
        when(criteria.uniqueResult()).thenReturn(livro);
        
        Collection<Autor> result = this.dao.getAutores(Long.MIN_VALUE);
        assertEquals(result, autores);
    }

    /**
     * Test of getBibliografia method, of class LivroDAO.
     */
    @Test
    public void testGetBibliografia() {

        Collection bibliografias = Arrays.asList(new Autor());

        when(spyDAO.getCollection(anyLong(), anyString())).thenReturn(bibliografias);

        Collection<?> result = spyDAO.getBibliografia(1L);

        verify(spyDAO).getCollection(1L, "bibliografias");

        assertEquals(bibliografias, result);
    }

    /**
     * Test of buscaLivroPorTitulo method, of class LivroDAO.
     */
    @Test
    public void testBuscaLivroPorTitulo1() {

        List livros = Arrays.asList(new Livro(), new Livro());

        preparaSessionFactoryMock();

        when(session.createCriteria(eq(Livro.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(livros);

        Collection<Livro> result = this.dao.buscaLivroPorTitulo("");

        assertEquals(livros, result);
    }

    /**
     * Test of buscaLivroPorTitulo method, of class LivroDAO.
     */
    @Test
    public void testBuscaLivroPorTitulo2() {

        List livros = Arrays.asList(new Livro(), new Livro());

        preparaSessionFactoryMock();

        when(session.createCriteria(eq(Livro.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(livros);

        Collection<Livro> result = this.dao.buscaLivroPorTitulo(null);

        assertEquals(livros, result);
    }

    /**
     * Test of buscaLivroPorTitulo method, of class LivroDAO.
     */
    @Test
    public void testBuscaLivroPorTitulo3() {

        List livros = Arrays.asList(new Livro(), new Livro());

        preparaSessionFactoryMock();

        when(session.createCriteria(eq(Livro.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(livros);

        Collection<Livro> result = this.dao.buscaLivroPorTitulo("Livro 2");

        assertEquals(livros, result);
    }

    /**
     * Test of obterQuantidadeDeAlunosPorLivro method, of class LivroDAO.
     */
    @Test
    public void testObterQuantidadeDeAlunosPorLivro1() {

        this.preparaSessionFactoryMock();

        when(session.createCriteria(eq(Livro.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.createAlias(anyString(), anyString())).thenReturn(criteria);
        when(criteria.setProjection(any(Projection.class))).thenReturn(criteria);
        when(criteria.uniqueResult()).thenReturn(5);

        Integer result = this.dao.obterQuantidadeDeAlunosPorLivro(1L);

        assertEquals(result.intValue(), 5);
    }

    /**
     * Test of obterQuantidadeDeAlunosPorLivro method, of class LivroDAO.
     */
    @Test
    public void testObterQuantidadeDeAlunosPorLivro2() {

        this.preparaSessionFactoryMock();

        when(session.createCriteria(eq(Livro.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.createAlias(anyString(), anyString())).thenReturn(criteria);
        when(criteria.setProjection(any(Projection.class))).thenReturn(criteria);
        when(criteria.uniqueResult()).thenReturn(0);

        Integer result = this.dao.obterQuantidadeDeAlunosPorLivro(0L);

        assertEquals(result.intValue(), 0);
    }

    @Test
    public void testObterQuantidadeDeAlunosPorLivro3() {

        this.preparaSessionFactoryMock();

        when(session.createCriteria(eq(Livro.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.createAlias(anyString(), anyString())).thenReturn(criteria);
        when(criteria.setProjection(any(Projection.class))).thenReturn(criteria);
        when(criteria.uniqueResult()).thenReturn(null);

        Integer result = this.dao.obterQuantidadeDeAlunosPorLivro(0L);

        assertEquals(result.intValue(), 0);
    }

    /**
     * Test of list method, of class LivroDAO.
     */
    @Test
    public void testList() {

        List listaLivro = Arrays.asList(new Livro());

        preparaSessionFactoryMock();

        Query query = mock(Query.class);
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.list()).thenReturn(listaLivro);

        Collection<Livro> result = this.dao.list();

        assertEquals(listaLivro, result);
    }
    
    /**
     * Testes do método removeLivros
     */
    @Test(expected=ValidationException.class)
    public void testRemoveLivros() throws ValidationException {
        
        preparaSessionFactoryMock();
        
        Livro livroComun =  new Livro();
        Collection<Livro> livrosToRemove =  Arrays.asList(livroComun);
        
        ItemListaCompras ic = new ItemListaCompras();
        ic.setId(1L);
        ic.setLivro(livroComun);
        
        List<ItemListaCompras> itensCompras = Arrays.asList(ic);
        
        when(session.createCriteria(eq(ItemListaCompras.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(itensCompras);
        
        ItemListaCotacao iCotacao = new ItemListaCotacao();
        iCotacao.setId(1L);
        iCotacao.setLivro(livroComun); 
                
        List<ItemListaCotacao> itensCotacao = Arrays.asList(iCotacao);
        
        Criteria criteriaCotacao = mock(Criteria.class);
        
        when(session.createCriteria(eq(ItemListaCotacao.class))).thenReturn(criteriaCotacao);
        when(criteriaCotacao.add(any(Criterion.class))).thenReturn(criteriaCotacao);
        when(criteriaCotacao.list()).thenReturn(itensCotacao);
        
        this.dao.removeLivros(livrosToRemove);
    }
            
    
    /**
     * Testes do método removeLivros02
     */
    @Test
    public void testRemoveLivros2() throws ValidationException {
        
        preparaSessionFactoryMock();
        
        Livro livroComun =  new Livro();
        Collection<Livro> livrosToRemove =  Arrays.asList(livroComun);
        
        List<ItemListaCompras> itensCompras = Arrays.asList();
        
        when(session.createCriteria(eq(ItemListaCompras.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(itensCompras);
                
        List<ItemListaCotacao> itensCotacao = Arrays.asList();
        
        Criteria criteriaCotacao = mock(Criteria.class);
        
        when(session.createCriteria(eq(ItemListaCotacao.class))).thenReturn(criteriaCotacao);
        when(criteriaCotacao.add(any(Criterion.class))).thenReturn(criteriaCotacao);
        when(criteriaCotacao.list()).thenReturn(itensCotacao);
        
        this.dao.removeLivros(livrosToRemove);
    }
    
    
    /**
     * Testes do método removeLivros02
     */
    @Test
    public void testRemoveLivros3() throws ValidationException {
        
        preparaSessionFactoryMock();
        
        Livro livroComun =  new Livro();
        Collection<Livro> livrosToRemove =  Arrays.asList(livroComun);      

        List<ItemListaCompras> itensCompras = null;
        
        when(session.createCriteria(eq(ItemListaCompras.class))).thenReturn(criteria);
        when(criteria.add(any(Criterion.class))).thenReturn(criteria);
        when(criteria.list()).thenReturn(itensCompras);  
                
        List<ItemListaCotacao> itensCotacao = null;
        
        Criteria criteriaCotacao = mock(Criteria.class);
        
        when(session.createCriteria(eq(ItemListaCotacao.class))).thenReturn(criteriaCotacao);
        when(criteriaCotacao.add(any(Criterion.class))).thenReturn(criteriaCotacao);
        when(criteriaCotacao.list()).thenReturn(itensCotacao);
        
        this.dao.removeLivros(livrosToRemove);
    }
            
//            public void removeLivros(Collection<Livro> livros) throws ValidationException {
//        List<Livro> livrosNaoRemovidos = new ArrayList<Livro>();
//        for (Livro livro : livros) {
//            Criteria criteriaCompra = this.getSession().createCriteria(ItemListaCompras.class);
//            criteriaCompra.add(Restrictions.eq("livro", livro));
//            Collection<ItemListaCompras> itensCompra = criteriaCompra.list();
//            Criteria criteriaCotacao = this.getSession().createCriteria(ItemListaCotacao.class);
//            criteriaCotacao.add(Restrictions.eq("livro", livro));
//            Collection<ItemListaCotacao> itensCotacao = criteriaCotacao.list();
//            if ((itensCompra == null ||  itensCompra.isEmpty())
//                    && (itensCotacao == null || itensCotacao.isEmpty())) {
//                this.remove(livro);
//            } else {
//                livrosNaoRemovidos.add(livro);
//            }
//        }
//        if (!livrosNaoRemovidos.isEmpty()) {
//            throw new ValidationException("cadastro.livro.remocao.dependencia");
//        }
//    }
}
