package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.Comunicacao;
import br.ufg.inf.es.persistencia.ComunicacaoDAO;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * Testes para o servico de comunicacao
 *
 * @author Victor Carvalho
 */
public class ComunicacaoServiceTest {

    private ComunicacaoDAO comunicacaoDAO;
    private ComunicacaoService comunicacaoService;
    private Comunicacao comunicacao;
    private Long idInsersao = 1L;

    @Before
    public void setUp() {
        comunicacao = new Comunicacao();
        comunicacaoService = new ComunicacaoService();
        comunicacaoDAO = mock(ComunicacaoDAO.class);
        
        comunicacaoService.setDAO(comunicacaoDAO);
    }

    /**
     * Test of getDAO method, of class ComunicacaoService.
     */
    @Test
    public void testGetDAO() {
        assertEquals(comunicacaoDAO, comunicacaoService.getDAO());
    }

    /**
     * Test of getComunicacao method, of class ComunicacaoService.
     */
    @Test
    public void testGetComunicacao() {
        when(comunicacaoDAO.getComunicacao()).thenReturn(comunicacao);
        Comunicacao result = comunicacaoService.getComunicacao();
        assertEquals(comunicacao, result);
    }

    /**
     * Test of insert method, of class ComunicacaoService.
     */
    @Test
    public void testInsert() throws Exception {
        when(comunicacaoDAO.insert(comunicacao)).thenReturn(idInsersao);
        Long result = comunicacaoService.insert(comunicacao);
        assertEquals(idInsersao, result);
    }

    /**
     * Test of editar method, of class ComunicacaoService.
     */
    @Test
    public void testEditar() throws Exception {
        comunicacaoService.editar(comunicacao);
    }
}
