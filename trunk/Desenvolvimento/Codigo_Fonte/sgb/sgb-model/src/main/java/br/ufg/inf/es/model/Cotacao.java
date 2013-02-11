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
 * @author Marquete
 */
@Entity
@Table(name = "COTACAO")
public class Cotacao extends AbstractEntityModel {

    /** Campo valor*/
    private double valor;
    
    /** Campo dataCadastro*/
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
    
    /** Campo livro*/
    @OneToOne
    private Livro livro;
    
    /** Campo livraria*/
    @OneToOne
    private Livraria livraria;
    
    /** Campo quantidade*/
    private int quantidade;

	/**
	 * Obtém o valor do campo <code>valor</code>
	 *
	 * @return {@link double}
	 */
	public double getValor() {
		
		return this.valor;
	}

	/**
	 * Define o campo <code>valor</code>.
	 *
	 * @param valor 
	 */
	public void setValor(double valor) {
		
		this.valor = valor;
	}

	/**
	 * Obtém o valor do campo <code>dataCadastro</code>
	 *
	 * @return {@link Date}
	 */
	public Date getDataCadastro() {
		
		return this.dataCadastro;
	}

	/**
	 * Define o campo <code>dataCadastro</code>.
	 *
	 * @param dataCadastro 
	 */
	public void setDataCadastro(Date dataCadastro) {
		
		this.dataCadastro = dataCadastro;
	}

	/**
	 * Obtém o valor do campo <code>livro</code>
	 *
	 * @return {@link Livro}
	 */
	public Livro getLivro() {
		
		return this.livro;
	}

	/**
	 * Define o campo <code>livro</code>.
	 *
	 * @param livro 
	 */
	public void setLivro(Livro livro) {
		
		this.livro = livro;
	}

	/**
	 * Obtém o valor do campo <code>livraria</code>
	 *
	 * @return {@link Livraria}
	 */
	public Livraria getLivraria() {
		
		return this.livraria;
	}

	/**
	 * Define o campo <code>livraria</code>.
	 *
	 * @param livraria 
	 */
	public void setLivraria(Livraria livraria) {
		
		this.livraria = livraria;
	}

	/**
	 * Obtém o valor do campo <code>quantidade</code>
	 *
	 * @return {@link int}
	 */
	public int getQuantidade() {
		
		return this.quantidade;
	}

	/**
	 * Define o campo <code>quantidade</code>.
	 *
	 * @param quantidade 
	 */
	public void setQuantidade(int quantidade) {
		
		this.quantidade = quantidade;
	}

}
