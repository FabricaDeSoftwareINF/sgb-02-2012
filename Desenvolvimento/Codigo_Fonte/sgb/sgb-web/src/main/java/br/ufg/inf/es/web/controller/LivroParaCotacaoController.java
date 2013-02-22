package br.ufg.inf.es.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.ItemListaCompraService;
import br.ufg.inf.es.model.ItemListaCompras;
import br.ufg.inf.es.web.controller.form.ItemListaCompraForm;

/**
 *
 * @author Victor Carvalho
 */
@Component
@Scope("session")
public class LivroParaCotacaoController extends SGBController<ItemListaCompras, ItemListaCompraForm, ItemListaCompraService> {

    @Autowired
    private ItemListaCompraService livroParaCotacaoService;
    @Autowired
    private ItemListaCompraForm form;
    private String parametroMec;

    public String getParametroMec() {
        return parametroMec;
    }
    
    public void setParametroMec(String parametroMec) {
		
    	this.parametroMec = parametroMec;
	}

	@Override
    public ItemListaCompraService getService() {
        return livroParaCotacaoService;
    }

    @Override
    public ItemListaCompraForm getForm() {
        return this.form;
    }

    @Override
    public void initData() {
        super.initData();
        try {
            Collection<ItemListaCompras> entidades = this.getService().obtemLivrosParaCotacao();
            this.getForm().setTodosLivros(entidades);

            this.getForm().setCollectionEntities(entidades);
        } catch (ValidationException ve) {
            this.addErrorMessage(ve.getKeyMessage());
        }
    }
}
