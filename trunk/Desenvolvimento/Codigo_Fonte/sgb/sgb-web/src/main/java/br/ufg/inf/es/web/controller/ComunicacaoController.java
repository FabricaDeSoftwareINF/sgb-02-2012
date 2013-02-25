package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.util.cripto.CriptoGeneric;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.ComunicacaoService;
import br.ufg.inf.es.model.Comunicacao;
import br.ufg.inf.es.web.controller.form.ComunicacaoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author igor
 */

@Component
@Scope("session")
public class ComunicacaoController extends SGBController<Comunicacao, 
        ComunicacaoForm, ComunicacaoService> {
    
    @Autowired
    private ComunicacaoForm form;
    
    @Autowired
    private ComunicacaoService service;
        
    private String password;
    
    private boolean tslPage = false;
    
    private boolean sslPage = false;
    
    @Override
    public String openInitialPage() {
       Comunicacao comunicacao = service.getComunicacao();
        if (comunicacao != null) {

            form.setEntity(comunicacao);
            setSslPage(comunicacao.isSsl());
            setTslPage(comunicacao.isTsl());

        }
        
        return "ComunicacaoController/initialPage";
    }

    public void setForm(ComunicacaoForm form) {
        this.form = form;
    }

    @Override
    public ComunicacaoForm getForm() {
        return form;
    }

    public void setService(ComunicacaoService service) {
        this.service = service;
    }

    @Override
    public ComunicacaoService getService() {
        return service;
    }

    public void limpar() {
        this.form = new ComunicacaoForm();
    }

    public String salvarComunicacao() {

        try {
            if (this.getForm().getEntity().getId() == null) {
                this.form.getEntity().setSenha(new CriptoGeneric().criptografa(password));
                
                this.form.getEntity().setSsl(sslPage);
                this.form.getEntity().setTsl(tslPage);
                
                this.service.insert(this.form.getEntity());
                this.addSuccessMessage("arquitetura.msg.sucesso");
            } else {
                this.form.getEntity().setSenha(new CriptoGeneric().criptografa(password));
                this.service.editar(this.form.getEntity());
                this.addSuccessMessage("arquitetura.msg.sucesso");
            }
        } catch (ValidationException ex) {

            this.addWarningMessage(ex.getKeyMessage());

        }
        return super.openSearchPage();

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTslPage() {
        return tslPage;
    }

    public void setTslPage(boolean tslPage) {
        this.tslPage = tslPage;
    }

    public boolean isSslPage() {
        return sslPage;
    }

    public void setSslPage(boolean sslPage) {
        this.sslPage = sslPage;
    }
    
    public String voltar() {
        return "/index.jsf";

    }
}
