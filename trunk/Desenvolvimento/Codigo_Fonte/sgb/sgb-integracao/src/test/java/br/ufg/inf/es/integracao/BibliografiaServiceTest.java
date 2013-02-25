package br.ufg.inf.es.integracao;

import br.ufg.inf.es.persistencia.BibliografiaDAO;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * Testes do servce da entidade Bibliografia
 * 
 * @author Victor Carvalho
 */
public class BibliografiaServiceTest {

    /**
     * Test of getDAO method, of class BibliografiaService.
     */
    @Test
    public void testGetDAODeveVirNuloSemAInjecaoDoSpring() {
        BibliografiaService instance = new BibliografiaService();
        BibliografiaDAO result = instance.getDAO();
        assertNull(result);
    }
}
