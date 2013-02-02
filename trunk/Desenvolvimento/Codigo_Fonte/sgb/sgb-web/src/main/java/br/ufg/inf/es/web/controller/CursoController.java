package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.CursoService;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.web.controller.form.CursoForm;
import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *  Classe controlodara para a entidade curso.
 * 
 * @author Diogo Gon&ccedil;alves Teodoro
 * @author Alan Vieira Ribeiro
 */
@Component
@Scope("session")
public class CursoController extends SGBController<Curso, CursoForm, CursoService> {

    @Autowired
    private CursoForm form;
    
    @Autowired
    private CursoService service;
         
    /**
     * Método responsável por salvar um curso.
     * 
     * @author Allan Vieira Ribeiro
     * @author Diogo Gonçalves Teodoro
     */
    @Override
    public void insert() {
        
        try {
            
            this.getService().insert(this.getForm().getEntity(), this.getForm().getListaDisciplinaAssociacao());
            
            addSuccessMessage("arquitetura.msg.sucesso");
            
        } catch (ValidationException ve) {
            
            addErrorMessage(ve.getKeyMessage());            
            
        } finally {

            this.clearData();
        }       
    }

    /**
     * Método responsável por iniciar os dados de inicialização da página inclusão
     * @return String com o caminho da página de inclusão
     */
    @Override
    public String openInsertPage() {
     
        this.getForm().setListaDisciplinaComboBox(this.getService().listarDisciplinasNaoVinculadasACurso());
        
        this.clearData();
        
        return super.openInsertPage();
    }   
    
    /**
     * Método responsável por iniciar os dados de inicialização da página edição
     * @return String com o caminho da página de edição.
     */
    @Override
    public String openEditPage() {
     
        this.getForm().setListaDisciplinaComboBox(this.getService().listarDisciplinasNaoVinculadasACurso());
        
        this.getService().getDAO().refresh(this.getForm().getEntity());
        
        this.getForm().setListaDisciplinaAssociacao(this.getService().listarDisciplinasDeUmCurso(this.getForm().getEntity().getId()));
        
        return super.openEditPage();
    }   

    /**
     * Método responsável por adicionar uma disciplina na lista temporária de 
     * associação com um curso.
     * 
     */
    public void addDisciplinaAssociacao() {

        if(this.getForm().getDisciplinaSelecionada().getNome() != null && !this.getForm().getDisciplinaSelecionada().getNome().isEmpty()){
        
            this.getForm().getListaDisciplinaAssociacao().add(this.getForm().getDisciplinaSelecionada());

            this.getForm().getListaDisciplinaComboBox().remove(this.getForm().getDisciplinaSelecionada());
        }
    }

    /**
     * Método responsável por remover uma disciplina da lista temporária de 
     * associação com um curso.
     */
    public void removeDisciplina() {
        
        this.getForm().getListaDisciplinaComboBox().add(this.getForm().getDisciplinaToRemove());
        
        this.getForm().getListaDisciplinaAssociacao().remove(this.getForm().getDisciplinaToRemove());
    }
    
    /**
     * Método responsável por limpar os campos da tela retornando para os valores padrões.
     * 
     * @author Allan Vieira Ribeiro
     */
    public void clearData() {
        
        this.getForm().setEntity(new Curso());
        
        this.getForm().setDisciplinaSelecionada(new Disciplina());
        
        this.getForm().setListaDisciplinaAssociacao(new LinkedList<Disciplina>());
        
        this.getForm().setListaDisciplinaComboBox(this.getService().listarDisciplinasNaoVinculadasACurso());
    }

    @Override
    public CursoForm getForm() {
        
        return form;
    }

    @Override
    public CursoService getService() {
        
        return service;
    }
}
