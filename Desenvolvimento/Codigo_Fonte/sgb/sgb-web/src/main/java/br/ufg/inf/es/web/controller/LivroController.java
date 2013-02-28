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
import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;
import br.ufg.inf.es.persistencia.biblioteca.LivrosBibliotecaDAO;
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
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.NotFoundException;
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
    @Autowired
    private LivrosBibliotecaDAO livrosBibliotecaDAO;
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

        this.form.setLivrosAssociados(new ArrayList<LivroBiblioteca>());

        return super.openInitialPage();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String openInsertPage() {
        this.initData();
        this.cursos = this.cursoService.list();
        return super.openInsertPage();
    }

    @Override
    public String openEditPage() {
        this.cursos = this.cursoService.list();
        Collection<Autor> autoresAdicionados = this.getForm().getEntity().getAutores();
        if (autoresAdicionados != null) {
            this.getForm().setAutoresAdicionados(autoresAdicionados);
        }

        if (this.getForm().getEntity().getCodigosLivrosBiblioteca() != null
                && (!this.getForm().getEntity().getCodigosLivrosBiblioteca().isEmpty())) {

            for (Long codigoLivro : this.getForm().getEntity().getCodigosLivrosBiblioteca()) {
                try {
                    LivroBiblioteca livroBiblioteca = this.livrosBibliotecaDAO.
                            getLivroBibliotecaCodigo(codigoLivro);

                    if (this.getForm().getLivrosAssociados() == null) {
                        this.getForm().setLivrosAssociados(new ArrayList<LivroBiblioteca>());
                    }

                    this.getForm().getLivrosAssociados().add(livroBiblioteca);

                } catch (NotFoundException ex) {

                    this.addWarningMessage("arquitetura.msg.notfound");
                } catch (SQLException ex) {
                    StringBuilder exception = new StringBuilder();
                    exception.append("arquitetura.msg.connectionexception");
                    exception.append("\n");
                    exception.append(ex.getMessage());
                    this.addWarningMessage("arquitetura.msg.connectionexception");
                }
            }
        }

        return super.openEditPage();
    }

    @Override
    public void initData() {
        this.getForm().setCursoSelecionado(new Curso());
        this.getForm().clearCollectionData();
        this.getForm().clearInsertData();
        this.getForm().clearSearchData();
        this.getForm().setTipoBibliografia(null);
        this.setLivrosSelecionados(new Livro[]{});
        this.getForm().setAutoresAdicionados(new ArrayList<Autor>());
        this.getForm().setBibliografiaRemocao(new Bibliografia());
        this.getForm().setBibliografiaTemp(new Bibliografia());
        this.getForm().setLivroBiblioteca(new LivroBiblioteca());
    }

    @Override
    public String openSearchPage() {
        this.form.setLivrosAssociados(new ArrayList<LivroBiblioteca>());
        return super.openSearchPage();
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

    public void associarLivroBiblioteca() {

        if (UtilObjeto.isReferencia(this.getForm().getLivroBiblioteca())) {
            Livro livro = this.form.getEntity();

            if (livro != null) {
                Collection<Long> codigosLivrosBiblioteca = livro.getCodigosLivrosBiblioteca();
                if (this.form.getLivroBiblioteca().getId() != null) {
                    if (codigosLivrosBiblioteca == null) {
                        codigosLivrosBiblioteca = new ArrayList<Long>();
                    }

                    if (!codigosLivrosBiblioteca.contains(this.form.getLivroBiblioteca().getId())) {
                        codigosLivrosBiblioteca.add(this.form.getLivroBiblioteca().getId());
                        this.form.getEntity().setCodigosLivrosBiblioteca(codigosLivrosBiblioteca);

                        this.form.getLivrosAssociados().add(this.form.getLivroBiblioteca());
                        this.form.setLivroBiblioteca(null);
                    } else {
                        this.addWarningMessage("Livro já associado. Verifique!");
                    }

                } else {
                    this.addErrorMessage("Não foi selecionado um Livro da Biblioteca. Verifique!");
                }
            } else {
                this.addWarningMessage("Para associar um livro da biblioteca, "
                        + "primeiro deve ser selecione um livro do sistema.");
            }

        } else {
            this.addWarningMessage("cadastro.livro.validacao.associacaolivrobiblioteca");
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

    public Collection<LivroBiblioteca> completeLivroBiblioteca(String query) {

        Collection<LivroBiblioteca> livrosBiblioteca = new ArrayList<LivroBiblioteca>();
        Collection<LivroBiblioteca> livrosAssociados = new ArrayList<LivroBiblioteca>();


        Map<String, String> keysQuery = getKeyQuery(query.trim());
        try {
            if (keysQuery.containsKey("CODIGO")) {
                Long codigoLivro = 0l;
                try {
                    codigoLivro = new Long(keysQuery.get("CODIGO"));
                } catch (NumberFormatException ex) {
                    this.addErrorMessage("Código informado na pesquisa de livros "
                            + "na biblioteca deve ser número inteiro longo.");
                }

                Collection<LivroBiblioteca> livrosBibl = new ArrayList<LivroBiblioteca>();
                livrosBibl.add(this.livrosBibliotecaDAO.
                        getLivroBibliotecaCodigo(codigoLivro));

                livrosBiblioteca = livrosBibl;

            } else if (keysQuery.containsKey("CÓDIGO")) {

                Long codigoLivro = 0l;
                try {
                    codigoLivro = new Long(keysQuery.get("CÓDIGO"));
                } catch (NumberFormatException ex) {
                    this.addErrorMessage("Código informado na pesquisa de livros "
                            + "na biblioteca deve ser número inteiro longo.");
                }

                Collection<LivroBiblioteca> livrosBibl = new ArrayList<LivroBiblioteca>();
                livrosBibl.add(this.livrosBibliotecaDAO.
                        getLivroBibliotecaCodigo(codigoLivro));

                livrosBiblioteca = livrosBibl;

            } else if (keysQuery.containsKey("ISBN")) {

                livrosBiblioteca = this.livrosBibliotecaDAO.
                        getLivrosBibliotecaIsbn(keysQuery.get("ISBN"));

            } else if (keysQuery.containsKey("AUTOR")) {

                livrosBiblioteca = this.livrosBibliotecaDAO.
                        getLivrosBibliotecaAutor(keysQuery.get("AUTOR"));

            } else {
                livrosBiblioteca = this.livrosBibliotecaDAO.
                        getLivrosBibliotecaTitulo(query);
            }

            livrosAssociados = getLivrosBibliotecaAssociados(this.form.getEntity().getCodigosLivrosBiblioteca());

            for (LivroBiblioteca livroBiblioteca : livrosAssociados) {
                boolean retirou = livrosBiblioteca.remove(livroBiblioteca);
                System.out.println(retirou);
            }

        } catch (NotFoundException ex) {
            this.addWarningMessage("arquitetura.msg.notfound");
        } catch (SQLException ex) {
            StringBuilder exception = new StringBuilder();
            exception.append("arquitetura.msg.connectionexception");
            exception.append("\n");
            exception.append(ex.getMessage());
            this.addWarningMessage("arquitetura.msg.connectionexception");
        }

        return livrosBiblioteca;
    }

    private Collection<LivroBiblioteca> getLivrosBibliotecaAssociados(Collection<Long> codigoLivrosAssociados) {
        Collection<LivroBiblioteca> livrosAssociados = new ArrayList<LivroBiblioteca>();

        if (codigoLivrosAssociados != null && (!codigoLivrosAssociados.isEmpty())) {
            for (Long codigo : codigoLivrosAssociados) {
                try {
                    LivroBiblioteca livroBiblioteca = this.livrosBibliotecaDAO.getLivroBibliotecaCodigo(codigo);
                    livrosAssociados.add(livroBiblioteca);
                } catch (NotFoundException ex) {
                    Logger.getLogger(LivroController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(LivroController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return livrosAssociados;
    }

    private Map<String, String> getKeyQuery(String query) {
        final int POSICAO_UM = 1;

        Map<String, String> keyQuery = new HashMap<String, String>();

        int posicaoIgualdade = 0;
        String key = null;
        String conteudo = null;

        boolean isIgualdade = false;

        for (int i = POSICAO_UM; i <= query.length(); i++) {

            if (query.substring(i - POSICAO_UM, i).equals("=")) {
                posicaoIgualdade = i;
                isIgualdade = true;
            }
        }

        if (isIgualdade) {
            key = query.substring(0, posicaoIgualdade - 1).toUpperCase();
            conteudo = query.substring(posicaoIgualdade, query.length());
            keyQuery.put(key.toUpperCase(), conteudo);
        }
        return keyQuery;
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

    public String salvarLivro() throws ValidationException {

        super.insert();
        return openSearchPage();
    }

    public String editarLivro() throws ValidationException {

        try {
            Hibernate.isInitialized(this.getForm().getEntity());

            Hibernate.initialize(this.getForm().getEntity());
            this.getService().insert(this.getForm().getEntity());
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

    public void removerReferenciaBiblioteca() {
        LivroBiblioteca livroBiblioteca = this.getForm().getLivroBibliotecaRemocao();
        this.getForm().getLivrosAssociados().remove(livroBiblioteca);
        this.getForm().getEntity().getCodigosLivrosBiblioteca().remove(livroBiblioteca.getId());
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
