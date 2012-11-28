package br.ufg.inf.es.model;

import br.ufg.inf.es.base.model.annotations.OrderingProperty;
import br.ufg.inf.es.base.model.annotations.SortOrder;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author cézar Augusto Ferreira
 */
@Entity
@Table(name = "USUARIO")
public class Usuario extends AbstractEntityModel {

    @OrderingProperty(sortOrder = SortOrder.DESC)
    private String login;
    private String senha;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}