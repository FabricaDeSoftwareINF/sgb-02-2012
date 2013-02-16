/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * Entidade Cotacao
 *
 * @author Marquete
 */
@Entity
@Table(name = "COTACAO")
public class Cotacao extends AbstractEntityModel {

    /**
     * Campo valor
     */
    private double valor;

    /**
     * Campo livraria
     */
    @OneToOne
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

}
