package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG001Parametros;
import br.ufg.inf.es.model.Comunicacao;
import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;
import br.ufg.inf.es.persistencia.ComunicacaoDAO;
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
public class ComunicacaoService extends GenericService<Comunicacao> {
 
    @Autowired
    private ComunicacaoDAO dao;

    @Override
    public ComunicacaoDAO getDAO() {

        return this.dao;
    }
    
    /**
     * Método responsável por buscar os parâmetros de comunicação do sistema
     * @author Igor
     * @return objeto de parâmetros de comunicação
     */
    public Comunicacao getComunicacao() {

        return this.getDAO().getComunicacao();

    }

    /**
     * Método responsável por realizar a inserção dos parâmetros de comunicação
     * @author Igor
     * @param comunicacao instancia das classe dos parâmetros de comunicação
     * @return id da nova entidade
     * @throws ValidationException validação da instancia do objeto de coinfiguração
     * de comunicação
     */
    @Override
    @RNG001Parametros
    public Long insert(Comunicacao comunicacao) throws ValidationException {

        return this.getDAO().insert(comunicacao);
    }

    /**
     * Método reponsável por realizar a edição dos parâmetros de comunicação
     * @author Igor
     * @param entidade instância da classe dos parâmetros de comunicação
     * @throws ValidationException validação da instancia do objeto de coinfiguração
     * de comunicação
     */
    @RNG001Parametros
    public void editar(Comunicacao entidade) throws ValidationException {
     
        this.getDAO().update(entidade);        
    }
}
