package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.base.persistence.biblioteca.DBDriver;
import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author igor
 */
@Component
@Scope("session")
public class DBBibliotecaConfigForm extends GenericForm<DBBibliotecaConfig>{
    
    private Boolean exibirDialogExclusao;
    
    public DBDriver[] getDrivers(){        
        return DBDriver.values();
    }
    
    public Boolean getExibirDialogExclusao() {
        return exibirDialogExclusao;
    }
    
     public void setExibirDialogExclusao(Boolean exibirDialogExclusao) {
        this.exibirDialogExclusao = exibirDialogExclusao;
    }
}
