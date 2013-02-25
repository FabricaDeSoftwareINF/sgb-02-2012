package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.util.UtilObjeto;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.enuns.EnumTipoBibliografia;
import br.ufg.inf.es.integracao.CursoService;
import br.ufg.inf.es.integracao.DisciplinaService;
import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.controller.form.DisciplinaForm;
import br.ufg.inf.es.web.datamodel.DisciplinaDataModel;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marco Aurélio, Cássio Augusto Silva de Freitas
 */
@Component
@Scope("session")
public class DisciplinaController extends SGBController<Disciplina, DisciplinaForm, DisciplinaService> {

    private static final String KEY_SUCESSO = "arquitetura.msg.sucesso";
    
    private static final String KEY_NENHUMA_DISCIPLINA_MESSAGE = "label.disciplina.nenhumaSelecionada";
    
    private static final String KEY_NENHUM_LIVRO_SELECIONADO = "label.disciplina.nenhumLivroSelecionado";
    
    private static final String KEY_NENHUM_TIPO_SELECIONADO = "label.disciplina.nenhumTipoSelecionado";
   
    @Autowired
    private DisciplinaForm form;
    
    @Autowired
    private DisciplinaService service;
   
    @Autowired
    private CursoService cursoService;

    /**
     * Método responsável por iniciar o dados do formulário e preparar para
     * abrir a tela inicial
     *
     * @author Cássio Augusto Silva de Freitas
     */
    @Override
    public void initData() {

        super.initData();

        this.getForm().setIsEditPage(Boolean.FALSE);

        this.getForm().setExibirDialogDetalhe(Boolean.FALSE);

        this.getForm().getEntity().setCurso(new Curso());

        this.getForm().setCursos(this.getCursoService().list());

        this.getForm().setTipoBibliografias(new ArrayList<EnumTipoBibliografia>());

        this.getForm().setLivroSelecionado(new Livro());

        this.getForm().setTipoBibliografias(Arrays.asList(EnumTipoBibliografia.values()));

        this.getForm().setSelecionadosAux(new ArrayList<Livro>());
        
        this.getForm().setDisciplinasSelecionadas(new ArrayList<Disciplina>());

        this.getForm().setDataModelDisciplina(new DisciplinaDataModel((List) this.getForm().getCollectionEntities()));
    }

    @Override
    public String openInitialPage() {

        this.service.getDAO().closeSession();

        return super.openInitialPage();

    }

    @Override
    public String openEditPage() {

        this.getForm().setIsEditPage(Boolean.TRUE);

        this.getForm().setEntity(this.getService().find(this.getForm().getDisciplinaEdicao().getId()));

        this.getForm().getEntity().setBibliografias((Collection<Bibliografia>) this.getService().getDAO().getCollection(this.getForm().getEntity().getId(), "bibliografias"));
        
        Collection<Bibliografia> bibliografias = this.getForm().getEntity().getBibliografias();

        Collection<Livro> livrosAssociados = new ArrayList<Livro>();

        for (Bibliografia bibliografia : bibliografias) {

            livrosAssociados.add(bibliografia.getLivro());

        }

        this.getForm().setSelecionadosAux(livrosAssociados);
        
        this.getService().getDAO().closeSession();

        return super.openEditPage();

    }

    /**
     * Método responsável por buscar coleção de Livros de acordo com o titulo
     * digitado pelo usuário
     *
     * @author Cássio Augusto Silva de Freitas
     * @param query
     * @return Coleção de Livros
     */
    public Collection<Livro> buscaLivros(String query) {

        List<Livro> livros = (List<Livro>) this.getService().buscaLivros(query);
        
        for(Livro livro : livros) {
            
            livro.setAutores(this.getService().buscaAutores(livro.getId()));
        }

        livros.removeAll(this.getForm().getSelecionadosAux());

        return livros;

    }

    /**
     * Método responsável por associar os Livros selecionados a Disciplina
     *
     * @author Cássio Augusto Silva de Freitas
     */
    public void associarLivros() {


        if (UtilObjeto.isReferencia(this.getForm().getBibliografiaTmp().getLivro())
                && UtilObjeto.isReferencia(this.getForm().getTipoBibliografiaSelecionado())) {
            
            Disciplina disciplina = getForm().getEntity();

            Bibliografia bibliografia = getForm().getBibliografiaTmp();

            bibliografia.setTipo(getForm().getTipoBibliografiaSelecionado());

            bibliografia.setDisciplina(disciplina);

            Collection<Bibliografia> listaBibliografias = disciplina.getBibliografias();

            listaBibliografias.add(bibliografia);

            this.getForm().setBibliografiaTmp(new Bibliografia());

            this.getForm().setTipoBibliografiaSelecionado(null);

            this.getForm().getSelecionadosAux().add(bibliografia.getLivro());

        } else if(!UtilObjeto.isReferencia(this.getForm().getTipoBibliografiaSelecionado())){
            
            this.addWarningMessage(KEY_NENHUM_TIPO_SELECIONADO);
            
        } else {
            
            this.addWarningMessage(KEY_NENHUM_LIVRO_SELECIONADO);
        }
    }

    /**
     * Método responsável por inserir e/ou editar uma disciplina
     *
     * @return String de navegação
     */
    public void inserir() {

        try {

            this.getService().update(this.form.getEntity());

            this.addSuccessMessage(DisciplinaController.KEY_SUCESSO);

            limparCampos();

        } catch (ValidationException ex) {

            this.addWarningMessage(ex.getKeyMessage());

        }


    }

    /**
     * Método responsável por limpar os campos após o cadastro.
     */
    private void limparCampos() {

        this.getForm().setEntity(new Disciplina());
        
        this.getForm().setSelecionadosAux(new ArrayList<Livro>());

    }

    /**
     * Método responsável por editar uma disciplina
     *
     * @author Cássio Augusto , Marco Aurélio
     */
    public String editarDisciplina() {

        try {

            Hibernate.isInitialized(this.getForm().getEntity());

            Hibernate.initialize(this.getForm().getEntity());

            this.getService().update(this.getForm().getEntity());

            this.addSuccessMessage(DisciplinaController.KEY_SUCESSO);

        } catch (ValidationException ex) {
            
            this.getService().getDAO().closeSession();

            this.addWarningMessage(ex.getKeyMessage());
            
        }
        return this.openInitialPage();
    }

    /**
     * Método responsável por remover uma Bibliografia da associação criada pelo
     * usuário
     *
     * @author Cássio Augusto Silva de Freitas
     */
    public void desassociarBibliografia() {
        
        Bibliografia bibliografia = this.getForm().getBibliografiaParaRemocao();
        
        this.getForm().getEntity().getBibliografias().remove(bibliografia);
       
        bibliografia.getDisciplina().getBibliografias().remove(bibliografia);
        
        this.getForm().getSelecionadosAux().remove(bibliografia.getLivro());
              
    }

    /**
     * Método responsável por exibir detalhes de uma Disciplina.
     *
     * @author Cássio Augusto Silva de Freitas, Marco Aurélio Camargo
     */
    public void exibirDetalhes() {

        this.getForm().setExibirDialogDetalhe(Boolean.TRUE);

        this.getForm().getDisciplinaDetalhe().setBibliografias((Collection<Bibliografia>) this.getService().getDAO().getCollection(this.getForm().getDisciplinaDetalhe().getId(), "bibliografias"));
    }

    /**
     * Método responsável por fechar o dialog de detalhes
     *
     * @author Cássio Augusto Silva de Freitas, Marco Aurélio Camargo
     */
    public void fecharDialogDetalhe() {

        this.getForm().setExibirDialogDetalhe(Boolean.FALSE);
    }

    /**
     * Método responsável por remover as disciplinas selecionadas pelo usuário.
     *
     * @author Cássio Augusto Silva Freitas, Marco Aurélio
     */
    public void removerDisciplinasSelecionadas() {

        Collection<Disciplina> disciplinasSeleciondadas =
                this.getForm().getDisciplinasSelecionadas();

        if (UtilObjeto.isReferencia(disciplinasSeleciondadas)) {
            try {

                this.getService().removeAll(disciplinasSeleciondadas);

                this.getForm().setDisciplinasSelecionadas(new ArrayList<Disciplina>());

                initData();

                this.addSuccessMessage(KEY_SUCESSO);

            } catch (ValidationException ex) {

                Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            this.addWarningMessage(KEY_NENHUMA_DISCIPLINA_MESSAGE);

        }
    }

    @Override
    public DisciplinaForm getForm() {
        return form;
    }

    @Override
    public DisciplinaService getService() {
        return this.service;
    }

    public void setService(DisciplinaService service) {
        this.service = service;
    }

    public void setForm(DisciplinaForm form) {
        this.form = form;
    }

    public CursoService getCursoService() {
        return cursoService;
    }

    public void setCursoService(CursoService cursoService) {
        this.cursoService = cursoService;
    }
}
