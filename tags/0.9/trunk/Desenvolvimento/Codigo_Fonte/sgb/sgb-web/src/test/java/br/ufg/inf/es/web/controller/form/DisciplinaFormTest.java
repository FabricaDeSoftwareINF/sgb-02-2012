package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.enuns.EnumTipoBibliografia;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.datamodel.DisciplinaDataModel;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class DisciplinaFormTest {

    private DisciplinaForm form;
    private Collection<Disciplina> disciplinasSelecionadas;
    private DisciplinaDataModel dataModelDisciplina;
    private Boolean exibirDialogDetalhe;
    private Disciplina disciplinaDetalhe;
    private Disciplina disciplinaEdicao;
    private Boolean isEditPage;
    private Bibliografia bibliografiaParaRemocao;
    private Curso cursoSelecionado;
    private Collection<Curso> cursos;
    private Collection<EnumTipoBibliografia> tipoBibliografias;
    private EnumTipoBibliografia tipoBibliografiaSelecionado;
    private Livro livroSelecionado;
    private Collection<Livro> selecionadosAux = new ArrayList<Livro>();
    private Bibliografia bibliografiaTmp = new Bibliografia();

    @Before
    public void setUp() {
        definaAtributos();

        form.setDisciplinasSelecionadas(disciplinasSelecionadas);
        form.setBibliografiaParaRemocao(bibliografiaParaRemocao);
        form.setBibliografiaTmp(bibliografiaTmp);
        form.setCursoSelecionado(cursoSelecionado);
        form.setCursos(cursos);
        form.setDataModelDisciplina(dataModelDisciplina);
        form.setDisciplinaDetalhe(disciplinaDetalhe);
        form.setDisciplinaEdicao(disciplinaEdicao);
        form.setDisciplinaDetalhe(disciplinaDetalhe);
        form.setDisciplinasSelecionadas(disciplinasSelecionadas);
        form.setExibirDialogDetalhe(exibirDialogDetalhe);
        form.setIsEditPage(isEditPage);
        form.setLivroSelecionado(livroSelecionado);
        form.setSelecionadosAux(selecionadosAux);
        form.setTipoBibliografiaSelecionado(tipoBibliografiaSelecionado);
        form.setTipoBibliografias(tipoBibliografias);
    }

    private void definaAtributos() {
        disciplinasSelecionadas = new ArrayList<Disciplina>();
        dataModelDisciplina = new DisciplinaDataModel();
        exibirDialogDetalhe = true;
        disciplinaDetalhe = new Disciplina();
        disciplinaEdicao = new Disciplina();
        isEditPage = true;
        bibliografiaParaRemocao = new Bibliografia();
        cursoSelecionado = new Curso();
        cursos = new ArrayList<Curso>();
        tipoBibliografias = new ArrayList<EnumTipoBibliografia>();
        tipoBibliografiaSelecionado = EnumTipoBibliografia.BASICA;
        livroSelecionado = new Livro();

        form = new DisciplinaForm();
    }

    /**
     * Test of getDisciplinasSelecionadas method, of class DisciplinaForm.
     */
    @Test
    public void testGetDisciplinasSelecionadas() {
        Collection result = form.getDisciplinasSelecionadas();
        assertEquals(disciplinasSelecionadas, result);
    }

    /**
     * Test of getDataModelDisciplina method, of class DisciplinaForm.
     */
    @Test
    public void testGetDataModelDisciplina() {
        DisciplinaDataModel result = form.getDataModelDisciplina();
        assertEquals(dataModelDisciplina, result);
    }

    /**
     * Test of getExibirDialogDetalhe method, of class DisciplinaForm.
     */
    @Test
    public void testGetExibirDialogDetalhe() {
        Boolean result = form.getExibirDialogDetalhe();
        assertEquals(exibirDialogDetalhe, result);
    }

    /**
     * Test of getDisciplinaDetalhe method, of class DisciplinaForm.
     */
    @Test
    public void testGetDisciplinaDetalhe() {
        Disciplina result = form.getDisciplinaDetalhe();
        assertEquals(disciplinaDetalhe, result);
    }

    /**
     * Test of getIsEditPage method, of class DisciplinaForm.
     */
    @Test
    public void testGetIsEditPage() {
        Boolean result = form.getIsEditPage();
        assertEquals(isEditPage, result);
    }

    /**
     * Test of getDisciplinaEdicao method, of class DisciplinaForm.
     */
    @Test
    public void testGetDisciplinaEdicao() {
        Disciplina result = form.getDisciplinaEdicao();
        assertEquals(disciplinaEdicao, result);
    }

    /**
     * Test of getBibliografiaParaRemocao method, of class DisciplinaForm.
     */
    @Test
    public void testGetBibliografiaParaRemocao() {
        Bibliografia result = form.getBibliografiaParaRemocao();
        assertEquals(bibliografiaParaRemocao, result);
    }

    /**
     * Test of getSelecionadosAux method, of class DisciplinaForm.
     */
    @Test
    public void testGetSelecionadosAux() {
        Collection result = form.getSelecionadosAux();
        assertEquals(selecionadosAux, result);
    }

    /**
     * Test of getLivroSelecionado method, of class DisciplinaForm.
     */
    @Test
    public void testGetLivroSelecionado() {
        Livro result = form.getLivroSelecionado();
        assertEquals(livroSelecionado, result);
    }

    /**
     * Test of getTipoBibliografiaSelecionado method, of class DisciplinaForm.
     */
    @Test
    public void testGetTipoBibliografiaSelecionado() {
        EnumTipoBibliografia result = form.getTipoBibliografiaSelecionado();
        assertEquals(tipoBibliografiaSelecionado, result);
    }

    /**
     * Test of getTipoBibliografias method, of class DisciplinaForm.
     */
    @Test
    public void testGetTipoBibliografias() {
        Collection result = form.getTipoBibliografias();
        assertEquals(tipoBibliografias, result);
    }

    /**
     * Test of getCursoSelecionado method, of class DisciplinaForm.
     */
    @Test
    public void testGetCursoSelecionado() {
        Curso result = form.getCursoSelecionado();
        assertEquals(cursoSelecionado, result);
    }

    /**
     * Test of getCursos method, of class DisciplinaForm.
     */
    @Test
    public void testGetCursos() {
        Collection result = form.getCursos();
        assertEquals(cursos, result);
    }

    /**
     * Test of getBibliografiaTmp method, of class DisciplinaForm.
     */
    @Test
    public void testGetBibliografiaTmp() {
        Bibliografia result = form.getBibliografiaTmp();
        assertEquals(bibliografiaTmp, result);
    }
}
