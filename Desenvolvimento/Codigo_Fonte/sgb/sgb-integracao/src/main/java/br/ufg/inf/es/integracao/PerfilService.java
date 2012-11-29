/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.model.Perfil;
import br.ufg.inf.es.persistencia.PerfilDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author Luã
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PerfilService extends GenericService<Perfil> {

    @Autowired
    private PerfilDAO dao;

    @Override
    public PerfilDAO getDAO() {

        return this.dao;
    }

    public void setDao(PerfilDAO dao) {

        this.dao = dao;
    }
}
