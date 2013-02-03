/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG012;
import br.ufg.inf.es.integracao.annotations.RNG018;
import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;
import br.ufg.inf.es.persistencia.biblioteca.DBBibliotecaConfigDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author igor
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DBBibliotecaConfigService extends GenericService<DBBibliotecaConfig>{
    
    @Autowired
    private DBBibliotecaConfigDAO dao;

    @Override
    public DBBibliotecaConfigDAO getDAO() {

        return this.dao;
    }

    public void setDao(DBBibliotecaConfigDAO dao) {

        this.dao = dao;
    }

    /**
     * Método responsável por buscar todos os autores do Banco de dados
     * @author Cássio Augusto
     * @return Coleção de Autores
     */
    public DBBibliotecaConfig getBibliotecaCfg() {

        return this.getDAO().getBibliotecaCfg();

    }

    /**
     * Método responsável por realizar a inserção de um novo Autor
     * @author Cássio Augusto Silva de Freitas
     * @param dbBibliotecaConfig a ser inserido
     * @return id da nova entidade
     * @throws ValidationException 
     */
    @Override
    @RNG018
    public Long insert(DBBibliotecaConfig dbBibliotecaConfig) throws ValidationException {

        return this.getDAO().insert(dbBibliotecaConfig);

    }

    /**
     * Método reponsável por realizar a edição de um determinado autor
     * @author Cássio Augusto Silva de Freitas
     * @param entidade
     * @throws ValidationException 
     */
    @RNG018
    public void editar(DBBibliotecaConfig entidade) throws ValidationException {
     
        this.getDAO().update(entidade);
        
    }
}
