/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model.importacaobibliografia;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Alunoinf_2
 */
public class LivroImportadoTest {
    
    public LivroImportadoTest() {
    }
static  LivroImportado instance = new LivroImportado();
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        
        instance.setAnoPublicacao("2012");
        instance.setAutor("Autor");
        instance.setEdicao("1");
        instance.setIsbn10("Isnb11");
        instance.setIsbn13("Isbn13");
        instance.setTitulo("Titulo");
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTitulo method, of class LivroImportado.
     */
    @Test
    public void testGetTitulo() {
      
       
        String expResult = "Titulo";
        String result = instance.getTitulo();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setTitulo method, of class LivroImportado.
     */
    @Test
    public void testSetTitulo() {
      String expResult = "Titulo";
        String result = instance.getTitulo();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getEdicao method, of class LivroImportado.
     */
    @Test
    public void testGetEdicao() {
       
        String expResult = "1";
        String result = instance.getEdicao();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of setEdicao method, of class LivroImportado.
     */
    @Test
    public void testSetEdicao() {
        
        String expResult = "1";
        String result = instance.getEdicao();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of getAnoPublicacao method, of class LivroImportado.
     */
    @Test
    public void testGetAnoPublicacao() {
       
        String expResult = "2012";
        String result = instance.getAnoPublicacao();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAnoPublicacao method, of class LivroImportado.
     */
    @Test
    public void testSetAnoPublicacao() {
        String expResult = "2012";
        String result = instance.getAnoPublicacao();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAutor method, of class LivroImportado.
     */
    @Test
    public void testGetAutor() {
       
        String expResult = "Autor";
        String result = instance.getAutor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAutor method, of class LivroImportado.
     */
    @Test
    public void testSetAutor() {
        
        String expResult = "Autor";
        String result = instance.getAutor();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIsbn10 method, of class LivroImportado.
     */
    @Test
    public void testgetIsbn10() {
        
        
        String expResult = "Isnb11";
        String result = instance.getIsbn10();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setIsbn10 method, of class LivroImportado.
     */
    @Test
    public void testsetIsbn10() {
     
        String expResult = "Isnb11";
        String result = instance.getIsbn10();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getIsbn13 method, of class LivroImportado.
     */
    @Test
    public void testGetIsbn13() {
        
        String expResult = "Isbn13";
        String result = instance.getIsbn13();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIsbn13 method, of class LivroImportado.
     */
    @Test
    public void testSetIsbn13() {
        String expResult = "Isbn13";
        String result = instance.getIsbn13();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class LivroImportado.
     */
    @Test
    public void testToString() {
       
        String expResult = "LivroImportado{titulo=Titulo, edicao=1, anoPublicacao=2012, autor=Autor, isbn10=Isnb11, isbn13=Isbn13}";
        String result = instance.toString();
        assertEquals(expResult, result);
     
    }
}
