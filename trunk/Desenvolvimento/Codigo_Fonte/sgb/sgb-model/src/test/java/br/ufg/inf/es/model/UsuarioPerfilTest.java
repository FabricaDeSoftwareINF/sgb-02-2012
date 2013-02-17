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
     * Test of getRoles method, of class UsuarioPerfil. For role:
     * DOCENTE_CONSELHEIRO
     */
    @Test
    public void testGetRolesDOCENTE_CONSELHEIRO() {
        UsuarioPerfil up = UsuarioPerfil.DOCENTE_CONSELHEIRO;
        Collection<String> esperado = Arrays.asList(UsuarioPerfil.DOCENTE.getName(),
                UsuarioPerfil.CONSELHEIRO.getName());
        valideResultado(esperado, up);
    }

    /**
     * Test of getRoles method, of class UsuarioPerfil. For role: DOCENTE_ADM
     */
    @Test
    public void testGetRolesDOCENTE_ADM() {
        UsuarioPerfil up = UsuarioPerfil.DOCENTE_ADM;
        Collection<String> esperado = Arrays.asList(UsuarioPerfil.DOCENTE.getName(),
                UsuarioPerfil.ADM.getName());
        valideResultado(esperado, up);
    }

    /**
     * Test of getRoles method, of class UsuarioPerfil. For role:
     * CONSELHEIRO_ADM
     */
    @Test
    public void testGetRolesCONSELHEIRO_ADM() {
        UsuarioPerfil up = UsuarioPerfil.CONSELHEIRO_ADM;
        Collection<String> esperado = Arrays.asList(UsuarioPerfil.CONSELHEIRO.getName(),
                UsuarioPerfil.ADM.getName());
        valideResultado(esperado, up);
    }

    /**
     * Test of getRoles method, of class UsuarioPerfil. For role: TECNICO_ADM
     */
    @Test
    public void testGetRolesTECNICO_ADM() {
        UsuarioPerfil up = UsuarioPerfil.TECNICO_ADM;
        Collection<String> esperado = Arrays.asList(UsuarioPerfil.TECNICO.getName(),
                UsuarioPerfil.ADM.getName());
        valideResultado(esperado, up);
    }

    /**
     * Test of getRoles method, of class UsuarioPerfil. For role:
     * DOCENTE_CONSELHEIRO_ADM
     */
    @Test
    public void testGetRolesDOCENTE_CONSELHEIRO_ADM() {
        UsuarioPerfil up = UsuarioPerfil.DOCENTE_CONSELHEIRO_ADM;
        Collection<String> esperado = Arrays.asList(UsuarioPerfil.DOCENTE.getName(),
                UsuarioPerfil.CONSELHEIRO.getName(), UsuarioPerfil.ADM.getName());
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