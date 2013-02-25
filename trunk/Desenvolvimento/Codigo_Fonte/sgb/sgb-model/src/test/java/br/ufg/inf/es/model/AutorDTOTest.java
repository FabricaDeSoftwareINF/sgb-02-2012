package br.ufg.inf.es.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Victor Carvalho
 */
public class AutorDTOTest {

    private AutorDTO autorDTO;
    private String nome = "nome";
    private String sobreNome = "sobrenome";

    /**
     * setup
     */
    @Before
    public void setUp() {
        autorDTO = new AutorDTO();
        autorDTO.setNome(nome);
        autorDTO.setSobrenome(sobreNome);
    }

    /**
     * Test of getNome method, of class AutorDTO.
     */
    @Test
    public void testGetNome() {
        assertEquals(nome, autorDTO.getNome());
    }

    /**
     * Test of getSobrenome method, of class AutorDTO.
     */
    @Test
    public void testGetSobrenome() {
        assertEquals(sobreNome, autorDTO.getSobrenome());
    }
}