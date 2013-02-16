/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import br.ufg.inf.es.model.dtos.LivroParaCotacao;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Alunoinf_2
 */
public class LivroParaCotacaoTest {

    public LivroParaCotacaoTest() {
    }
    static LivroParaCotacao instance;

    @BeforeClass
    public static void setUpClass() throws Exception {
        Integer quantidadeVagas = 50;
        Integer parametroMec = 5;
        Integer quantidadeLivrosDisponiveis = 5;
        Integer quantidadeLivrosFaltando = 5;
        String nomeLivro = "A";
        String isbn = "11111";
        instance = new LivroParaCotacao(quantidadeVagas, parametroMec, quantidadeLivrosDisponiveis, quantidadeLivrosFaltando, nomeLivro, isbn);

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getIsbn method, of class LivroParaCotacao.
     */
    @Test
    public void testGetIsbn() {

        String expResult = "11111";
        String result = instance.getIsbn();
        assertEquals(expResult, result);

    }

    /**
     * Test of getNomeLivro method, of class LivroParaCotacao.
     */
    @Test
    public void testGetNomeLivro() {


      
        String expResult = "A";
        String result = instance.getNomeLivro();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getParametroMec method, of class LivroParaCotacao.
     */
    @Test
    public void testGetParametroMec() {
        
        Integer expResult = 5;
        Integer result = instance.getParametroMec();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getQuantidadeLivrosDisponiveis method, of class LivroParaCotacao.
     */
    @Test
    public void testGetQuantidadeLivrosDisponiveis() {
        
        Integer expResult = 5;
        Integer result = instance.getQuantidadeLivrosDisponiveis();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getQuantidadeLivrosFaltando method, of class LivroParaCotacao.
     */
    @Test
    public void testGetQuantidadeLivrosFaltando() {
        
        Integer expResult = 5;
        Integer result = instance.getQuantidadeLivrosFaltando();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getQuantidadeVagas method, of class LivroParaCotacao.
     */
    @Test
    public void testGetQuantidadeVagas() {
       
        Integer expResult = 50;
        Integer result = instance.getQuantidadeVagas();
        assertEquals(expResult, result);
        
    }
}
