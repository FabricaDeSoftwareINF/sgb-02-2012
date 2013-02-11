package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.base.model.Entity;
import br.ufg.inf.es.base.model.annotations.OrderingProperty;
import br.ufg.inf.es.base.model.annotations.SortOrder;
import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.base.util.UtilObjeto;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * Classe que define um DAO genérico para ser utilizado com o framework Hibernate.
 * @author Cézar Augusto Ferreira
 */
public abstract class GenericHibernateDAO<E extends Entity<Long>> implements DAO<E, Long> {

    /** Campo session*/
    private Session session;

    /**
     * Método que obtém a fábrica de sessões do Hibernate.
     *
     * @return SessionFactory
     */
    protected abstract SessionFactory getSessionFactory();

    /**
     * Método que define a fábrica de sessões do Hibernate.
     *
     * @param sessionFactory
     */
    protected abstract void setSessionFactory(SessionFactory sessionFactory);

    /**
     * Método que obtém a sessão do Hibernate.
     *
     * @return A sessão obtida.
     */
    protected Session getSession() {

        if (this.session == null || !this.session.isOpen()) {

            this.session = this.getSessionFactory().openSession();
        }

        return this.session;
    }

    /**
     * Método que retorna a classe da Entidade.
     *
     * @return A classe da Entidade.
     */
    protected Class<E> getClassEntity() {

        final Type type[] = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();

        return (Class<E>) type[0];
    }

    /**
     * Método que verifica se o argumento é uma referência.
     *
     * @param o
     */
    protected void isReferencia(Object o) {

        if (!UtilObjeto.isReferencia(o)) {

            throw new IllegalArgumentException("ARGUMENTO INVÁLIDO!");
        }
    }

    /** 
     * {@inheritDoc} 
     */
    public E find(Long id) {
    	
        E objeto;
        
        try {

            objeto = (E) this.getSession().get(this.getClassEntity(), id);

        } finally {

            this.getSession().close();

        }
        return objeto;
    }

    /** 
     * {@inheritDoc} 
     */
    public Long insert(E entidade) {
    	
        Long id;
        
        try {

            isReferencia(entidade);

            id = (Long) this.getSession().save(entidade);

            this.getSession().flush();

        } finally {
            this.getSession().close();
        }
        return id;
    }

    /** 
     * {@inheritDoc} 
     */
    public void update(E entidade) {
    	
        try {
        	
            isReferencia(entidade);

            this.getSession().merge(entidade);

            this.getSession().flush();

        } finally {

            this.getSession().close();
        }
    }

    /** 
     * {@inheritDoc} 
     */
    public void save(E entidade) {
    	
        try {
            isReferencia(entidade);

            this.getSession().persist(entidade);

            this.getSession().flush();
        } finally {
            this.getSession().close();
        }
    }

    /** 
     * {@inheritDoc} 
     */
    public void remove(E entidade) {
        try {
            isReferencia(entidade);

            this.getSession().delete(entidade);

            this.getSession().flush();
            
        } finally {
        	
            this.getSession().close();
        }
    }

    /** 
     * {@inheritDoc} 
     */
    public void removeAll(Collection<E> entidades) {
        
    	try {

            isReferencia(entidades);

            for (E entidade : entidades) {

                this.remove(entidade);
            }

            this.getSession().flush();

        } finally {

            this.getSession().close();

        }
    }

    /** 
     * {@inheritDoc} 
     */
    public Collection<E> search(E entidade) {
    	
        Collection<E> lista;
        
        try {
        	
            Example example = Example.create(entidade);

            example.enableLike();

            example.excludeZeroes();

            example.excludeNone();

            lista = this.createCriteria().add(example).list();

        } finally {

            this.getSession().close();

        }

        return lista;
    }

    /**
     * Método que cria uma criteria com a entidade da classe.
     *
     * @return A criteria criada.
     */
    protected Criteria createCriteria() {

        return this.getSession().createCriteria(this.getClassEntity());
    }

    /**
     * Método responsável por iniciaalizar uma coleção da Entidade
     *
     * @param <T>
     * @param id
     * @param property
     * @return
     */
    public Collection<?> getCollection(Long id, String property) {

        try {

            final Collection<?> result = (Collection) PropertyUtils.getProperty(this.getSession().get(this.getClassEntity(), id), property);

            Hibernate.initialize(result);

            return result;

        } catch (Exception ex) {

            Logger.getLogger(GenericHibernateDAO.class.getName()).log(Level.SEVERE, null, ex);

            return Collections.EMPTY_LIST;

        }
        
    }

    /** 
     * {@inheritDoc} 
     */
    public Collection<E> list() {

        Collection<E> lista;

        Criteria criteria = this.createCriteria();

        this.addOrder(criteria);

        lista = criteria.list();

        return lista;

    }

    /** 
     * {@inheritDoc} 
     */
    public Collection<E> search(String key, String... properties) {

        if (properties == null || properties.length == 0) {
            throw new IllegalArgumentException("properties must not be null");
        }

        Collection<E> lista;

        key = key.trim();

        if (key.isEmpty()) {
            return null;
        }

        Criteria criteria = this.createCriteria();

        List<Criterion> criterionOrs = new ArrayList<Criterion>();

        Criterion criterion = null;

        for (int i = 0; i < properties.length; i++) {
            String[] keys = key.split(" ");
            Criterion criterionOr = Restrictions.ilike(properties[i], keys[0], MatchMode.ANYWHERE);
            for (int k = 1; k < keys.length; k++) {
                criterionOr = Restrictions.or(criterionOr,
                        Restrictions.ilike(properties[i], keys[k].trim(), MatchMode.ANYWHERE));
            }
            criterionOrs.add(criterionOr);
        }

        if (key.split(" ").length > 1) {
            criterion = Restrictions.isNotNull(properties[0]);
        } else {
            criterion = Restrictions.isNull(properties[0]);
        }

        for (Criterion ors : criterionOrs) {
            if (key.split(" ").length > 1) {
                criterion = Restrictions.and(criterion, ors);
            } else {
                criterion = Restrictions.or(criterion, ors);
            }
        }

        criteria.add(criterion);

        this.addOrder(criteria);

        lista = criteria.list();

        return lista;

    }

    /** 
     * {@inheritDoc} 
     */
    public void refresh(E entidade) {
        try {

            isReferencia(entidade);

            this.getSession().refresh(entidade);

        } finally {

            this.getSession().close();

        }
    }

    /**
     * Método que retornar a ordenação para determinada propriedade.
     *
     * @param order
     * @param property
     * @return O tipo de ordenação.
     */
    private Order transformerOrder(OrderingProperty order, String property) {

        return (SortOrder.ASC.equals(order.sortOrder())) ? Order.asc(property) : Order.desc(property);
    }

    /**
     * Método que cria a ordenação para o tipo de classe.
     *
     * @param clazzEntity
     * @return Order
     */
    private Order buildOrdering(Class<?> clazzEntity) {

        Order queryOrdering = null;

        searchOrderingProperty:
        for (Field field : clazzEntity.getDeclaredFields()) {

            if (field.isAnnotationPresent(OrderingProperty.class)) {

                OrderingProperty order = field.getAnnotation(OrderingProperty.class);

                queryOrdering = this.transformerOrder(order, field.getName());

                break searchOrderingProperty;
            }
        }

        return ((queryOrdering != null) ? queryOrdering : ((Object.class.equals(clazzEntity)) ? null : this.buildOrdering(clazzEntity.getSuperclass())));
    }

    /**
     * Método que adiciona um tipo de ordenação para um criteria.
     *
     * @param criteria
     */
    private void addOrder(Criteria criteria) {

        Order order = this.buildOrdering(this.getClassEntity());

        if (order != null) {

            criteria.addOrder(order);
        }
    }

    /**
     * Método que encerra a sessão.
     *
     */
    public void closeSession() {

        try {

            this.session.close();

        } catch (Exception e) {

            Logger.getLogger(this.getClass().getSimpleName()).info(e.getMessage());
        }
    }

    /** 
     * {@inheritDoc} 
     */
    @Override
    protected void finalize() throws Throwable {

        try {

            this.session.close();

        } catch (Exception e) {

            Logger.getLogger(this.getClass().getSimpleName()).info(e.getMessage());
        }

        super.finalize();
    }
}
