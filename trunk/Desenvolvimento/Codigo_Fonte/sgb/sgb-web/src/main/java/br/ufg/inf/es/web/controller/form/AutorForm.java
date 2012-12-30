package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Autor;
import java.util.Collection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Cézar Augusto Ferreira
 */
@Component
@Scope("session")
public class AutorForm extends GenericForm<Autor> {
    private Collection<Autor> autoresSelecionados;
    private Collection<String> nomesSelecionados;

    public Collection<Autor> getAutoresSelecionados() {
        return autoresSelecionados;
    }

    public void setAutoresSelecionados(Collection<Autor> autoresSelecionados) {
        this.autoresSelecionados = autoresSelecionados;
    }

    public Collection<String> getNomesSelecionados() {
        return nomesSelecionados;
    }

    public void setNomesSelecionados(Collection<String> nomesSelecionados) {
        this.nomesSelecionados = nomesSelecionados;
    }
        
}