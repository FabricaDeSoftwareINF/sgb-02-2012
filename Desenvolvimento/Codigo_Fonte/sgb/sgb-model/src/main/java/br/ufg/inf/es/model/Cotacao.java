package br.ufg.inf.es.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entidade Cotacao
 *
 * @author Marquete
 */
@Entity
@Table(name = "COTACAO")
public class Cotacao extends AbstractEntityModel {

    private static final int HASH = 5;
    private static final int SALTO = 67;
    private static final int VALOR_BITS = 32;
    /**
     * Campo valor
     */
    private double valor;
    /**
     * Campo livraria
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Livraria livraria;

    /**
     * Obtém o valor do campo
     * <code>valor</code>
     *
     * @return {@link double}
     */
    public double getValor() {
        return this.valor;
    }

    /**
     * Define o campo
     * <code>valor</code>.
     *
     * @param valor
     */
    public void setValor(double valor) {

        this.valor = valor;
    }

    /**
     * Obtém o valor do campo
     * <code>livraria</code>
     *
     * @return {@link Livraria}
     */
    public Livraria getLivraria() {

        return this.livraria;
    }

    /**
     * Define o campo
     * <code>livraria</code>.
     *
     * @param livraria
     */
    public void setLivraria(Livraria livraria) {

        this.livraria = livraria;
    }

    @Override
    public int hashCode() {
        int hash = HASH;
        hash = SALTO * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> VALOR_BITS));
        hash = SALTO * hash + (this.livraria != null ? this.livraria.hashCode() : 0);
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
        final Cotacao other = (Cotacao) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (this.livraria != other.livraria && (this.livraria == null || !this.livraria.equals(other.livraria))) {
            return false;
        }
        return true;
    }
}
