package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.AutorDTO;
import br.ufg.inf.es.persistencia.AutorDAO;
import java.util.Arrays;
import java.util.Collection;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * Classe de teste da Estória do servico de autores
 *
 * @author Victor Carvalho
 */
public class AutorServiceUnitTest {

    private AutorDAO autorDAO;
    private AutorService autorService;
    private Autor autor;
    private Long idAutor;

    @Before
    public void setUp() {
        autorService = new AutorService();

        autorDAO = mock(AutorDAO.class);
        autorService.setDao(autorDAO);

        autor = new Autor();
        idAutor = 1l;
    }

    /**
     * Teste insere autor válido throws ValidationException
     */
    @Test
    public void testInserirAutor() throws ValidationException {
        autor.setNome("Autor");
        autor.setSobrenome("Sobrenome");

        when(autorDAO.insert(autor)).thenReturn(idAutor);
        Long id = autorService.insert(autor);
        verify(autorDAO).insert(autor);

        assertEquals(idAutor, id);
    }

    /**
     * Test of getDAO method, of class AutorService.
     */
    @Test
    public void testGetDAO() {
        assertEquals(autorDAO, autorService.getDAO());
    }

    /**
     * Test of buscaTodosAutores method, of class AutorService.
     */
    @Test
    public void testBuscaTodosAutores() {
        String filtroNome = "filtro";
        Collection expResult = Arrays.asList(new AutorDTO());

        when(autorDAO.listarAutores(filtroNome)).thenReturn(expResult);

        Collection result = autorService.buscaTodosAutores(filtroNome);
        assertEquals(expResult, result);
    }

    /**
     * Test of editar method, of class AutorService.
     */
    @Test
    public void testEditar() throws Exception {
        autorService.editar(autor);

        verify(autorDAO).update(autor);
    }

    /**
     * Test of complete method, of class AutorService.
     */
    @Test
    public void testComplete() {
        String query = "nome";
        autor.setNome(query);

        Autor autor2 = new Autor();
        autor2.setNome("outroAutor");

        Collection expResult = Arrays.asList(autor, autor2);

        when(autorDAO.list()).thenReturn(expResult);

        Collection result = autorService.complete(query);
        assertEquals(1, result.size());
    }
}