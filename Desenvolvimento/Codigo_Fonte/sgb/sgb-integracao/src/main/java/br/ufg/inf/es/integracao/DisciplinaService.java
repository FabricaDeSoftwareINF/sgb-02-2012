/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.persistencia.DisciplinaDAO;
import br.ufg.inf.es.persistencia.LivroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author cezar
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DisciplinaService extends GenericService<Disciplina> {

    @Autowired
    private DisciplinaDAO dao;

    @Override
    public DisciplinaDAO getDAO() {
        return this.dao;
    }

    public void setDao(DisciplinaDAO dao) {

        this.dao = dao;
    }
}
