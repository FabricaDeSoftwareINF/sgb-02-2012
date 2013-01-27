package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.ParametrosService;
import br.ufg.inf.es.model.Parametros;
import br.ufg.inf.es.web.controller.form.ParametrosForm;
import java.math.BigDecimal;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope
public class ParametrosController
        extends SGBController<Parametros, ParametrosForm, ParametrosService> {

    @Autowired
    private ParametrosForm form;
    @Autowired
    private ParametrosService service;

    @Override
    public ParametrosForm getForm() {
        return this.form;
    }

    public void setForm(ParametrosForm form) {
        this.form = form;
    }

    public void setService(ParametrosService service) {
        this.service = service;
    }

    @Override
    public ParametrosService getService() {
        return this.service;
    }
    
    public BigDecimal getValorFrete() {  
        return this.form.getEntity().getValorFrete();  
    }  
  
    public void setValorFrete(BigDecimal text) {  
        this.edit();
    }  

    @Override
    public void edit() {
        try {
            Parametros parametro = this.getForm().getEntity();
            Hibernate.initialize(parametro);
            getService().save(parametro);
            addSuccessMessage("Valor atualizado com sucesso!");
        } catch (ValidationException ex) {
            addWarningMessage(ex.getKeyMessage());
        }
    }

    @Override
    public String openViewPage() {
        try {
            Parametros parametros = service.find();
            this.getForm().setEntity(parametros);
            return this.getRootNavigation() + "viewPage";
        } catch (Exception e) {
        }
        return "";
    }
}
