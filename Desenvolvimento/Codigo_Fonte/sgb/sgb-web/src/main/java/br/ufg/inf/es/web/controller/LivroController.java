package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.enuns.EnumTipoBibliografia;
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
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.hibernate.Hibernate;
import br.ufg.inf.es.web.datamodel.LivroDataModel;
import java.util.Arrays;
import java.util.List;
import org.primefaces.event.UnselectEvent;

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
    private EnumTipoBibliografia tipoBibliografia;
    private Curso cursoSelecionado;
    private Collection<Curso> cursos;
    private String formatoSelecionado;
    private StreamedContent fileExportado;
    private LivroDataModel livroModel;
    private Livro[] livrosSelecionados;

    /**
     * Método responsável por retornar a string de navegação para a pagina
     * incial da Estória de usuário buscar todos os livros.
     *
     * @return string de navegação
     */
    @Override
    public String openInitialPage() {
        this.livroModel = new LivroDataModel((List) service.list());
        this.autor = new Autor();
        this.editora = new Editora();
        this.getForm().setCursoSelecionado(new Curso());
        this.getForm().setAutoresAdicionados(this.getForm().getEntity().getAutores());

        this.getForm().setTodosLivros(new ArrayList<Livro>());
        buscaTodosLivros();

        return super.openInitialPage();
    }

    public LivroDataModel getLivroModel() {
        return this.livroModel;
    }

    public Curso getCursoSelecionado() {
        return cursoSelecionado;
    }

    public void setCursoSelecionado(Curso cursoSelecionado) {
        this.cursoSelecionado = cursoSelecionado;
    }

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

    public Livro[] getLivrosSelecionados() {
        return livrosSelecionados;
    }

    public void setLivrosSelecionados(Livro[] livrosSelecionados) {
        this.livrosSelecionados = livrosSelecionados;
    }

    public EnumTipoBibliografia getTipoBibliografia() {
        return tipoBibliografia;
    }

    public void setTipoBibliografia(EnumTipoBibliografia tipoBibliografia) {
        this.tipoBibliografia = tipoBibliografia;
    }

    public void associarDisciplina() {
        Livro livro = this.form.getEntity();
        Bibliografia bibliografia = this.form.getBibliografiaTemp();
        bibliografia.setTipo(this.form.getTipoBibliografia());
        bibliografia.setLivro(livro);
        Collection<Bibliografia> listaBibliografias = livro.getBibliografias();
        listaBibliografias.add(bibliografia);
        this.form.setBibliografiaTemp(new Bibliografia());
    }

    public Collection<Livro> complete(String query) {
        Collection<Livro> livros = this.service.searchByAttributes(query, "titulo");
//        livros.removeAll(Arrays.asList(this.livrosSelecionados));
        return this.service.searchByAttributes(query, "titulo");
    }

    public Collection<Autor> completeAutor(String query) {
        Collection<Autor> autores = this.autorService.
                searchByAttributes(query, "nome", "sobrenome");
        Collection<Autor> autoresAdicionados = this.getForm().getAutoresAdicionados();
        if (autoresAdicionados != null) {
            autores.removeAll(autoresAdicionados);
        }
        return autores;
    }

    public Collection<Editora> completeEditora(String query) {
        return this.editoraService.searchByAttributes(query, "nome");
    }

    public Collection<Disciplina> completeDisciplina(String query) {
        Collection<Disciplina> results = new ArrayList<Disciplina>();

        Collection<Disciplina> disciplinas = disciplinaService.getDAO().
                listarDisciplinasDeUmCurso(this.getForm().getCursoSelecionado().getId());

        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getNome().toUpperCase().contains(query.toUpperCase())) {
                results.add(disciplina);
            }
        }
        return results;
    }

    @Override
    public String openInsertPage() {

        this.cursos = this.cursoService.list();
        this.getForm().setCursoSelecionado(new Curso());


        this.openInsertView();
        return "/paginas/livro/inclusao.xhtml";
    }

    public String salvarLivro() throws ValidationException {

        super.insert();

        return super.openSearchPage();
    }

    public String editarLivro() throws ValidationException {

        try {
            Hibernate.isInitialized(this.getForm().getEntity());

            Hibernate.initialize(this.getForm().getEntity());
            this.getService().save(this.getForm().getEntity());
            this.getForm().clearInsertData();
            this.addSuccessMessage("arquitetura.msg.sucesso");

        } catch (ValidationException ex) {

            this.addWarningMessage(ex.getKeyMessage());

        }
        return this.openInitialPage();
    }

    public void salvarEditora() throws ValidationException {
        editoraService.insert(editora);
        editora = new Editora();
    }

    public void salvarAutor() throws ValidationException {
        autorService.insert(autor);
        autor = new Autor();
    }

    public void removerLivros() {
        List<Livro> livros = Arrays.asList(getLivrosSelecionados());
        try {
            getService().removeAll(livros);
            this.addSuccessMessage("arquitetura.msg.sucesso");
            this.getLivroModel().setWrappedData(this.getService().list());
        } catch (ValidationException ve) {
            this.addSuccessMessage("arquitetura.msg.erro");
        }
    }

    public String voltar() {
        return "/index.jsf";
    }

    public void exportarLivro() {
        Livro livroSelecionado = this.getForm().getEntity();
        try {
            String livroMarc = MarcParser.livroToMarc(this.getForm().getEntity());
            ByteArrayInputStream bais = new ByteArrayInputStream(livroMarc.getBytes());

            this.fileExportado = new DefaultStreamedContent(bais, "application/marc",
                    livroSelecionado.getTitulo() + ".mrc");
        } catch (Exception npe) {
            this.addErrorMessage("exportacaomarc.erro");
        }
    }

    public StreamedContent getFile() {
        return this.fileExportado;
    }

    /**
     * @author Jackeline
     */
    public void buscaTodosLivros() {
        this.getForm().setTodosLivros(this.getService().buscaTodosLivros(this.getForm().getFiltroTitulo()));
        this.getForm().setFiltroTitulo("");
        limpaEntidadeDeCadastro();
    }

    /**
     * @author Jackeline
     */
    private void limpaEntidadeDeCadastro() {
        this.getForm().setEntity(new Livro());
    }

    public void handleUnselectAutor(UnselectEvent event) {
        Autor autor = (Autor) event.getObject();
        this.getForm().getAutoresAdicionados().remove(autor);
    }

    public void addAutorOnSelect(UnselectEvent event) {
        Autor autor = (Autor) event.getObject();
        this.getForm().getAutoresAdicionados().add(autor);
    }
}
