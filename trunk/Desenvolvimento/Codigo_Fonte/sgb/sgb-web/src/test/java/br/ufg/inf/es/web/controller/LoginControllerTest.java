/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.service.Auth;
import br.ufg.inf.es.base.util.SgbCryptography;
import br.ufg.inf.es.integracao.AuthMock;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

/**
 *
 * @author Alunoinf_2
 */
public class LoginControllerTest {
    
    public LoginControllerTest() {
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
     * Test of getAuth method, of class LoginController.
     */
    @Test
    public void testGetAuth() {
        
        LoginController instance = new LoginController();
        Auth expResult = new AuthMock();
        instance.setAuth(expResult);
        Auth result = instance.getAuth();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setAuth method, of class LoginController.
     */
    @Test
    public void testSetAuth() {
     
        Auth auth = new AuthMock();
        LoginController instance = new LoginController();
        instance.setAuth(auth);
        assertNotNull(auth);
    }

    /**
     * Test of authenticate method, of class LoginController.
     */
    @Test(expected=BadCredentialsException.class)
    
    public void testAuthenticate() {
        
        String username = "professor";
        String pass  = "454";
        Authentication a = new UsernamePasswordAuthenticationToken(username,pass);
        LoginController instance = new LoginController();
        instance.setAuth(new AuthMock());
        
        Collection<GrantedAuthority> ga = new ArrayList<GrantedAuthority>();
        ga.add(new  GrantedAuthorityImpl("ROLE_PROFESSOR")); 
        Authentication expResult = new UsernamePasswordAuthenticationToken(username, pass,ga);
        
        Authentication result = instance.authenticate(a);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of supports method, of class LoginController.
     */
    @Test
    public void testSupportsFalse() {
        Class<?> type = String.class;
        LoginController instance = new LoginController();
        boolean expResult = false;
        boolean result = instance.supports(type);
        assertEquals(expResult, result);
    }
     @Test
    public void testSupportsTrue() {
        Class<UsernamePasswordAuthenticationToken> type = UsernamePasswordAuthenticationToken.class;
        LoginController instance = new LoginController();
        boolean expResult = true;
        boolean result = instance.supports(type);
        assertEquals(expResult, result);
    }
}
