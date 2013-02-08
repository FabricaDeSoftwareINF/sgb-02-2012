package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.*;
import br.ufg.inf.es.integracao.exportacaodados.MarcParser;
import br.ufg.inf.es.model.AutorDTO;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.persistencia.LivroDAO;
import br.ufg.inf.es.web.controller.form.ListaComprasForm;
import br.ufg.inf.es.web.controller.form.LivroForm;
import br.ufg.inf.es.web.datamodel.LivroDataModel;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jackeline Neves
 */
@Component
@Scope("session")
public class ListaComprasController extends SGBController<ListaCompras, ListaComprasForm, ListaComprasService> {

    @Autowired
    private ListaComprasForm form;
    @Autowired
    private ListaComprasService service;
    @Autowired
    private LivroService livroService;
    
    private LivroDataModel livroDataModel;
      
    private Livro[] livrosSelecionados;


    /**
     * Método responsável por retornar a string de navegação para a pagina incial da Estória de usuário
     * buscar todos os livros.
     * @return  string de navegação
     */
    @Override
    public String openInitialPage() {
        
        this.getForm().setListaCompras(this.service.list());
        this.livroDataModel = new LivroDataModel( (List) livroService.list());
        
        this.getService().carregarLivrosDaListaCompras(this.getForm().getListaCompras());
        this.getForm().setTodosLivros(new ArrayList<Livro>());
        buscaTodosLivros();

        return super.openInitialPage();
    }

    @Override
    public ListaComprasForm getForm() {
        return this.form;
    }

    @Override
    public ListaComprasService getService() {
        return this.service;
    }
    
    public LivroDataModel getLivroDataModel() {
        return livroDataModel;
    }
    
    /**
     * @author Jackeline
     */
    public void buscaTodosLivros(){
        LivroDAO livroDao = livroService.getDAO();
        Collection<Livro> todosLivros = livroDao.list();
        this.getForm().setTodosLivros(todosLivros);
        this.getForm().setFiltroTitulo("");
    }
 

      public Livro[] getLivrosSelecionados() {
        return livrosSelecionados;
    }

    public void setLivrosSelecionados(Livro[] livrosSelecionados) {
        this.livrosSelecionados = livrosSelecionados;
    }
}
