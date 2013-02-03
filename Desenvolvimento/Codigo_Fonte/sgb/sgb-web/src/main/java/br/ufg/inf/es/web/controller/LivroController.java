package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.AutorService;
import br.ufg.inf.es.integracao.CursoService;
import br.ufg.inf.es.integracao.DisciplinaService;
import br.ufg.inf.es.integracao.EditoraService;
import br.ufg.inf.es.integracao.LivroService;
import br.ufg.inf.es.integracao.exportacaodados.MarcParser;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.Editora;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.controller.form.LivroForm;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import br.ufg.inf.es.web.datamodel.LivroDataModel;

/**
 *
 * @author cezar
 */
@Component
@Scope("session")
public class LivroController extends SGBController<Livro, LivroForm, LivroService> {

    @Autowired
    private LivroForm form;
    @Autowired
    private LivroService service;
    @Autowired
    private DisciplinaService disciplinaService;
    @Autowired
    private CursoService cursoService;
    @Autowired
    private EditoraService editoraService;
    @Autowired
    private AutorService autorService;
    
    private Autor autor;
    private Editora editora;
    private String tipoBibliografia;
    private Curso cursoSelecionado;
    private String formatoSelecionado;
    private StreamedContent fileExportado;
    
    private LazyDataModel<Livro> lazyModel;

    /**
     * Método responsável por retornar a string de navegação para a pagina
     * incial da Estória de usuário buscar todos os livros.
     *
     * @return string de navegação
     */
    @Override
    public String openInitialPage() {
        lazyModel = new LivroDataModel(service);
        this.autor = new Autor();
        this.editora = new Editora();

        return super.openInitialPage();
    }
    
    public LazyDataModel<Livro> getLivros() {
        return lazyModel;
    }

    public Curso getCursoSelecionado() {
        return cursoSelecionado;
    }

    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }
    private Collection<Curso> cursos;

    @Override
    public LivroForm getForm() {
        return form;
    }

    public void setForm(LivroForm form) {
        this.form = form;
    }

    public void setFileExportado(StreamedContent fileExportado) {
        this.fileExportado = fileExportado;
    }

    @Override
    public LivroService getService() {
        return service;
    }

    public void setService(LivroService service) {
        this.service = service;
    }

    public void limparDisciplina() {
        this.form.getBibliografiaTemp().setDisciplina(new Disciplina());
    }

    public DisciplinaService getDisciplinaService() {
        return disciplinaService;
    }

    public void setDisciplinaService(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    public CursoService getCursoService() {
        return cursoService;
    }

    public void setCursoService(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    public String getTipoBibliografia() {
        return tipoBibliografia;
    }

    public void setTipoBibliografia(String tipoBibliografia) {
        this.tipoBibliografia = tipoBibliografia;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Collection<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Collection<Curso> cursos) {
        this.cursos = cursos;
    }

    public EditoraService getEditoraService() {
        return editoraService;
    }

    public void setEditoraService(EditoraService editoraService) {
        this.editoraService = editoraService;
    }

    public AutorService getAutorService() {
        return autorService;
    }

    public void setAutorService(AutorService autorService) {
        this.autorService = autorService;
    }

    public String getFormatoSelecionado() {
        return formatoSelecionado;
    }

    public void setFormatoSelecionado(String formatoSelecionado) {
        this.formatoSelecionado = formatoSelecionado;
    }

    public void associarDisciplina() {
        Livro livro = this.form.getEntity();
        Bibliografia bibliografia = this.form.getBibliografiaTemp();
        bibliografia.setTipo(tipoBibliografia);
        bibliografia.setLivro(livro);
        Collection<Bibliografia> listaBibliografias = livro.getBibliografias();
        listaBibliografias.add(bibliografia);
    }

    public Collection<Livro> complete(String query) {
        Collection<Livro> results = new ArrayList<Livro>();

        for (Livro autor : form.getCollectionEntities()) {
            if (autor.getTitulo().contains(query)) {
                results.add(autor);
            }
        }

        return results;
    }

    @Override
    public String openInsertPage() {

        cursos = cursoService.list();

        this.openInsertView();
        return "/paginas/livro/inclusao.xhtml";
    }

    public String salvarLivro() throws ValidationException {

        super.insert();

        return super.openSearchPage();
    }

    public void salvarEditora() throws ValidationException {
        editoraService.insert(editora);
        editora = new Editora();
    }
    
    public void salvarAutor() throws ValidationException {
        autorService.insert(autor);
        autor = new Autor();
    }

    public String voltar() {
        return "/index.jsf";
    }

    public void exportarLivro() {
        Livro livroSelecionado = this.getForm().getEntity();
        String livroMarc = MarcParser.livroToMarc(this.getForm().getEntity());
        ByteArrayInputStream bais = new ByteArrayInputStream(livroMarc.getBytes());

        this.fileExportado = new DefaultStreamedContent(bais, "application/marc",
                livroSelecionado.getTitulo() + ".mrc");
    }

    public StreamedContent getFile() {
        return this.fileExportado;
    }
}
