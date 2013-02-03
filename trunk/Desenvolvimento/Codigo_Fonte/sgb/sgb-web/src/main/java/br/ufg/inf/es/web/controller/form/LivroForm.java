
package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Bibliografia;
import br.ufg.inf.es.model.Livro;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * @author cezar
 */
@Component
@Scope("session")
public class LivroForm extends GenericForm<Livro> {
    
    private Bibliografia bibliografiaTemp = new Bibliografia();
    
    public Bibliografia getBibliografiaTemp() {
        return bibliografiaTemp;
    }

    public void setBibliografiaTemp(Bibliografia bibliografiaTemp) {
        this.bibliografiaTemp = bibliografiaTemp;
    } 
}
