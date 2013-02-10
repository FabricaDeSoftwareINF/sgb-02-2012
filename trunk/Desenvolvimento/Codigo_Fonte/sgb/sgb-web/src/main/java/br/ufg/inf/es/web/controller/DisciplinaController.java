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
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
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

        this.getForm().setExibirDialogRemocao(Boolean.FALSE);

        this.getForm().setExibirDialogDetalhe(Boolean.FALSE);

        this.getForm().getEntity().setCurso(new Curso());

        this.getForm().setCursos(this.getCursoService().list());

        this.getForm().setTipoBibliografias(new ArrayList<EnumTipoBibliografia>());

        this.getForm().setLivrosSelecionados(new ArrayList<Livro>());

        this.getForm().setBibliografiasAssociadas(new ArrayList<Bibliografia>());

        this.getForm().setTipoBibliografias(Arrays.asList(EnumTipoBibliografia.values()));

        this.getForm().setSelecionadosAux(new ArrayList<Livro>());

        this.getForm().setDataModelDisciplina(new DisciplinaDataModel((List) this.getForm().getCollectionEntities()));
    }

    @Override
    public String openEditPage() {

        this.getForm().setIsEditPage(Boolean.TRUE);

        this.getForm().setEntity(this.getService().find(this.getForm().getDisciplinaEdicao().getId()));

        this.getForm().getEntity().setBibliografias((Collection<Bibliografia>) this.getService().getDAO().getCollection(this.getForm().getEntity().getId(), "bibliografias"));

        this.getForm().setBibliografiasAssociadas(this.getForm().getEntity().getBibliografias());

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

        for (Bibliografia bl : this.getForm().getBibliografiasAssociadas()) {

            if (livros.contains(bl.getLivro())) {

                livros.remove(bl.getLivro());
            }

        }

        livros.removeAll(this.getForm().getSelecionadosAux());

        return livros;

    }

    /**
     * Método responsável por adicionar um Livro na coleção auxiliar de livros
     * selecionados
     *
     * @author Cássio Augusto Silva de Freitas
     * @param livro
     */
    public void addLivroOnSelect(SelectEvent event) {

        Livro livro = (Livro) event.getObject();

        this.getForm().getSelecionadosAux().add(livro);

    }

    /**
     * Método responsável por remover um LivroSelecionado da coleção auxiliar
     * quando se tira ele do autocomplete
     *
     * @author Cássio Augusto Silva de Freitas
     * @param livro
     */
    public void removeOnUnselect(UnselectEvent unEvent) {

        Livro livro = (Livro) unEvent.getObject();

        this.getForm().getSelecionadosAux().remove(livro);

    }

    /**
     * Método responsável por associar os Livros selecionados a Disciplina
     *
     * @author Cássio Augusto Silva de Freitas
     */
    public void associarLivros() {

        if (UtilObjeto.isReferencia(this.getForm().getLivrosSelecionados()) && !this.getForm().getLivrosSelecionados().isEmpty()) {

            for (Livro livro : this.getForm().getLivrosSelecionados()) {

                Bibliografia bliografia = new Bibliografia();

                bliografia.setTipo(this.getForm().getTipoBibliografiaSelecionado());

                bliografia.setLivro(livro);

                this.getForm().getBibliografiasAssociadas().add(bliografia);

            }

            this.getForm().setLivrosSelecionados(new ArrayList<Livro>());

            this.getForm().setSelecionadosAux(new ArrayList<Livro>());


        } else {

            this.addWarningMessage("label.disciplina.nenhumLivroSelecionado");

        }
    }

    /**
     * Método responsável por inserir e/ou editar uma disciplina
     *
     * @return String de navegação
     */
    public void inserir() {

        try {

            this.getForm().getEntity().setBibliografias(this.getForm().getBibliografiasAssociadas());

            this.getService().inserir(this.form.getEntity());

            this.addSuccessMessage(DisciplinaController.KEY_SUCESSO);

            this.openInitialPage();

        } catch (ValidationException ex) {

            this.addWarningMessage(ex.getKeyMessage());

        }


    }

    /**
     * Método responsável por editar uma disciplina
     *
     * @author Cássio Augusto , Marco Aurélio
     */
    public void editarDisciplina() {

        try {

            this.getService().editarDisciplina(this.getForm().getEntity());

            this.addSuccessMessage(DisciplinaController.KEY_SUCESSO);

            this.openInitialPage();

        } catch (ValidationException ex) {

            this.addWarningMessage(ex.getKeyMessage());
        }
    }

    /**
     * Método responsável por exibir o dialog de remoção
     *
     * @author Cássio Augusto
     */
    public void exibirDialogRemocao() {

        this.getForm().setExibirDialogRemocao(Boolean.TRUE);

    }

    /**
     * Método responsável por remover uma Bibliografia da associação criada pelo
     * usuário
     *
     * @author Cássio Augusto Silva de Freitas
     */
    public void desassociarBibliografia() {

        this.getForm().setExibirDialogRemocao(Boolean.FALSE);

        this.getForm().getBibliografiasAssociadas().remove(this.getForm().getBibliografiaParaRemocao());


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
     * Método responsável por exibir o dialog de confirmação da remoção.
     * 
     * @author Cássio Augusto Silva Freitas, Marco Aurélio
     */
    public void exibirConfirmDialog() {

        this.getForm().setExibirDialogRemocao(Boolean.TRUE);
    }

    /**
     * Método responsável por remover as disciplinas selecionadas pelo usuário.
     * 
     * @author Cássio Augusto Silva Freitas, Marco Aurélio
     */
    public void removerDisciplinasSelecionadas() {

        if (UtilObjeto.isReferencia(this.getForm().getDisciplinasSelecionadas()) && this.getForm().getDisciplinasSelecionadas().length != 0) {

            Collection<Disciplina> colecao = Arrays.asList(this.getForm().getDisciplinasSelecionadas());
        
            try {
                
                this.getService().removeAll(colecao);
                
                fecharDialogRemocao();
            
            } catch (ValidationException ex) {
            
                Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        } else {

            this.addWarningMessage(KEY_NENHUMA_DISCIPLINA_MESSAGE);

        }
    }
    
    /**
     * Método responsável por fechar o dialog de remoção.
     * 
     * @author Cássio Augusto Silva de Freitas, Marco Aurélio Camargo
     */
    public void fecharDialogRemocao() {
        
        this.getForm().setExibirDialogRemocao(Boolean.FALSE);
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
