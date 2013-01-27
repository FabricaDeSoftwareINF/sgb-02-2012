/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model.biblioteca;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Alunoinf_2
 */
public class LivroBibliotecaTest {
    
    public LivroBibliotecaTest() {
    }
static LivroBiblioteca instance = new LivroBiblioteca();
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
           
        instance.setAno("2012");
        instance.setAutor("Autor");
        instance.setEdicao(5);
        instance.setEditora("Editora");
        instance.setId(1L);
        instance.setIsbn("Isbn");
        instance.setNome("Livro");
        instance.setQuantidade(10);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAno method, of class LivroBiblioteca.
     */
    @Test
    public void testGetAno() {
       
     
        String expResult = "2012";
        String result = instance.getAno();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setAno method, of class LivroBiblioteca.
     */
    @Test
    public void testSetAno() {
       
        String expResult = "2012";
        String result = instance.getAno();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAutor method, of class LivroBiblioteca.
     */
    @Test
    public void testGetAutor() {
       
        String expResult = "Autor";
        String result = instance.getAutor();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of setAutor method, of class LivroBiblioteca.
     */
    @Test
    public void testSetAutor() {
        
        String expResult = "Autor";
        String result = instance.getAutor();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of getEdicao method, of class LivroBiblioteca.
     */
    @Test
    public void testGetEdicao() {
       
        Integer expResult = 5;
        Integer result = instance.getEdicao();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setEdicao method, of class LivroBiblioteca.
     */
    @Test
    public void testSetEdicao() {
       
       Integer expResult = 5;
        Integer result = instance.getEdicao();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEditora method, of class LivroBiblioteca.
     */
    @Test
    public void testGetEditora() {
      
        String expResult = "Editora";
        String result = instance.getEditora();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setEditora method, of class LivroBiblioteca.
     */
    @Test
    public void testSetEditora() {
        String expResult = "Editora";
        String result = instance.getEditora();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class LivroBiblioteca.
     */
    @Test
    public void testGetId() {
       
        Long expResult = 1L;
        Long result = instance.getId();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setId method, of class LivroBiblioteca.
     */
    @Test
    public void testSetId() {
       
      Long expResult = 1L;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIsbn method, of class LivroBiblioteca.
     */
    @Test
    public void testGetIsbn() {
      
        String expResult = "Isbn";
        String result = instance.getIsbn();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setIsbn method, of class LivroBiblioteca.
     */
    @Test
    public void testSetIsbn() {
     String expResult = "Isbn";
        String result = instance.getIsbn();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getNome method, of class LivroBiblioteca.
     */
    @Test
    public void testGetNome() {
       
        String expResult = "Livro";
        String result = instance.getNome();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setNome method, of class LivroBiblioteca.
     */
    @Test
    public void testSetNome() {
        String expResult = "Livro";
        String result = instance.getNome();
        assertEquals(expResult, result);
    }

    /**
     * Test of getQuantidade method, of class LivroBiblioteca.
     */
    @Test
    public void testGetQuantidade() {
        
        Integer expResult = 10;
        Integer result = instance.getQuantidade();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setQuantidade method, of class LivroBiblioteca.
     */
    @Test
    public void testSetQuantidade() {
        
         Integer expResult = 10;
        Integer result = instance.getQuantidade();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class LivroBiblioteca.
     */
    @Test
    public void testToString() {
       
        String expResult = "LivroBiblioteca{id=1, nome=Livro, isbn=Isbn, ano=2012, edicao=5, editora=Editora, autor=Autor, quantidade=10}";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }
}
