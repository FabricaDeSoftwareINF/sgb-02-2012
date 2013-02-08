
package br.ufg.inf.es.persistencia.biblioteca;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;
import br.ufg.inf.es.persistencia.GenericHibernateDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe de percistencia das configurações da integração com sistema da biblioteca
 * @author igor
 */
@Repository
@Transactional(rollbackFor=ValidationException.class)
public class DBBibliotecaConfigDAO extends GenericHibernateDAO<DBBibliotecaConfig>{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected SessionFactory getSessionFactory() {

        return this.sessionFactory;
    }

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

        Criteria criteria = this.getSession().createCriteria(DBBibliotecaConfig.class);

        return (DBBibliotecaConfig) criteria.uniqueResult();
    }
}
