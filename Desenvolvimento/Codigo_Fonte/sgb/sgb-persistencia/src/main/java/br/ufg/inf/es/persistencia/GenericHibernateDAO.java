package br.ufg.inf.es.persistencia;

import br.ufg.inf.es.base.model.Entity;
import br.ufg.inf.es.base.model.annotations.OrderingProperty;
import br.ufg.inf.es.base.model.annotations.SortOrder;
import br.ufg.inf.es.base.persistence.DAO;
import br.ufg.inf.es.base.util.UtilObjeto;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

/**
 * @author Cézar Augusto Ferreira
 */
public abstract class GenericHibernateDAO<E extends Entity<Long>> implements DAO<E, Long> {

    private Session session;

    protected abstract SessionFactory getSessionFactory();

    protected Session getSession() {

        if (this.session == null || !this.session.isOpen()) {

            this.session = this.getSessionFactory().openSession();
        }

        return this.session;
    }

    protected Class<E> getClassEntity() {

        final Type type[] = ((ParameterizedTypeImpl) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments();

        return (Class<E>) type[0];
    }

    private void isReferencia(Object o) {

        if (!UtilObjeto.isReferencia(o)) {

            throw new IllegalArgumentException("ARGUMENTO INVÁLIDO!");
        }
    }

    public E find(Long id) {

        return (E) this.getSession().get(this.getClassEntity(), id);
    }

    public Long insert(E entidade) {

        isReferencia(entidade);

        return (Long) this.getSession().save(entidade);
    }

    public void update(E entidade) {

        isReferencia(entidade);

        this.getSession().merge(entidade);
        
        this.getSession().flush();
    }

    public void save(E entidade) {

        isReferencia(entidade);

        this.getSession().persist(entidade);
        
        this.getSession().flush();
    }

    public void remove(E entidade) {

        isReferencia(entidade);

        this.getSession().delete(entidade);
        
        this.getSession().flush();
    }

    public void removeAll(Collection<E> entidades) {

        isReferencia(entidades);

        for (E entidade : entidades) {

            this.remove(entidade);
        }
    }

    public Collection<E> search(E entidade) {

        Example example = Example.create(entidade);

        example.enableLike();

        example.excludeZeroes();

        example.excludeNone();

        return this.createCriteria().add(example).list();
    }

    protected Criteria createCriteria() {

        return this.getSession().createCriteria(this.getClassEntity());
    }

    public Collection<E> list() {

        Criteria criteria = this.createCriteria();

        this.addOrder(criteria);

        return criteria.list();
    }

    public void refresh(E entidade) {

        isReferencia(entidade);

        this.getSession().refresh(entidade);
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