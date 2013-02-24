package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.AutorDTO;
import br.ufg.inf.es.web.datamodel.AutorDataModel;
import java.util.Arrays;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class AutorFormTest {

    private AutorForm form;
    private String filtroNome;
    private AutorDataModel model;
    private AutorDTO autorEdicao;
    private Long idAutorEdicao;
    private AutorDTO[] autoresSelecionados;
    private Boolean exibirDialogExclusao;
    private Collection<String> nomesSelecionados;
    private Collection<AutorDTO> todosAutores;

    @Before
    public void setUp() {
        filtroNome = "filtro";
        model = new AutorDataModel();
        autorEdicao = new AutorDTO();
        idAutorEdicao = 1L;
        autoresSelecionados = new AutorDTO[1];
        autoresSelecionados[0] = autorEdicao;
        exibirDialogExclusao = false;
        nomesSelecionados = Arrays.asList("nome");
        todosAutores = Arrays.asList(autorEdicao);

        form = new AutorForm();
        form.setAutorEdicao(autorEdicao);
        form.setAutoresSelecionados(autoresSelecionados);
        form.setExibirDialogExclusao(exibirDialogExclusao);
        form.setFiltroNome(filtroNome);
        form.setIdAutorEdicao(idAutorEdicao);
        form.setModel(model);
        form.setNomesSelecionados(nomesSelecionados);
        form.setTodosAutores(todosAutores);

    }

    /**
     * Test of getExibirDialogExclusao method, of class AutorForm.
     */
    @Test
    public void testGetExibirDialogExclusao() {
        Boolean result = form.getExibirDialogExclusao();
        assertEquals(exibirDialogExclusao, result);
    }

    /**
     * Test of getIdAutorEdicao method, of class AutorForm.
     */
    @Test
    public void testGetIdAutorEdicao() {
        Long result = form.getIdAutorEdicao();
        assertEquals(idAutorEdicao, result);
    }

    /**
     * Test of getAutorEdicao method, of class AutorForm.
     */
    @Test
    public void testGetAutorEdicao() {
        AutorDTO result = form.getAutorEdicao();
        assertEquals(autorEdicao, result);
    }

    /**
     * Test of getFiltroNome method, of class AutorForm.
     */
    @Test
    public void testGetFiltroNome() {
        String result = form.getFiltroNome();
        assertEquals(filtroNome, result);
    }

    /**
     * Test of getAutoresSelecionados method, of class AutorForm.
     */
    @Test
    public void testGetAutoresSelecionados() {
        AutorDTO[] result = form.getAutoresSelecionados();
        assertEquals(autoresSelecionados, result);
    }

    /**
     * Test of getModel method, of class AutorForm.
     */
    @Test
    public void testGetModel() {
        AutorDataModel result = form.getModel();
        assertEquals(model, result);
    }

    /**
     * Test of getTodosAutores method, of class AutorForm.
     */
    @Test
    public void testGetTodosAutores() {
        Collection result = form.getTodosAutores();
        assertEquals(todosAutores, result);
    }

    /**
     * Test of getNomesSelecionados method, of class AutorForm.
     */
    @Test
    public void testGetNomesSelecionados() {
        Collection result = form.getNomesSelecionados();
        assertEquals(nomesSelecionados, result);
    }
}