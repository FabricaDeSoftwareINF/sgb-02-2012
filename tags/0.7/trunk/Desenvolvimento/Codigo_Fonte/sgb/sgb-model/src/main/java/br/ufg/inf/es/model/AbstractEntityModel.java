package br.ufg.inf.es.model;

import br.ufg.inf.es.base.model.Entity;
import br.ufg.inf.es.base.model.annotations.OrderingProperty;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Entidade abstrata para o projeto sgb
 *
 * @author Cézar Augusto Ferreira
 */
@MappedSuperclass
public class AbstractEntityModel implements Entity<Long> {

    private static final int VALOR_HASH = 5;
    
    private static final int SALTO = 71;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderingProperty
    private Long id;

    public Long getId() {

        return this.id;
    }

    public void setId(final Long id) {

        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = AbstractEntityModel.VALOR_HASH;
        
        hash = AbstractEntityModel.SALTO * hash + (this.id != null ? this.id.hashCode() : 0);
        
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractEntityModel other = (AbstractEntityModel) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public boolean isNew() {
        
        return this.getId() == null;
    }
}