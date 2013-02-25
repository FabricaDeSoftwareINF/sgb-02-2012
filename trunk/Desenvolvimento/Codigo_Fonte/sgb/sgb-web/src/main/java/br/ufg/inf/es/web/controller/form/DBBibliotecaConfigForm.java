package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.base.persistence.biblioteca.DBDriver;
import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe que representa a entidade de parâmetro de Integralção com a Biblioteca
 *
 * @author igor
 */
@Component
@Scope("session")
public class DBBibliotecaConfigForm extends GenericForm<DBBibliotecaConfig> {

    private Boolean exibirDialogExclusao;
    private DBDriver driver;

    /**
     * Obtem os drivers
     *
     * @return
     */
    public DBDriver[] getDrivers() {
        return DBDriver.values();
    }

    /**
     * Obtem o valor para exibir o dialog de exclusao
     *
     * @return
     */
    public Boolean getExibirDialogExclusao() {
        return exibirDialogExclusao;
    }

    /**
     * Define um novo valor para a flag
     *
     * @param exibirDialogExclusao
     */
    public void setExibirDialogExclusao(Boolean exibirDialogExclusao) {
        this.exibirDialogExclusao = exibirDialogExclusao;
    }

    /**
     * Obtem o driver
     *
     * @return
     */
    public DBDriver getDriver() {
        return driver;
    }

    /**
     * Define um novo driver
     *
     * @param driver
     */
    public void setDriver(DBDriver driver) {
        this.driver = driver;
    }
}
