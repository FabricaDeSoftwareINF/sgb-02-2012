package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Livro;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class TemplateFormTest {

    private TemplateForm form;
    private Livro livroSelecionado;

    @Before
    public void setUp() {
        livroSelecionado = new Livro();
        form = new TemplateForm();
        form.setLivroSelecionado(livroSelecionado);
    }

    /**
     * Test of getLivroSelecionado method, of class TemplateForm.
     */
    @Test
    public void testGetLivroSelecionado() {
        Livro result = form.getLivroSelecionado();
        assertEquals(livroSelecionado, result);
    }
}
