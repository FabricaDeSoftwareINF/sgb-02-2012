package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.LivroService;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.controller.form.LivroForm;
import java.util.ArrayList;
import java.util.Collection;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marquete
 */
@Component
@Scope("session")
public class TemplateController {

    @Autowired
    private LivroForm form;
    @Autowired
    private LivroService service;
    private Collection<Livro> livros;

    public TemplateController() {
        form = new LivroForm();
        service = new LivroService();
        form.setLivrosAdicionados(new ArrayList<Livro>());
        livros = new ArrayList<Livro>(form.getLivrosAdicionados());
    }

    @Autowired
    public LivroForm getForm() {
        return form;
    }

    public void setForm(LivroForm form) {
        this.form = form;
    }

    public LivroService getService() {
        return service;
    }

    public void setService(LivroService service) {
        this.service = service;
    }

    public  Collection<Livro> getLivros() {
        livros = new ArrayList<Livro>(form.getLivrosAdicionados());
        return livros;
    }
    
    public void setLivros (Collection<Livro> livros) {
        this.livros = livros;
    }
    
    public Collection<Livro> completeLivro(String query) {

        Collection<Livro> livrosAdicionados = this.getForm().getLivrosAdicionados();
        Collection<Livro> livros = new ArrayList<Livro>();

        if (livrosAdicionados.isEmpty()) {

            livros = this.service.searchByAttributes(query, "titulo");

            if (livrosAdicionados != null) {
                livros.removeAll(livrosAdicionados);
            }

        }
        return livros;
    }

    public void handleUnselectLivro(UnselectEvent event) {

        Livro livroSelecionado = (Livro) event.getObject();

        this.getForm().getLivrosAdicionados().remove(livroSelecionado);
    }

    public void addLivroOnSelect(SelectEvent event) {

        Livro livroSelecionado = (Livro) event.getObject();

        this.getForm().getLivrosAdicionados().add(livroSelecionado);
    }
}
