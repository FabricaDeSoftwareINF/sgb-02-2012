package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;
import br.ufg.inf.es.persistencia.biblioteca.DBBibliotecaConfigDAO;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * Testes para o servico de configuracao da biblioteca
 *
 * @author Victor Carvalho
 */
public class DBBibliotecaConfigServiceTest {

    private DBBibliotecaConfigService service;
    private DBBibliotecaConfigDAO dao;
    private DBBibliotecaConfig dbConfig;
    private Long idInsert = 1L;

    @Before
    public void setUp() {
        dbConfig = new DBBibliotecaConfig();

        service = new DBBibliotecaConfigService();
        dao = mock(DBBibliotecaConfigDAO.class);

        service.setDao(dao);
    }

    /**
     * Test of getDAO method, of class DBBibliotecaConfigService.
     */
    @Test
    public void testGetDAO() {
        assertEquals(dao, service.getDAO());
    }

    /**
     * Test of getBibliotecaCfg method, of class DBBibliotecaConfigService.
     */
    @Test
    public void testGetBibliotecaCfg() {
        when(dao.getBibliotecaCfg()).thenReturn(dbConfig);
        DBBibliotecaConfig result = service.getBibliotecaCfg();
        assertEquals(dbConfig, result);
    }

    /**
     * Test of insert method, of class DBBibliotecaConfigService.
     */
    @Test
    public void testInsert() throws Exception {
        when(dao.insert(dbConfig)).thenReturn(idInsert);

        Long result = service.insert(dbConfig);
        assertEquals(idInsert, result);
    }

    /**
     * Test of editar method, of class DBBibliotecaConfigService.
     */
    @Test
    public void testEditar() throws Exception {
        service.editar(dbConfig);
    }
}
