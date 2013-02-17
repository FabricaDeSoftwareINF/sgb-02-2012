package br.ufg.inf.es.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Victor Carvalho
 */
public class ComunicacaoTest {

    private Comunicacao comunicacao;
    private String service = "service";
    private boolean ssl = false;
    private boolean tsl = false;
    private String port = "port";
    private String usuario = "usuario";
    private byte[] senha = "senha".getBytes();

    @Before
    public void setUp() {
        comunicacao = new Comunicacao();
        comunicacao.setService(service);
        comunicacao.setSsl(ssl);
        comunicacao.setTsl(tsl);
        comunicacao.setPort(port);
        comunicacao.setUsuario(usuario);
        comunicacao.setSenha(senha);
    }

    /**
     * Test of getService method, of class Comunicacao.
     */
    @Test
    public void testGetService() {
        assertEquals(service, comunicacao.getService());
    }

    /**
     * Test of isSsl method, of class Comunicacao.
     */
    @Test
    public void testIsSsl() {
        assertEquals(ssl, comunicacao.isSsl());
    }

    /**
     * Test of isTsl method, of class Comunicacao.
     */
    @Test
    public void testIsTsl() {
        assertEquals(tsl, comunicacao.isTsl());
    }

    /**
     * Test of getPort method, of class Comunicacao.
     */
    @Test
    public void testGetPort() {
        assertEquals(port, comunicacao.getPort());
    }

    /**
     * Test of getUsuario method, of class Comunicacao.
     */
    @Test
    public void testGetUsuario() {
        assertEquals(usuario, comunicacao.getUsuario());
    }
    
    /**
     * Test of getSenha method, of class Comunicacao.
     */
    @Test
    public void testGetSenha() {
        assertNotNull(comunicacao.getSenha());
    }
}