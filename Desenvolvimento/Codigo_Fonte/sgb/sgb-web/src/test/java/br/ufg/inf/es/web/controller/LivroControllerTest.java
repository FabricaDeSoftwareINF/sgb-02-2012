/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.*;
import br.ufg.inf.es.integracao.exportacaodados.MarcParser;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.controller.form.LivroForm;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Alunoinf_2
 */
public class LivroControllerTest {

    public LivroControllerTest() {
    }
    
    LivroController livroController = new LivroController();
    Curso cursoMock = Mockito.mock(Curso.class);
    LivroForm livroFormMock = Mockito.mock(LivroForm.class);
    LivroService livroServiceMock = Mockito.mock(LivroService.class);
    DisciplinaService disciplinaServiceMock = Mockito.mock(DisciplinaService.class);
    CursoService cursoServiceMock = Mockito.mock(CursoService.class);
    Collection<Curso> cursosMock = Mockito.mock(ArrayList.class);
    Collection<Livro> livrosMock = Mockito.mock(ArrayList.class);
    EditoraService editoraServiceMock = Mockito.mock(EditoraService.class);
    AutorService autorServiceMock = Mockito.mock(AutorService.class);

    @Before
    public void setUp() {
        livroController.setForm(livroFormMock);
        livroController.setService(livroServiceMock);
        livroController.setDisciplinaService(disciplinaServiceMock);
        livroController.setCursoService(cursoServiceMock);
        cursosMock.add(cursoMock);
        livroController.setCursos(cursosMock);
        livroController.setEditoraService(editoraServiceMock);
        livroController.setAutorService(autorServiceMock);
        Mockito.when(livroFormMock.getEntity()).thenReturn(new Livro());
    }

    /**
     * Test of setCursoSelecionado method, of class LivroController.
     */
    @Test
    public void testSetCursoSelecionado() {
        livroController.getForm().setCursoSelecionado(cursoMock);
    }

    @Test
    public void testGetForm() {

        
        LivroForm result = livroController.getForm();
        assertEquals(livroFormMock, result);

    }

    /**
     * Test of setForm method, of class LivroController.
     */
    @Test
    public void testSetForm() {
        livroController.setForm(livroFormMock);

    }

    /**
     * Test of getService method, of class LivroController.
     */
    @Test
    public void testGetService() {
        LivroService result = livroController.getService();
        assertEquals(livroServiceMock, result);
    }

    /**
     * Test of setService method, of class LivroController.
     */
    @Test
    public void testSetService() {
        livroController.setService(livroServiceMock);

    }

    /**
     * Test of limparDisciplina method, of class LivroController.
     */
    @Test
    public void testLimparDisciplina() {
        Mockito.when(livroFormMock.getBibliografiaTemp()).thenReturn(new Bibliografia());
        livroController.limparDisciplina();
        Mockito.verify(livroFormMock).getBibliografiaTemp();
    }

    /**
     * Test of getDisciplinaService method, of class LivroController.
     */
    @Test
    public void testGetDisciplinaService() {
        livroController.setDisciplinaService(disciplinaServiceMock);
        DisciplinaService result = livroController.getDisciplinaService();
        assertEquals(disciplinaServiceMock, result);

    }

    /**
     * Test of setDisciplinaService method, of class LivroController.
     */
    @Test
    public void testSetDisciplinaService() {

        livroController.setDisciplinaService(disciplinaServiceMock);
      
    }

    /**
     * Test of getCursoService method, of class LivroController.
     */
    @Test
    public void testGetCursoService() {
        CursoService result = livroController.getCursoService();
        assertEquals(cursoServiceMock, result);

    }

    /**
     * Test of setCursoService method, of class LivroController.
     */
    @Test
    public void testSetCursoService() {
        livroController.setCursoService(cursoServiceMock);
        
    }

    /**
     * Test of getCursos method, of class LivroController.
     */
    @Test
    public void testGetCursos() {


        Collection result = livroController.getCursos();
        assertEquals(cursosMock, result);

    }

    /**
     * Test of setCursos method, of class LivroController.
     */
    @Test
    public void testSetCursos() {
        livroController.setCursos(cursosMock);
       
    }

    /**
     * Test of getEditoraService method, of class LivroController.
     */
    @Test
    public void testGetEditoraService() {


        EditoraService result = livroController.getEditoraService();
        assertEquals(editoraServiceMock, result);

    }

    /**
     * Test of setEditoraService method, of class LivroController.
     */
    @Test
    public void testSetEditoraService() {

        livroController.setEditoraService(editoraServiceMock);
      
    }

    /**
     * Test of getAutorService method, of class LivroController.
     */
    @Test
    public void testGetAutorService() {


        AutorService result = livroController.getAutorService();
        assertEquals(autorServiceMock, result);

    }

    /**
     * Test of setAutorService method, of class LivroController.
     */
    @Test
    public void testSetAutorService() {


        livroController.setAutorService(autorServiceMock);
      

    }

    /**
     * Test of getFormatoSelecionado method, of class LivroController.
     */
    @Test
    public void testGetFormatoSelecionado() {

        String expResult = "A";
        livroController.setFormatoSelecionado("A");
        String result = livroController.getFormatoSelecionado();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFormatoSelecionado method, of class LivroController.
     */
    @Test
    public void testSetFormatoSelecionado() {

        String formatoSelecionado = "A";
        livroController.setFormatoSelecionado(formatoSelecionado);
       
    }

    /**
     * Test of associarDisciplina method, of class LivroController.
     */
    @Test
    @Ignore("Método com erro. Quando for corrigido, o teste deverá funcionar.")
    public void testAssociarDisciplina() {
        // this.form.getEntity().getBibliografia().add(this.form.getBibliografiaTemp());
        Livro livroMock = Mockito.mock(Livro.class);
        Mockito.when(livroFormMock.getEntity()).thenReturn(livroMock);
        Mockito.when(livroMock.getBibliografias()).thenReturn(new ArrayList<Bibliografia>());
        livroController.associarDisciplina();
        Mockito.verify(livroFormMock).getEntity();
        Mockito.verify(livroMock).getBibliografias();
    }

    /**
     * Test of complete method, of class LivroController.
     */
    @Test
    public void testComplete() {

        String query = "A";
        Livro livro = new Livro();
        livro.setTitulo("A");
        Collection<Livro> livros = new ArrayList();
        
        livros.add(livro);
        Mockito.when(livroServiceMock.searchByAttributes(query, "titulo")).thenReturn(livros);
        Collection result = livroController.complete(query);
        
        assertEquals(livros, result);
        
    }

    /**
     * Test of openInsertPage method, of class LivroController.
     */
    @Test
    public void testOpenInsertPage() {
       
     
        String expResult = "/paginas/livro/inclusao.xhtml";
        String result = livroController.openInsertPage();
        Mockito.verify(cursoServiceMock).list();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of salvarLivro method, of class LivroController.
     */
    @Test
    @Ignore("Causa um erro: java.lang.ClassFormatError: Absent Code attribute in method that is not native or abstract in class file javax/faces/application/FacesMessage")
    public void testSalvarLivro() throws Exception {
      
        String expResult = "";
      
        String result = livroController.salvarLivro();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of voltar method, of class LivroController.
     */
    @Test
    public void testVoltar() {
        
        String expResult = "/index.jsf";
        String result = livroController.voltar();
        assertEquals(expResult, result);
      
    }

    
    /**
     * Test of getFile method, of class LivroController.
     */
    @Test
    public void testGetFile() {
      
        StreamedContent sc =  Mockito.mock(StreamedContent.class);
        livroController.setFileExportado(sc);
        StreamedContent result = livroController.getFile();
        assertEquals(sc, result);
       
    }
}
