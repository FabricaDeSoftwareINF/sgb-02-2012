package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.model.UsuarioPerfil;
import java.util.ArrayList;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import org.junit.*;
import static org.mockito.Mockito.*;

/**
 * Testes do servico de autenticacao
 *
 * @author Alunoinf_2, Victor Carvalho
 */
public class SgbAuthTest {

    private SgbAuth auth;
    private UsuarioService us;
    private String user = "user";
    private String password = "password";

    @Before
    public void setUp() {
        auth = new SgbAuth();
        us = mock(UsuarioService.class);

        auth.setUsuarioService(us);
    }

    /**
     * Test of login method, of class SgbAuth.
     */
    @Test
    public void testLogin() {
        Usuario usuario = new Usuario();
        usuario.setPerfil(UsuarioPerfil.ADM);
        when(us.authUser(user, password)).thenReturn(usuario);

        auth.setUsuarioService(us);
        Collection<String> expResult = new ArrayList();
        expResult.add(UsuarioPerfil.ADM.name());
        Collection result = auth.login(user, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsuarioService method, of class SgbAuth.
     */
    @Test
    public void testGetUsuarioService() {
        assertEquals(us, auth.getUsuarioService());
    }
}