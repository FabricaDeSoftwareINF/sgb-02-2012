package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Editora;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class EditoraFormTest {

    private EditoraForm form;
    private Editora editoraSelecionada;
    private Boolean exibirDialogExclusao;

    @Before
    public void setUp() {
        editoraSelecionada = new Editora();
        exibirDialogExclusao = true;

        form = new EditoraForm();
        form.setEditoraSelecionada(editoraSelecionada);
        form.setExibirDialogExclusao(exibirDialogExclusao);
    }

    /**
     * Test of getEditoraSelecionada method, of class EditoraForm.
     */
    @Test
    public void testGetEditoraSelecionada() {
        Editora result = form.getEditoraSelecionada();
        assertEquals(editoraSelecionada, result);
    }

    /**
     * Test of getExibirDialogExclusao method, of class EditoraForm.
     */
    @Test
    public void testGetExibirDialogExclusao() {
        Boolean result = form.getExibirDialogExclusao();
        assertEquals(exibirDialogExclusao, result);
    }
}
