package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.AutorDTO;
import br.ufg.inf.es.web.datamodel.AutorDataModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Cézar Augusto Ferreira, Cássio Augusto
 */
@Component
@Scope("session")
public class AutorForm extends GenericForm<Autor> {
    
    private String filtroNome;
    
    private AutorDataModel model;
    
    private AutorDTO autorEdicao;
    
    private Long idAutorEdicao;
    
    private AutorDTO[] autoresSelecionados;
    
    private Boolean exibirDialogExclusao;
    
    private Collection<String> nomesSelecionados;
    
    /*Todos os autores*/
    private Collection<AutorDTO> todosAutores;

    public Boolean getExibirDialogExclusao() {
        return exibirDialogExclusao;
    }

    public void setExibirDialogExclusao(Boolean exibirDialogExclusao) {
        this.exibirDialogExclusao = exibirDialogExclusao;
    }
    
    public Long getIdAutorEdicao() {
        return idAutorEdicao;
    }

    public void setIdAutorEdicao(Long idAutorEdicao) {
        this.idAutorEdicao = idAutorEdicao;
    }
    
    public AutorDTO getAutorEdicao() {
        return autorEdicao;
    }

    public void setAutorEdicao(AutorDTO autorEdicao) {
        this.autorEdicao = autorEdicao;
    }

    public String getFiltroNome() {
        return filtroNome;
    }

    public void setFiltroNome(String filtroNome) {
        this.filtroNome = filtroNome;
    }

    public AutorDTO[] getAutoresSelecionados() {
        
        AutorDTO[] retorno = null;
        
        if(this.autoresSelecionados != null) {
            
             retorno = this.autoresSelecionados.clone();
        }
        
        return retorno;
    }

    public void setAutoresSelecionados(AutorDTO[] autoresSelecionados) {
        
        this.autoresSelecionados = (AutorDTO[]) autoresSelecionados.clone();
    }
    
    public AutorDataModel getModel() {
       
        List<AutorDTO> autores =  new ArrayList<AutorDTO>(this.getTodosAutores());
        
        model = new AutorDataModel(autores);
      
        return model;
    }

    public void setModel(AutorDataModel model) {
        this.model = model;
    }
    
    public Collection<AutorDTO> getTodosAutores() {
        return todosAutores;
    }

    public void setTodosAutores(Collection<AutorDTO> todosAutores) {
        this.todosAutores = todosAutores;
    }
   
    public Collection<String> getNomesSelecionados() {
        return nomesSelecionados;
    }

    public void setNomesSelecionados(Collection<String> nomesSelecionados) {
        this.nomesSelecionados = nomesSelecionados;
    }
        
}