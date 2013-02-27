package br.ufg.inf.es.model;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * Testes do enum UsuarioPerfil
 *
 * @author Victor Carvalho
 */
public class UsuarioPerfilTest {

    /**
     * Test of getName method, of class UsuarioPerfil.
     */
    @Test
    public void testGetName() {
        String nomeEsperado = "ADM";
        assertEquals(nomeEsperado, UsuarioPerfil.ADM.getName());
    }

    /**
     * Test of getRoles method, of class UsuarioPerfil. For role: ADM
     */
    @Test
    public void testGetRolesADM() {
        UsuarioPerfil up = UsuarioPerfil.ADM;
        Collection<String> esperado = Arrays.asList(up.getName());
        valideResultado(esperado, up);
    }

    /**
     * Test of getRoles method, of class UsuarioPerfil. For role: DOCENTE
     */
    @Test
    public void testGetRolesDOCENTE() {
        UsuarioPerfil up = UsuarioPerfil.DOCENTE;
        Collection<String> esperado = Arrays.asList(up.getName());
        valideResultado(esperado, up);
    }

    /**
     * Test of getRoles method, of class UsuarioPerfil. For role: CONSELHEIRO
     */
    @Test
    public void testGetRolesCONSELHEIRO() {
        UsuarioPerfil up = UsuarioPerfil.CONSELHEIRO;
        Collection<String> esperado = Arrays.asList(up.getName());
        valideResultado(esperado, up);
    }

    /**
     * Test of getRoles method, of class UsuarioPerfil. For role: TECNICO
     */
    @Test
    public void testGetRolesTECNICO() {
        UsuarioPerfil up = UsuarioPerfil.TECNICO;
        Collection<String> esperado = Arrays.asList(up.getName());
        valideResultado(esperado, up);
    }

    /**
     * faz a validacao de acordo com o perfil
     *
     * @param esperado lista com as strings de perfis esperados
     * @param up UsurioPerfil para teste
     */
    private void valideResultado(Collection<String> esperado, UsuarioPerfil up) {
        Collection result = up.getRoles();
        assertEquals(esperado, result);
    }
}