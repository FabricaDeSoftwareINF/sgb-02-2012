
package br.ufg.inf.es.persistencia.biblioteca;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;
import br.ufg.inf.es.persistencia.GenericHibernateDAO;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe de percistencia das configurações da integração com sistema da biblioteca
 * @author igor
 */
@Component
@Transactional(rollbackFor=ValidationException.class)
public class DBBibliotecaConfigDAO extends GenericHibernateDAO<DBBibliotecaConfig>{
    
    /** Campo sessionFactory*/
    @Autowired
    private SessionFactory sessionFactory;

    /** 
     * {@inheritDoc} 
     */
    @Override
    protected SessionFactory getSessionFactory() {

        return this.sessionFactory;
    }

    /** 
     * {@inheritDoc} 
     */
    @Override
    protected void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Método responsável por obter uma instância dos parâmetros de configuração
     * de conexão com o banco de dados da Biblioteca
     *
     * @author Igor
     * @return configurador do banco de dados da biblioteca
     */
    public DBBibliotecaConfig getBibliotecaCfg() {
        Criteria criteria = this.createCriteria();
        List results = criteria.list();
        if (results != null && !results.isEmpty()) {
            return (DBBibliotecaConfig) results.get(0);
        }
        return new DBBibliotecaConfig();
    }
}
