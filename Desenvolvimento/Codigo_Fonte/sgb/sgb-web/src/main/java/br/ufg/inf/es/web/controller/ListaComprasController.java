package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.*;
import br.ufg.inf.es.integracao.exportacaodados.MarcParser;
import br.ufg.inf.es.model.AutorDTO;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.ListaCompras;
import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.web.controller.form.ListaComprasForm;
import br.ufg.inf.es.web.controller.form.LivroForm;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
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


    /**
     * Método responsável por retornar a string de navegação para a pagina incial da Estória de usuário
     * buscar todos os livros.
     * @return  string de navegação
     */
    @Override
    public String openInitialPage() {
        
        this.getForm().setListaCompras(this.service.list());
        
        this.getService().carregarLivrosDaListaCompras(this.getForm().getListaCompras());

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

   

}
