package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.AutorService;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.AutorDTO;
import br.ufg.inf.es.web.controller.form.AutorForm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Cézar Augusto Ferreira
 */
@Component
@Scope("request")
public class AutorController
        extends SGBController<Autor, AutorForm, AutorService> {

    @Autowired
    private AutorForm form;
    
    @Autowired
    private AutorService service;

    /**
     * Método responsável por retornar a string de navegação para a pagina incial da Estória de usuário
     * buscar todos os altores.
     * @author Cássio Augusto Silva de Freitas
     * @return  string de navegação
     */
    @Override
    public String openInitialPage() {

        this.getForm().setExibirDialogExclusao(Boolean.FALSE);   

        buscaTodosAutores();

        return super.openInitialPage();
    }

    @Override
    public String openEditPage() {
        
        buscaTodosAutores();
       
        return this.getRootNavigation() + "editPage";
    }
    
    

    /**
     * Método responsável por buscar todos os autores do banco de dados e
     * colocar a coleção no formulário da estória de usuário.
     *
     * @author Cássio Augusto Silva de Freitas
     */
    public void buscaTodosAutores() {

        this.getForm().setTodosAutores(this.getService().buscaTodosAutores(this.getForm().getFiltroNome()));

        this.getForm().setFiltroNome("");

        limpaEntidadeDeCadastro();
    }

    /**
     * Método responsável por limpar a entidade a preparando para uma edição
     * 
     * @author Cássio Augusto Silva de Freitas
     */
    public void prepararInsercao() {

        limpaEntidadeDeCadastro();

    }

    /**
     * Método responsável por inserir um novo Autor
     * @author Cássio Augus1to Silva de Freitas
     */
    public void insereAutor() {
        try {

            this.getService().insert(this.getForm().getEntity());

            this.addSuccessMessage("arquitetura.msg.sucesso");

            buscaTodosAutores();

        } catch (ValidationException ex) {

            this.addWarningMessage(ex.getKeyMessage());

        }
    }

    /**
     * Método responsável por validar se pode aver uma exclusão ou não, 
     * verificando se há a necessidade de exibir o dialog de confirmação ou não. 
     * @author Cássio Augusto Silva de Freitas
     */
    public void prepararExclusao() {

        if (this.getForm().getAutoresSelecionados().length == 0) {

            this.getForm().setExibirDialogExclusao(Boolean.FALSE);

            this.addWarningMessage("Nenhum Autor foi selecionado!!");


        } else {
            this.getForm().setExibirDialogExclusao(Boolean.TRUE);
        }
    }

    /**
     * Método responsável por realizar  exclusão de autores selecionados pelo usuário
     * @author Cássio Augusto Silva de Freitas
     */
    public void removerAutoresSelecionados() {

        Collection<Autor> autores = new ArrayList<Autor>();
        
        for(int i= 0; i < this.getForm().getAutoresSelecionados().length; i++ ) {
          
            Autor autor =  this.getService().find(this.getForm().getAutoresSelecionados()[i].getId());
            
            autores.add(autor);
            
        }

        try {

            this.getService().removeAll(autores);

            this.addSuccessMessage("arquitetura.msg.sucesso");

            buscaTodosAutores();
            
            this.getForm().setExibirDialogExclusao(Boolean.FALSE);

        } catch (ValidationException ex) {

            this.addWarningMessage(ex.getKeyMessage());

        }

    }

    /**
     * Método responsável por editar um Autor e validar os campos do mesmo.
     * @author Cássio Augusto Silva de Freitas
     */
    public String editarAutor() {

        try {

            if ((this.getForm().getAutorEdicao().getNome() == null || this.getForm().getAutorEdicao().getNome().equals("")) && (this.getForm().getAutorEdicao().getSobrenome() == null || this.getForm().getAutorEdicao().getSobrenome().equals(""))) {
            
                addWarningMessage("cadastro.autor.label.RNG012.nomeEsobreNome");

            } else if (this.getForm().getAutorEdicao().getNome() == null || this.getForm().getAutorEdicao().getNome().equals("")) {
                
                addWarningMessage("cadastro.autor.label.RNG012.nome");

            } else if (this.getForm().getAutorEdicao().getSobrenome() == null || this.getForm().getAutorEdicao().getSobrenome().equals("")) {
             
                addWarningMessage("cadastro.autor.label.RNG012.sobrenome");

            } else {
                          
                Autor autor = this.getService().find(this.getForm().getAutorEdicao().getId());
             
                autor.setNome(this.getForm().getAutorEdicao().getNome());
              
                autor.setSobrenome(this.getForm().getAutorEdicao().getSobrenome());            

                this.getService().editar(autor);

                this.addSuccessMessage("arquitetura.msg.sucesso");
            }
        } catch (ValidationException ex) {

            this.addWarningMessage(ex.getKeyMessage());

        }
        
        return this.openInitialPage();
        
    }

    @Override
    public AutorForm getForm() {

        return this.form;
    }

    @Override
    public AutorService getService() {

        return this.service;
    }

    public void setForm(AutorForm form) {

        this.form = form;
    }

    public void setService(AutorService service) {

        this.service = service;
    }

    /**
     * Método responsável por inicializar a Entidade
     * @author Cássio Augusto Silva de Freitas
     */
    private void limpaEntidadeDeCadastro() {

        this.getForm().setEntity(new Autor());

    }
}
