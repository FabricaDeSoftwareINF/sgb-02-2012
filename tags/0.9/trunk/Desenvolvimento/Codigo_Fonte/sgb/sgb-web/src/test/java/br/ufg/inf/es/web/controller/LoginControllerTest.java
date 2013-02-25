package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.service.Auth;
import br.ufg.inf.es.base.util.SgbCryptography;
import br.ufg.inf.es.integracao.SgbAuth;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.powermock.api.mockito.PowerMockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import sun.security.acl.PrincipalImpl;

/**
 *
 * Testes para o controlador da tela de login
 *
 * @author Victor
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(FacesContext.class)
public class LoginControllerTest {

    private Auth auth;
    private LoginController controller;
    private String user = "user";
    private String password = "password";
    private String passwordEncrypted = new SgbCryptography().encrypt(password);

    @Before
    public void setUp() {
        controller = new LoginController();
        auth = mock(SgbAuth.class);

        controller.setAuth(auth);
    }

    /**
     * Test of getAuth method, of class LoginController.
     */
    @Test
    public void testGetAuth() {
        assertEquals(auth, controller.getAuth());
    }

    /**
     * Test of setAuth method, of class LoginController.
     */
    @Test
    public void testSetAuth() {
        Auth authNovo = new SgbAuth();
        controller.setAuth(authNovo);
        assertEquals(authNovo, controller.getAuth());
    }

    /**
     * Test of authenticate method, of class LoginController.
     */
    @Test(expected = BadCredentialsException.class)
    public void testAuthenticateParaDadosInvalidos() {
        Authentication a = new UsernamePasswordAuthenticationToken(user, password);

        when(auth.login(user, passwordEncrypted)).thenReturn(null);

        controller.authenticate(a);
    }

    /**
     * Test of authenticate method, of class LoginController.
     */
    @Test
    public void testAuthenticateParaUsuarioESenhaCorretos() {
        String role = "ROLE_PROFESSOR";
        Authentication a = new UsernamePasswordAuthenticationToken(user, password);

        when(auth.login(user, passwordEncrypted)).thenReturn(Arrays.asList(role));

        Authentication response = controller.authenticate(a);

        Collection<GrantedAuthority> ga = new ArrayList<GrantedAuthority>();
        ga.add(new GrantedAuthorityImpl(role));
        Authentication expResult = new UsernamePasswordAuthenticationToken(user, password, ga);

        assertEquals(expResult, response);
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

    /**
     * Test of supports method, of class LoginController.
     */
    @Test
    public void testSupportsTrue() {
        Class<UsernamePasswordAuthenticationToken> type = UsernamePasswordAuthenticationToken.class;
        LoginController instance = new LoginController();
        boolean expResult = true;
        boolean result = instance.supports(type);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsuarioLogado method, of class LoginController.
     */
    @Test
    public void testGetUsuarioLogado() {
        Principal principal = new PrincipalImpl(user);

        FacesContext fc = mock(FacesContext.class);
        ExternalContext ec = mock(ExternalContext.class);
        HttpServletRequest hsr = mock(HttpServletRequest.class);

        when(fc.getExternalContext()).thenReturn(ec);
        when(ec.getRequest()).thenReturn(hsr);
        when(hsr.getUserPrincipal()).thenReturn(principal);

        mockStatic(FacesContext.class);
        Mockito.when(FacesContext.getCurrentInstance()).thenReturn(fc);

        String nomeUserLogado = controller.getUsuarioLogado();
        assertEquals(user, nomeUserLogado);
    }
}