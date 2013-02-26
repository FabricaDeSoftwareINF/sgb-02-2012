package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.model.UsuarioPerfil;
import br.ufg.inf.es.web.datamodel.UsuarioDataModel;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class UsuarioFormTest {

    private UsuarioForm form;
    private Collection<Usuario> tabelaUsuarios = new ArrayList<Usuario>();
    private Collection<UsuarioPerfil> perfis = new ArrayList<UsuarioPerfil>();
    private UsuarioDataModel userDataModel = new UsuarioDataModel();
    private Usuario[] usuariosSelecionados = new Usuario[0];
    private Boolean exibirDialogExclusao = true;

    @Before
    public void setUp() {
        form = new UsuarioForm();

        form.setTabelaUsuarios(tabelaUsuarios);
        form.setPerfis(perfis);
        form.setUserDataModel(userDataModel);
        form.setUsuariosSelecionados(usuariosSelecionados);
        form.setExibirDialogExclusao(exibirDialogExclusao);
    }

    /**
     * Test of getExibirDialogExclusao method, of class UsuarioForm.
     */
    @Test
    public void testGetExibirDialogExclusao() {
        Boolean result = form.getExibirDialogExclusao();
        assertEquals(exibirDialogExclusao, result);
    }

    /**
     * Test of getTabelaUsuarios method, of class UsuarioForm.
     */
    @Test
    public void testGetTabelaUsuarios() {
        Collection result = form.getTabelaUsuarios();
        assertEquals(tabelaUsuarios, result);
    }

    /**
     * Test of getPerfis method, of class UsuarioForm.
     */
    @Test
    public void testGetPerfis() {
        Collection result = form.getPerfis();
        assertEquals(perfis, result);
    }

    /**
     * Test of getUserDataModel method, of class UsuarioForm.
     */
    @Test
    public void testGetUserDataModel() {
        UsuarioDataModel result = form.getUserDataModel();
        assertTrue(result instanceof UsuarioDataModel);
    }

    /**
     * Test of getUsuariosSelecionados method, of class UsuarioForm.
     */
    @Test
    public void testGetUsuariosSelecionados() {
        Usuario[] result = form.getUsuariosSelecionados();
        assertArrayEquals(usuariosSelecionados, result);
    }
}
