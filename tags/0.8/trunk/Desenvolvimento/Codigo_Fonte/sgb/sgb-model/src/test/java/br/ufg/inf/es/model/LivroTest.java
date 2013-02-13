/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Alunoinf_2
 */
public class LivroTest {
    
    public LivroTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
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
     * Test of getTitulo method, of class Livro.
     */
    @Test
    public void testGetTitulo() {
       
        Livro instance = new Livro();
        instance.setTitulo("A");
        String expResult = "A";
        String result = instance.getTitulo();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of setTitulo method, of class Livro.
     */
    @Test
    public void testSetTitulo() {
        Livro instance = new Livro();
        instance.setTitulo("A");
        String titulo = "A";
      
        instance.setTitulo(titulo);
        assertEquals(titulo, instance.getTitulo());
    }

    /**
     * Test of getAno method, of class Livro.
     */
    @Test
    public void testGetAno() {
        
        Livro instance = new Livro();
        instance.setAno(2012L);
        Long expResult = 2012L;
        Long result = instance.getAno();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setAno method, of class Livro.
     */
    @Test
    public void testSetAno() {
        
        Long ano = 2012L;
        Livro instance = new Livro();
        instance.setAno(2012L);
        instance.setAno(ano);
        assertEquals(instance.getAno(), ano);
    }

    /**
     * Test of getIsbn10 method, of class Livro.
     */
    @Test
    public void testgetIsbn10() {
        
        Livro instance = new Livro();
        instance.setIsbn10("AB");
        String expResult = "AB";
        String result = instance.getIsbn10();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setIsbn10 method, of class Livro.
     */
    @Test
    public void testsetIsbn10() {
       
        String isbn10 = "AB";
        Livro instance = new Livro();
        instance.setIsbn10("AB");
        
        instance.setIsbn10(isbn10);
        assertEquals(instance.getIsbn10(), isbn10);
    }

    /**
     * Test of getIsbn13 method, of class Livro.
     */
    @Test
    public void testGetIsbn13() {
        
        Livro instance = new Livro();
        instance.setIsbn13("BA");
        String expResult = "BA";
        String result = instance.getIsbn13();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setIsbn13 method, of class Livro.
     */
    @Test
    public void testSetIsbn13() {
        Livro instance = new Livro();
        instance.setIsbn13("BA");
        String expResult = "BA";
        String result = instance.getIsbn13();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getEdicao method, of class Livro.
     */
    @Test
    public void testGetEdicao() {
        
        Livro instance = new Livro();
        String expResult = "5";
        instance.setEdicao(expResult);
        String result = instance.getEdicao();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setEdicao method, of class Livro.
     */
    @Test
    public void testSetEdicao() {
         Livro instance = new Livro();
        String expResult = "5";
        instance.setEdicao(expResult);
        String result = instance.getEdicao();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getEditora method, of class Livro.
     */
    @Test
    public void testGetEditora() {
       
        Livro instance = new Livro();
        Editora expResult = new Editora();
        expResult.setId(1L);
        expResult.setNome("A");
        instance.setEditora(expResult);
        Editora result = instance.getEditora();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setEditora method, of class Livro.
     */
    @Test
    public void testSetEditora() {
          Livro instance = new Livro();
        Editora expResult = new Editora();
        expResult.setId(1L);
        expResult.setNome("A");
        instance.setEditora(expResult);
        Editora result = instance.getEditora();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getAutores method, of class Livro.
     */
    @Test
    public void testGetAutores() {
      
        Livro instance = new Livro();
        Collection expResult = new ArrayList<Autor>();
        Autor a = new Autor();
        a.setId(Long.MIN_VALUE);
        a.setNome("B");
        a.setSobrenome("C");
        instance.setAutores(expResult);
        Collection result = instance.getAutores();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setAutores method, of class Livro.
     */
    @Test
    public void testSetAutores() {
       Livro instance = new Livro();
        Collection expResult = new ArrayList<Autor>();
        Autor a = new Autor();
        a.setId(Long.MIN_VALUE);
        a.setNome("B");
        a.setSobrenome("C");
        instance.setAutores(expResult);
        Collection result = instance.getAutores();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getBibliografia method, of class Livro.
     */
    @Test
    public void testGetBibliografia() {
        
        Livro instance = new Livro();
        Collection expResult = new ArrayList<Bibliografia>();
        Bibliografia b = new Bibliografia();
        b.setLivro(instance);
        expResult.add(b);
        instance.setBibliografias(expResult);
        Collection result = instance.getBibliografias();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setBibliografia method, of class Livro.
     */
    @Test
    public void testSetBibliografia() {
        
        Livro instance = new Livro();
        Collection expResult = new ArrayList<Bibliografia>();
        Bibliografia b = new Bibliografia();
        b.setLivro(instance);
        expResult.add(b);
        instance.setBibliografias(expResult);
        Collection result = instance.getBibliografias();
        assertEquals(expResult, result);
    }
}
