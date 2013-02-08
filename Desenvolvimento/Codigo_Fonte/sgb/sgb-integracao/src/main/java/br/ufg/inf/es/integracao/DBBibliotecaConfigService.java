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
 * Classe de serviço para as operações de configuração dos parâmetros de 
 * integração com sistema da Biblioteca
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
     * Método responsável por buscar a configuração de integração com o banco da
     * biblioteca.
     * @author Igor
     * @return objeto de parâmetros de configuração de integração com o banco da 
     * biblioteca
     */
    public DBBibliotecaConfig getBibliotecaCfg() {

        return this.getDAO().getBibliotecaCfg();

    }

    /**
     * Método responsável por realizar a inserção das configurações de comunicação
     * com o banco de dados da biblioteca
     * @author Igor
     * @param dbBibliotecaConfig instancia das classe dos parâmetros de conexão 
     * com banco de dados da biblioteca
     * @return id da nova entidade
     * @throws ValidationException validação da instancia do objeto de coinfiguração
     * de conexão com banco de dados da biblioteca
     */
    @Override
    @RNG018
    public Long insert(DBBibliotecaConfig dbBibliotecaConfig) throws ValidationException {

        return this.getDAO().insert(dbBibliotecaConfig);
    }

    /**
     * Método reponsável por realizar a edição das configurações de integração 
     * com o banco de dados da biblioteca.
     * @author Igor
     * @param entidade
     * @throws ValidationException 
     */
    @RNG018
    public void editar(DBBibliotecaConfig entidade) throws ValidationException {
     
        this.getDAO().update(entidade);        
    }
}
