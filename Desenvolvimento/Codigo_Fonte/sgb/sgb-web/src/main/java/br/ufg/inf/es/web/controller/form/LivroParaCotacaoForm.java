package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.dtos.LivroParaCotacao;
import br.ufg.inf.es.web.datamodel.LivroParaCotacaoDataModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Victor Carvalho
 */
@Component
@Scope("session")
public class LivroParaCotacaoForm extends GenericForm<LivroParaCotacao> {

    private Collection<LivroParaCotacao> todosLivros;
    private LivroParaCotacaoDataModel livroDM;
    
    public Collection<LivroParaCotacao> getTodosLivros() {
        
        return todosLivros;
    }

    public void setTodosLivros(Collection<LivroParaCotacao> todosLivros) {
        
        this.todosLivros = todosLivros;
    }
    
    public LivroParaCotacaoDataModel getLivroDataModel() {
        
        List<LivroParaCotacao> livros =  new ArrayList<LivroParaCotacao>(this.getTodosLivros());
        
        livroDM = new LivroParaCotacaoDataModel(livros);
        
        return livroDM;
    }

    public void setLivroDataModel(LivroParaCotacaoDataModel livroDM) {
        this.livroDM = livroDM;
    }
}
