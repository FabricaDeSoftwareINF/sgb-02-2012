/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia;

import org.hibernate.SessionFactory;
import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 *
 * @author Alunoinf_2
 */
public class LivroDAOTest {
    
    public LivroDAOTest() {
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
     * Test of getSessionFactory method, of class LivroDAO.
     */
    @Test
    public void testGetSessionFactory() {
       
        LivroDAO instance = new LivroDAO();
        SessionFactory expResult = Mockito.mock(SessionFactory.class);
        instance.setSessionFactory(expResult);
        SessionFactory result = instance.getSessionFactory();
        assertEquals(expResult, result);
       
    }
}
