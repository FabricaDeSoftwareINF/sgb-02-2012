package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.base.model.Entity;
import br.ufg.inf.es.base.model.annotations.OrderingProperty;
import br.ufg.inf.es.base.model.annotations.SortOrder;
import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.base.util.UtilObjeto;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

/**
 * @author Cézar Augusto Ferreira
 */
public abstract class GenericHibernateDAO<E extends Entity<Long>> implements DAO<E, Long> {

    private Session session;

    protected abstract SessionFactory getSessionFactory();

    protected abstract void setSessionFactory(SessionFactory sessionFactory);

    protected Session getSession() {

        if (this.session == null || !this.session.isOpen()) {

            this.session = this.getSessionFactory().openSession();
        }

        return this.session;
    }

    protected Class<E> getClassEntity() {

        final Type type[] = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();

        return (Class<E>) type[0];
    }

    private void isReferencia(Object o) {

        if (!UtilObjeto.isReferencia(o)) {

            throw new IllegalArgumentException("ARGUMENTO INVÁLIDO!");
        }
    }

    public E find(Long id) {
        E objeto;
        try {

            objeto = (E) this.getSession().get(this.getClassEntity(), id);

        } finally {

            this.getSession().close();

        }
        return objeto;
    }

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

    public void update(E entidade) {
        try {
            isReferencia(entidade);

            this.getSession().merge(entidade);

            this.getSession().flush();

        } finally {

            this.getSession().close();
        }
    }

    public void save(E entidade) {
        try {
            isReferencia(entidade);

            this.getSession().persist(entidade);

            this.getSession().flush();
        } finally {
            this.getSession().close();
        }
    }

    public void remove(E entidade) {
        try {
            isReferencia(entidade);

            this.getSession().delete(entidade);

            this.getSession().flush();
        } finally {
            this.getSession().close();
        }
    }

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

        } finally {

            this.getSession().close();
        }
    }

    public Collection<E> list() {

        Collection<E> lista;

        Criteria criteria = this.createCriteria();

        this.addOrder(criteria);

        lista = criteria.list();

        return lista;
        
    }

    public void refresh(E entidade) {
        try {

            isReferencia(entidade);

            this.getSession().refresh(entidade);

        } finally {

            this.getSession().close();

        }
    }

    private Order transformerOrder(OrderingProperty order, String property) {

        return (SortOrder.ASC.equals(order.sortOrder())) ? Order.asc(property) : Order.desc(property);
    }

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

    private void addOrder(Criteria criteria) {

        Order order = this.buildOrdering(this.getClassEntity());

        if (order != null) {

            criteria.addOrder(order);
        }
    }
    
    public void closeSession() {
        
        try {

            this.session.close();

        } catch (Exception e) {

            Logger.getLogger(this.getClass().getSimpleName()).info(e.getMessage());
        }
    }
    

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
