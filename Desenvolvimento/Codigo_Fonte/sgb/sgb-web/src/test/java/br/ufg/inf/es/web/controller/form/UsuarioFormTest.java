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
    private Collection<UsuarioPerfil> perfis = new ArrayList<UsuarioPerfil>();
    private UsuarioDataModel userDataModel = new UsuarioDataModel();
    private Collection<Usuario> usuariosSelecionados = new ArrayList<Usuario>();

    @Before
    public void setUp() {
        form = new UsuarioForm();
        
        form.setPerfis(perfis);
        form.setUserDataModel(userDataModel);
        form.setUsuariosSelecionados(usuariosSelecionados);
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

}
