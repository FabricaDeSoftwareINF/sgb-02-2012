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
import br.ufg.inf.es.web.controller.form.CursoForm;
import br.ufg.inf.es.web.controller.form.DisciplinaForm;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
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

        this.getForm().setExibirDialogRemocao(Boolean.FALSE);

        this.getForm().getEntity().setCurso(new Curso());

        this.getForm().setCursos(this.getCursoService().list());

        this.getForm().setTipoBibliografias(new ArrayList<EnumTipoBibliografia>());

        this.getForm().setLivrosSelecionados(new ArrayList<Livro>());

        this.getForm().setBibliografiasAssociadas(new ArrayList<Bibliografia>());

        this.getForm().setTipoBibliografias(Arrays.asList(EnumTipoBibliografia.values()));
        
        this.getForm().setSelecionadosAux(new ArrayList<Livro>());
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

            List<Livro> aux = new ArrayList<Livro>(this.getForm().getLivrosSelecionados());

            for (Livro livro : this.getForm().getLivrosSelecionados()) {

                while (Collections.frequency(aux, livro) >= 1) {

                    if (Collections.frequency(aux, livro) == 1) {

                        Bibliografia bibliografia = new Bibliografia();

                        bibliografia.setTipo(this.getForm().getTipoBibliografiaSelecionado());

                        bibliografia.setLivro(livro);

                        this.getForm().getBibliografiasAssociadas().add(bibliografia);
                        
                    }
                    
                    aux.remove(livro);
                    
                    break;
                }
            }

            this.getForm().setLivrosSelecionados(new ArrayList<Livro>());
            
            this.getForm().setSelecionadosAux(new ArrayList<Livro>());
            

        } else {

            this.addWarningMessage("label.disciplina.nenhumLivroSelecionado");

        }

    }

    /**
     * Método responsável por inserir uma nova Disciplina
     *
     * @return String de navegação
     */
    public String inserir() {

        try {

            this.getService().insert(this.form.getEntity());

            this.addSuccessMessage(DisciplinaController.KEY_SUCESSO);

        } catch (ValidationException ex) {

            this.addWarningMessage(ex.getKeyMessage());

        }

        return this.openInitialPage();
    }
    
    /**
     * Método responsável por remover uma Bibliografia da associação criada pelo usuário
     * 
     * @author Cássio Augusto Silva de Freitas
     */
    public void desassociarBibliografia() {
        
        this.getForm().getBibliografiasAssociadas().remove(this.getForm().getBibliografiaSelecionada());
        
        this.getForm().setBibliografiaSelecionada(new Bibliografia());
          
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
