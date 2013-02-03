/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.persistence.biblioteca.DBDriver;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.DBBibliotecaConfigService;
import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;
import br.ufg.inf.es.web.controller.form.DBBibliotecaConfigForm;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author igor
 */
@Component
@Scope("session")
public class BibliotecaConfigController extends SGBController<DBBibliotecaConfig, DBBibliotecaConfigForm, DBBibliotecaConfigService> {

    @Autowired
    private DBBibliotecaConfigForm form;
    @Autowired
    private DBBibliotecaConfigService service;

    @Override
    public String openInitialPage() {
        DBBibliotecaConfig config = service.getBibliotecaCfg();
        if (config != null) {

            form.setEntity(config);

        }
        return "BibliotecaConfigController/initialPage";
    }

    public void setForm(DBBibliotecaConfigForm form) {
        this.form = form;
    }

    @Override
    public DBBibliotecaConfigForm getForm() {
        return form;
    }

    public void setService(DBBibliotecaConfigService service) {
        this.service = service;
    }

    @Override
    public DBBibliotecaConfigService getService() {
        return service;
    }

    public void limpar() {
        this.form = new DBBibliotecaConfigForm();
    }

    public String salvarDBBibliotecaConfig() {

        try {
            if (this.getForm().getEntity() == null || 
                    this.getForm().getEntity().getDriver() == null) {
                this.service.insert(this.form.getEntity());
                this.addSuccessMessage("arquitetura.msg.sucesso");
            } else {
                this.service.editar(this.form.getEntity());
                this.addSuccessMessage("arquitetura.msg.sucesso");
            }
        } catch (ValidationException ex) {

            this.addWarningMessage(ex.getKeyMessage());

        }
        return super.openSearchPage();

    }

    public String editarDBBibliotecaConfig() {

        super.edit();

        return super.openInitialPage();
    }

    public void prepararExclusao() {

        if (this.getForm().getEntity() != null && 
                this.getForm().getEntity().getDriver() != null) {
            this.getForm().setExibirDialogExclusao(Boolean.TRUE);
        } else {
            this.getForm().setExibirDialogExclusao(Boolean.FALSE);
            this.addWarningMessage("arquitetura.msg.nenhumregistroencontrado");
        }
    }

    public void remover() {
        this.service.getDAO().remove(form.getEntity());
        this.addSuccessMessage("arquitetura.msg.sucesso");
    }

    public String voltar() {
        return "/index.jsf";

    }
}
