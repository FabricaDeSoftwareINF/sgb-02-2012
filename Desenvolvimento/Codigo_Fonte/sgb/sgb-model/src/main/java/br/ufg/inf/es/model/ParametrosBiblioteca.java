package br.ufg.inf.es.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Victor Carvalho
 */
@Entity
@Table(name = "PARAMETROS_BIBLIOTECA")
public class ParametrosBiblioteca extends AbstractEntityModel {

    private final static int VALOR_HASH = 3;
    
    private final static int SALTO = 67;
    
    @Column(name = "valor_frete", columnDefinition = "Decimal(10,2) default '30.00'")
    private BigDecimal valorFrete = new BigDecimal("30.00");

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParametrosBiblioteca other = (ParametrosBiblioteca) obj;
        if (this.valorFrete != other.valorFrete && (this.valorFrete == null || !this.valorFrete.equals(other.valorFrete))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = ParametrosBiblioteca.VALOR_HASH;
        hash = ParametrosBiblioteca.SALTO * hash + (this.valorFrete != null ? this.valorFrete.hashCode() : 0);
        return hash;
    }
}
