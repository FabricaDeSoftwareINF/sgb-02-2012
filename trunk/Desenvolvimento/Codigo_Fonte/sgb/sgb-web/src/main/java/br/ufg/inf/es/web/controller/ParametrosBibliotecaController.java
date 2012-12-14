package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.ParametrosBibliotecaService;
import br.ufg.inf.es.model.ParametrosBiblioteca;
import br.ufg.inf.es.web.controller.form.ParametrosBibliotecaForm;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope
public class ParametrosBibliotecaController
        extends SGBController<ParametrosBiblioteca, ParametrosBibliotecaForm, ParametrosBibliotecaService> {

    @Autowired
    private ParametrosBibliotecaForm form;
    @Autowired
    private ParametrosBibliotecaService service;

    @Override
    public ParametrosBibliotecaForm getForm() {
        return this.form;
    }

    @Override
    public ParametrosBibliotecaService getService() {
        return this.service;
    }

    @Override
    public void edit() {
        try {
            ParametrosBiblioteca parametro = this.getForm().getEntity();
            Hibernate.initialize(parametro);
            this.getService().update(parametro);

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Sucesso", "Valor atualizado com sucesso!"));
        } catch (ValidationException ex) {
            this.addWarningMessage(ex.getKeyMessage());
        }
    }

    @Override
    public String openViewPage() {
        try {
            ParametrosBiblioteca parametros = service.find();
            this.getForm().setEntity(parametros);
            return this.getRootNavigation() + "viewPage";
        } catch (Exception e) {
        }
        return "";
    }
}
