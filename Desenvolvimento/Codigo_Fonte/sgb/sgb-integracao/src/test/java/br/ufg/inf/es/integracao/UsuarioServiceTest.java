package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.persistencia.UsuarioDAO;
import java.util.ArrayList;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.powermock.api.mockito.PowerMockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Testes do servico de usuarios
 *
 * @author alunoufg, Victor Carvalho
 */
@RunWith(PowerMockRunner.class)
public class UsuarioServiceTest {

    private Usuario usuario;
    private UsuarioService usuarioService;
    private UsuarioDAO usuarioDAO;

    @Before
    public void setUp() {
        usuario = new Usuario();
        usuarioService = new UsuarioService();

        usuario.setSenha("123456");
        usuario.setId(Long.MIN_VALUE);

        usuarioDAO = mock(UsuarioDAO.class);
        usuarioService.setDao(usuarioDAO);
    }

    /**
     * Test of getDAO method, of class UsuarioService.
     */
    @Test
    public void testGetDAO() {
        assertEquals(usuarioDAO, usuarioService.getDAO());
    }

    /**
     * Test of setDao method, of class UsuarioService.
     */
    @Test
    public void testSetDao() {
        UsuarioDAO dao = new UsuarioDAO();
        usuarioService.setDao(dao);
        assertEquals(dao, usuarioService.getDAO());
    }

    /**
     * Test of insert method, of class UsuarioService.
     */
    @Test
    public void testInsert() throws Exception {
        Usuario entity = new Usuario();
        entity.setSenha("123456");

        when(usuarioDAO.insert(entity)).thenReturn(0L);

        Long expResult = new Long(0);

        Long result = usuarioService.insert(entity);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class UsuarioService.
     */
    @Test
    public void testUpdate() throws Exception {
        usuarioService.update(usuario);
    }

    /**
     * Test of remove method, of class UsuarioService.
     */
    @Test
    public void testRemove() throws Exception {
        usuarioService.remove(usuario);
    }

    /**
     * Test of removeAll method, of class UsuarioService.
     */
    @Test
    public void testRemoveAll() throws Exception {
        Collection<Usuario> collectionEntities = new ArrayList<Usuario>();
        usuarioService.removeAll(collectionEntities);
    }

    /**
     * Test of search method, of class UsuarioService.
     */
    @Test
    public void testSearch() {
        Collection expResult = new ArrayList<Usuario>();
        Collection result = usuarioService.search(usuario);
        assertEquals(expResult, result);
    }

    @Test
    public void testList() {
        Collection expResult = new ArrayList<Usuario>();
        Collection result = usuarioService.list();
        assertEquals(expResult, result);
    }

    /**
     * Test of refresh method, of class UsuarioService.
     */
    @Test
    public void testRefresh() {
        usuarioService.refresh(usuario);
    }

    /**
     * Test of authUser method, of class UsuarioService.
     */
    @Test
    public void testAuthUser() {
        String user = "a";
        String password = "123";

        Usuario expResult = new Usuario();
        expResult.setNome(user);
        expResult.setSenha(password);

        when(usuarioDAO.findUserByEmailAndPassword(user, password)).thenReturn(expResult);

        Usuario result = usuarioService.authUser(user, password);

        assertEquals(expResult, result);
    }

    /**
     * Test of save method, of class UsuarioService.
     */
    @Test
    public void testSave() throws Exception {
        usuarioService.save(usuario);
    }
}