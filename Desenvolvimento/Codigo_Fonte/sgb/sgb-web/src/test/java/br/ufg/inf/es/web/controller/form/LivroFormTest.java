package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.enuns.EnumTipoBibliografia;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;
import br.ufg.inf.es.web.datamodel.LivroDataModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Alunoinf_2, victor
 */
public class LivroFormTest {

    private LivroForm form;
    private Bibliografia bibliografiaTemp = new Bibliografia();
    private Livro[] selectedLivros;
    private Livro livroSelecionado;
    private LivroDataModel livroDM;
    private Collection<Livro> todosLivros;
    private String filtroTitulo;
    private Curso cursoSelecionado;
    private Collection<Autor> autoresAdicionados = new ArrayList<Autor>();
    private Bibliografia bibliografiaRemocao = new Bibliografia();
    private LivroBiblioteca livroBiblioteca = new LivroBiblioteca();
    private LivroBiblioteca livroBibliotecaRemocao = new LivroBiblioteca();
    private Collection<LivroBiblioteca> livrosAssociados = new ArrayList<LivroBiblioteca>();

    @Before
    public void setUp() {
        selectedLivros = new Livro[0];
        livroSelecionado = new Livro();
        livroDM = new LivroDataModel();
        todosLivros = new ArrayList<Livro>();
        filtroTitulo = "filtro";
        cursoSelecionado = new Curso();

        form = new LivroForm();
        form.setBibliografiaTemp(bibliografiaTemp);
        form.setSelectedLivros(selectedLivros);
        form.setLivroSelecionado(livroSelecionado);
        form.setLivroDM(livroDM);
        form.setTodosLivros(todosLivros);
        form.setFiltroTitulo(filtroTitulo);
        form.setCursoSelecionado(cursoSelecionado);
        form.setAutoresAdicionados(autoresAdicionados);
        form.setBibliografiaRemocao(bibliografiaRemocao);
        form.setLivroBiblioteca(livroBiblioteca);
        form.setLivroBibliotecaRemocao(livroBibliotecaRemocao);
        form.setLivrosAssociados(livrosAssociados);
    }

    /**
     * Test of getSelectedLivros method, of class LivroForm.
     */
    @Test
    public void testGetSelectedLivros() {
        Livro[] result = form.getSelectedLivros();
        assertArrayEquals(selectedLivros, result);
    }

    /**
     * Test of getLivroSelecionado method, of class LivroForm.
     */
    @Test
    public void testGetLivroSelecionado() {
        Livro result = form.getLivroSelecionado();
        assertEquals(livroSelecionado, result);
    }

    /**
     * Test of getLivroDM method, of class LivroForm.
     */
    @Test
    public void testGetLivroDM() {
        LivroDataModel result = form.getLivroDM();
        assertTrue(result instanceof LivroDataModel);
    }

    /**
     * Test of getTodosLivros method, of class LivroForm.
     */
    @Test
    public void testGetTodosLivros() {
        Collection result = form.getTodosLivros();
        assertEquals(todosLivros, result);
    }

    /**
     * Test of getFiltroTitulo method, of class LivroForm.
     */
    @Test
    public void testGetFiltroTitulo() {
        String result = form.getFiltroTitulo();
        assertEquals(filtroTitulo, result);
    }

    /**
     * Test of getTiposBibliografia method, of class LivroForm.
     */
    @Test
    public void testGetTiposBibliografia() {
        List result = form.getTiposBibliografia();
        assertEquals(Arrays.asList(EnumTipoBibliografia.values()), result);
    }

    /**
     * Test of getTipoBibliografia method, of class LivroForm.
     */
    @Test
    public void testGetTipoBibliografia() {
        EnumTipoBibliografia result = form.getTipoBibliografia();
        assertEquals(bibliografiaTemp.getTipo(), result);
    }

    /**
     * Test of getCursoSelecionado method, of class LivroForm.
     */
    @Test
    public void testGetCursoSelecionado() {
        Curso result = form.getCursoSelecionado();
        assertEquals(cursoSelecionado, result);
    }

    /**
     * Test of getAutoresAdicionados method, of class LivroForm.
     */
    @Test
    public void testGetAutoresAdicionados() {
        Collection result = form.getAutoresAdicionados();
        assertEquals(autoresAdicionados, result);
    }

    /**
     * Test of getBibliografiaRemocao method, of class LivroForm.
     */
    @Test
    public void testGetBibliografiaRemocao() {
        Bibliografia result = form.getBibliografiaRemocao();
        assertEquals(bibliografiaRemocao, result);
    }

    /**
     * Test of getLivroBiblioteca method, of class LivroForm.
     */
    @Test
    public void testGetLivroBiblioteca() {
        LivroBiblioteca result = form.getLivroBiblioteca();
        assertEquals(livroBiblioteca, result);
    }

    /**
     * Test of getLivroBibliotecaRemocao method, of class LivroForm.
     */
    @Test
    public void testGetLivroBibliotecaRemocao() {
        LivroBiblioteca result = form.getLivroBibliotecaRemocao();
        assertEquals(livroBibliotecaRemocao, result);
    }

    /**
     * Test of getLivrosAssociados method, of class LivroForm.
     */
    @Test
    public void testGetLivrosAssociados() {
        Collection result = form.getLivrosAssociados();
        assertEquals(livrosAssociados, result);
    }

    /**
     * Test of getBibliografiaTemp method, of class LivroForm.
     */
    @Test
    public void testGetBibliografiaTemp() {
        Bibliografia result = form.getBibliografiaTemp();
        assertEquals(bibliografiaTemp, result);
    }
}