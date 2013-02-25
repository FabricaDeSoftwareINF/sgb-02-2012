package br.ufg.inf.es.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Victor Carvalho
 */
public class PerfilTest {

    private Perfil perfil;
    private String tipo = "tipo";

    /**
     * setup
     */
    @Before
    public void setUp() {
        perfil = new Perfil();

        perfil.setTipo(tipo);
    }

    /**
     * Test of getTipo method, of class Perfil.
     */
    @Test
    public void testGetTipo() {
        assertEquals(tipo, perfil.getTipo());
    }

    /**
     * Test of getTipo method, of class Perfil.
     */
    @Test
    public void testGetConstructor() {
        perfil = new Perfil(tipo);
        assertEquals(tipo, perfil.getTipo());
    }
}
