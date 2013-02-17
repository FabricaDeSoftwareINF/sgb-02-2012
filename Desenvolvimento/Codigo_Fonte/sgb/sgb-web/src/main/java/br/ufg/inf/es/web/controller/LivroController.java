package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.util.UtilObjeto;
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
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import javax.faces.model.SelectItem;
import org.primefaces.event.SelectEvent;
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
        service.getDAO().closeSession();
        this.livroModel = new LivroDataModel((List) service.list());
        this.autor = new Autor();
        this.editora = new Editora();

        this.getForm().setTodosLivros(new ArrayList<Livro>());


        return super.openInitialPage();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void openInsertView() {
        super.openInsertView();
        this.getForm().setCursoSelecionado(new Curso());
        this.getForm().clearCollectionData();
        this.getForm().clearInsertData();
        this.getForm().clearSearchData();
        this.getForm().setTipoBibliografia(null);
        this.getForm().setAutoresAdicionados(new ArrayList<Autor>());
        this.getForm().setBibliografiaRemocao(new Bibliografia());
        this.getForm().setBibliografiaTemp(new Bibliografia());
    }

    @Override
    public void openEditView() {
        super.openEditView();
        this.getForm().setCursoSelecionado(new Curso());
        this.getForm().setTipoBibliografia(null);
        this.getForm().setAutoresAdicionados(new ArrayList<Autor>());
        this.getForm().setBibliografiaRemocao(new Bibliografia());
        this.getForm().setBibliografiaTemp(new Bibliografia());
        Collection<Autor> autoresAdicionados = this.getForm().getEntity().getAutores();
        if (autoresAdicionados != null) {
            this.getForm().setAutoresAdicionados(autoresAdicionados);
        }
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

        Livro[] retorno = null;

        if (this.livrosSelecionados != null) {

            retorno = this.livrosSelecionados.clone();
        }

        return retorno;
    }

    public void setLivrosSelecionados(Livro[] livrosSelecionados) {

        if (livrosSelecionados != null) {

            this.livrosSelecionados = (Livro[]) livrosSelecionados.clone();
        }
    }

    public EnumTipoBibliografia getTipoBibliografia() {

        return tipoBibliografia;
    }

    public void setTipoBibliografia(EnumTipoBibliografia tipoBibliografia) {

        this.tipoBibliografia = tipoBibliografia;
    }

    public void associarDisciplina() {

        if (UtilObjeto.isReferencia(this.getForm().getBibliografiaTemp().getDisciplina())
                && UtilObjeto.isReferencia(this.getForm().getTipoBibliografia())) {
            Livro livro = this.form.getEntity();
            Bibliografia bibliografia = this.form.getBibliografiaTemp();
            bibliografia.setTipo(this.form.getTipoBibliografia());
            bibliografia.setLivro(livro);
            Collection<Bibliografia> listaBibliografias = livro.getBibliografias();
            listaBibliografias.add(bibliografia);
            this.form.setBibliografiaTemp(new Bibliografia());
            this.form.setTipoBibliografia(null);
            this.form.setCursoSelecionado(null);
        } else {
            this.addWarningMessage("cadastro.livro.validacao.associacaodisciplina");
        }
    }

    public Collection<Livro> complete(String query) {

        return this.service.searchByAttributes(query, "titulo");
    }

    public Collection<Autor> completeAutor(String query) {
        Collection<Autor> autores = this.autorService.searchByAttributes(query, "nome", "sobrenome");
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
                results.removeAll(buscaDisciplinasAssociadas());
            }
        }
        return results;
    }

    private Collection<Disciplina> buscaDisciplinasAssociadas() {
        Collection<Bibliografia> bibliografias =
                this.getForm().getEntity().getBibliografias();
        Collection<Disciplina> disciplinasAdicionadas = new ArrayList<Disciplina>();

        for (Bibliografia bibliografia : bibliografias) {
            disciplinasAdicionadas.add(bibliografia.getDisciplina());
        }
        return disciplinasAdicionadas;
    }

    @Override
    public String openInsertPage() {

        this.cursos = this.cursoService.list();
        this.getForm().setCursoSelecionado(new Curso());
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
            this.getService().update(this.getForm().getEntity());
            this.getForm().clearInsertData();
            this.addSuccessMessage("arquitetura.msg.sucesso");

        } catch (ValidationException ex) {

            this.addWarningMessage(ex.getKeyMessage());

        }
        return this.openInitialPage();
    }

    public void salvarEditora() throws ValidationException {
        editoraService.insert(editora);
        this.getForm().getEntity().setEditora(editora);
        editora = new Editora();
    }

    public void salvarAutor() throws ValidationException {
        autorService.insert(autor);
        Collection<Autor> listAutores = this.getForm().getEntity().getAutores();
        if (listAutores != null) {
            listAutores.add(autor);
        } else {
            listAutores = new ArrayList<Autor>();
            listAutores.add(autor);
            this.getForm().getEntity().setAutores(listAutores);
        }
        this.getForm().getAutoresAdicionados().add(autor);
        autor = new Autor();
    }

    public void removerLivros() {
        List<Livro> livros = Arrays.asList(getLivrosSelecionados());
        try {
            getService().removeAll(livros);
            this.addSuccessMessage("arquitetura.msg.sucesso");
            this.getLivroModel().setWrappedData(this.getService().list());
        } catch (ValidationException ve) {
            this.addSuccessMessage(ve.getKeyMessage());
        }
    }

    public void removerBibliografia() {
        Bibliografia bibliografia = this.getForm().getBibliografiaRemocao();
        this.getForm().getEntity().getBibliografias().remove(bibliografia);
        bibliografia.getLivro().getBibliografias().remove(bibliografia);
        bibliografia.getDisciplina().getBibliografias().remove(bibliografia);
    }

    public String voltar() {
        return "/index.jsf";
    }

    public void exportarLivro() {
        Livro livroSelecionado = this.getForm().getEntity();
        try {
            String livroMarc = MarcParser.livroToMarc(this.getForm().getEntity());

            ByteArrayInputStream bais = new ByteArrayInputStream(livroMarc.getBytes(Charset.forName("UTF-8")));

            this.fileExportado = new DefaultStreamedContent(bais, "application/marc",
                    livroSelecionado.getTitulo() + ".mrc");
        } catch (Exception npe) {
            this.addErrorMessage("exportacaomarc.erro");
        }
    }

    public StreamedContent getFile() {
        return this.fileExportado;
    }

    public void handleUnselectAutor(UnselectEvent event) {

        Autor autorSelecionado = (Autor) event.getObject();

        this.getForm().getAutoresAdicionados().remove(autorSelecionado);
    }

    public void addAutorOnSelect(SelectEvent event) {

        Autor autorSelecionado = (Autor) event.getObject();

        this.getForm().getAutoresAdicionados().add(autorSelecionado);
    }

    /**
     * Cria opções para filtragem dos livros, no datatable, entre estrangeiros
     * ou não.
     *
     * @return Opções para filtragem dos livros, no datatable, entre
     * estrangeiros ou não.
     */
    public SelectItem[] getEstrangeiroOptions() {
        String simLabel = getBundle().getString("arquitetura.msg.sim");
        String naoLabel = getBundle().getString("arquitetura.msg.nao");
        SelectItem vazio = new SelectItem("");
        SelectItem sim = new SelectItem("true", simLabel);
        SelectItem nao = new SelectItem("false", naoLabel);
        return new SelectItem[]{vazio, sim, nao};
    }

}
