package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.aop.RNGExecutor;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.persistencia.AutorDAO;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * Classe de teste da Estória de Usuário 12
 *
 * @author Cássio Augusto Silva de Freitas
 */
public class AutorServiceTest extends TestCase {

    private AutorDAO autorDAO;
    private AutorService autorService;

    @Before
    @Override
    public void setUp() {

        ApplicationContext springContext = new FileSystemXmlApplicationContext("/src/main/resources/appContext-AutorServiceTest.xml");

        autorService = springContext.getBean(AutorService.class);

        RNGExecutor rngExecutor = springContext.getBean(RNGExecutor.class);

        rngExecutor.setApplicationContext(springContext);

        autorDAO = Mockito.mock(AutorDAO.class);

        autorService.setDao(autorDAO);
    }

    /**
     * Teste insere autor válido throws ValidationException
     */
    @Test
    public void testInserirAutor001() throws ValidationException {

        Autor autor = new Autor();
        autor.setNome("Autor 1");
        autor.setSobrenome("Sobrenome 1");

        Mockito.when(autorDAO.insert(autor)).thenReturn(Long.MIN_VALUE);

        Long id = autorService.insert(autor);

        Mockito.verify(autorDAO).insert(autor);

        Assert.assertEquals(Long.MIN_VALUE, id.longValue());
    }

    /**
     * Teste verifica se o método do Dao foi chamado
     *
     * @throws ValidationException
     */
    @Test
    public void testInserirAutor002() throws ValidationException {

        Autor autor = new Autor();

        autor.setNome("joaquina");

        autor.setSobrenome("Sobrenome 1");

        Mockito.when(autorDAO.insert(autor)).thenReturn(Long.MIN_VALUE);

        autorService.insert(autor);

        Mockito.verify(autorDAO).insert(autor);

    }

    /**
     * Teste inserir Autor com nome Vazio
     *
     * @throws ValidationException
     */
    @Test
    public void testInserirAutor003() throws ValidationException {

        Autor autor = new Autor();
        autor.setNome("");
        autor.setSobrenome("Sobrenome 1");

        try {
            autorService.insert(autor);
        } catch (ValidationException ex) {
            Assert.assertTrue("O nome do autor não pode ser vazio, keyMessage: " + ex.getKeyMessage(), true);
        }
    }

    /**
     * Teste inserir Autor com sobrenome Vazio
     *
     * @throws ValidationException
     */
    @Test
    public void testInserirAutor004() throws ValidationException {

        Autor autor = new Autor();
        autor.setNome("Autor 1");
        autor.setSobrenome("");

        try {
            autorService.insert(autor);
        } catch (ValidationException ex) {
            Assert.assertTrue("O sobrenome do autor não pode ser vazio, keyMessage: " + ex.getKeyMessage(), true);
        }
    }

    /**
     * Teste inserir Autor com nome e sobrenome Vazio
     *
     * @throws ValidationException
     */
    @Test
    public void testInserirAutor005() throws ValidationException {
        Autor autor = new Autor();
        autor.setNome("");
        autor.setSobrenome("");

        try {
            autorService.insert(autor);
        } catch (ValidationException ex) {
            Assert.assertTrue("O nome e sobrenome do autor não pode ser vazio, keyMessage: " + ex.getKeyMessage(), true);
        }
    }

    /**
     * Teste inserir Autor com nome e sobrenome Nullos
     *
     * @throws ValidationException
     */
    @Test
    public void testInserirAutor006() throws ValidationException {

        Autor autor = new Autor();
        autor.setNome(null);
        autor.setSobrenome(null);

        try {
            autorService.insert(autor);
        } catch (ValidationException ex) {
            Assert.assertTrue("O nome e sobrenome do autor não pode ser null, keyMessage: " + ex.getKeyMessage(), true);
        }
    }

    /**
     * Test of getDAO method, of class AutorService.
     */
    @Test
    public void testGetDAO() {
        AutorService instance = new AutorService();
        AutorDAO result = instance.getDAO();
        assertNull(result);
    }

    /**
     * Test of setDao method, of class AutorService.
     */
    @Test
    public void testSetDao() {
        AutorDAO dao = new AutorDAO();
        AutorService instance = new AutorService();

        assertNull(instance.getDAO());

        instance.setDao(dao);

        assertNotNull(instance.getDAO());
    }
}