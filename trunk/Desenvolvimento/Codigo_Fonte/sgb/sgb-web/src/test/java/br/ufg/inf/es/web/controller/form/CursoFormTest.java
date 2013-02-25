package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.web.datamodel.CursoDataModel;
import java.util.Collection;
import java.util.LinkedList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class CursoFormTest {

    private CursoForm form;
    private Disciplina disciplinaSelecionada;
    private Disciplina disciplinaToRemove;
    private Collection<Disciplina> listaDisciplinaAssociacao;
    private Collection<Disciplina> listaDisciplinaComboBox;
    private boolean exibirDialog;
    private CursoDataModel cursoDataTableModel;
    private Curso[] cursosParaRemocao;
    private Curso cursoParaDetalhe;

    @Before
    public void setUp() {
        disciplinaSelecionada = new Disciplina();
        disciplinaToRemove = new Disciplina();
        listaDisciplinaAssociacao = new LinkedList<Disciplina>();
        listaDisciplinaComboBox = new LinkedList<Disciplina>();
        exibirDialog = false;
        cursoDataTableModel = new CursoDataModel();
        cursosParaRemocao = new Curso[0];
        cursoParaDetalhe = new Curso();

        form = new CursoForm();
        form.setDisciplinaSelecionada(disciplinaSelecionada);
        form.setDisciplinaToRemove(disciplinaToRemove);
        form.setListaDisciplinaAssociacao(listaDisciplinaAssociacao);
        form.setListaDisciplinaComboBox(listaDisciplinaComboBox);
        form.setExibirDialog(exibirDialog);
        form.setCursoDataTableModel(cursoDataTableModel);
        form.setCursosParaRemocao(cursosParaRemocao);
        form.setCursoParaDetalhe(cursoParaDetalhe);
    }

    /**
     * Test of getListaDisciplinaAssociacao method, of class CursoForm.
     */
    @Test
    public void testGetListaDisciplinaAssociacao() {
        Collection result = form.getListaDisciplinaAssociacao();
        assertEquals(listaDisciplinaAssociacao, result);
    }

    /**
     * Test of getDisciplinaSelecionada method, of class CursoForm.
     */
    @Test
    public void testGetDisciplinaSelecionada() {
        Disciplina result = form.getDisciplinaSelecionada();
        assertEquals(disciplinaSelecionada, result);
    }

    /**
     * Test of getDisciplinaToRemove method, of class CursoForm.
     */
    @Test
    public void testGetDisciplinaToRemove() {
        Disciplina result = form.getDisciplinaToRemove();
        assertEquals(disciplinaToRemove, result);
    }

    /**
     * Test of getListaDisciplinaComboBox method, of class CursoForm.
     */
    @Test
    public void testGetListaDisciplinaComboBox() {
        Collection result = form.getListaDisciplinaComboBox();
        assertEquals(listaDisciplinaComboBox, result);
    }

    /**
     * Test of isExibirDialog method, of class CursoForm.
     */
    @Test
    public void testIsExibirDialog() {
        boolean result = form.isExibirDialog();
        assertEquals(exibirDialog, result);
    }

    /**
     * Test of getCursoDataTableModel method, of class CursoForm.
     */
    @Test
    public void testGetCursoDataTableModel() {
        CursoDataModel result = form.getCursoDataTableModel();
        assertTrue(result instanceof CursoDataModel);
    }

    /**
     * Test of getCursosParaRemocao method, of class CursoForm.
     */
    @Test
    public void testGetCursosParaRemocao() {
        Curso[] result = form.getCursosParaRemocao();
        assertArrayEquals(cursosParaRemocao, result);
    }

    /**
     * Test of getCursoParaDetalhe method, of class CursoForm.
     */
    @Test
    public void testGetCursoParaDetalhe() {
        Curso result = form.getCursoParaDetalhe();
        assertEquals(cursoParaDetalhe, result);
    }
}
