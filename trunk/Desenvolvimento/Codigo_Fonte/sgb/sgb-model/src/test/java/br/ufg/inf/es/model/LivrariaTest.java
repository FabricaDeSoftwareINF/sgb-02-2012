package br.ufg.inf.es.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Victor Carvalho
 */
public class LivrariaTest {

    private Livraria livraria;
    private String nome = "nome";
    private String site = "site";
    private String urlLogo = "logo";

    /**
     * setup
     */
    @Before
    public void setUp() {
        livraria = new Livraria();

        livraria.setNome(nome);
        livraria.setSite(site);
        livraria.setUrlLogo(urlLogo);
    }

    /**
     * Test of getNome method, of class Livraria.
     */
    @Test
    public void testGetNome() {
        assertEquals(nome, livraria.getNome());
    }

    /**
     * Test of getSite method, of class Livraria.
     */
    @Test
    public void testGetSite() {
        assertEquals(site, livraria.getSite());
    }

    /**
     * Test of getUrlLogo method, of class Livraria.
     */
    @Test
    public void testGetUrlLogo() {
        assertEquals(urlLogo, livraria.getUrlLogo());
    }
}