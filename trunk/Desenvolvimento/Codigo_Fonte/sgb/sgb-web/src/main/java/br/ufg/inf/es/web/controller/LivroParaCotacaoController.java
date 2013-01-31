package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.LivroParaCotacaoService;
import br.ufg.inf.es.model.LivroParaCotacao;
import br.ufg.inf.es.web.controller.form.LivroParaCotacaoForm;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Victor Carvalho
 */
@Component
@Scope
public class LivroParaCotacaoController extends SGBController<LivroParaCotacao, LivroParaCotacaoForm, LivroParaCotacaoService> {

    @Autowired
    private LivroParaCotacaoService livroParaCotacaoService;
    @Autowired
    private LivroParaCotacaoForm form;
    
    private String parametroMec;

    public String getParametroMec() {
        return parametroMec;
    }

    @Override
    public LivroParaCotacaoService getService() {
        return livroParaCotacaoService;
    }

    @Override
    public LivroParaCotacaoForm getForm() {
        return this.form;
    }

    @Override
    public void initData() {
        super.initData();
        Collection<LivroParaCotacao> entidades = this.getService().obtenhaLivrosParaCotacao();
        this.getForm().setCollectionEntities(entidades);
        
        LivroParaCotacao primeiro = entidades.iterator().next();
        parametroMec = primeiro.getParametroMec() + "";
    }
}
