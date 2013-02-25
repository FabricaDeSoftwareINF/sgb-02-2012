/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Classe que representa a entidade perfil.
 * @author Luã
 */
@Entity
@Table(name = "PERFIL")
public class Perfil extends AbstractEntityModel {
    
    /** Campo tipo*/
    private String tipo;

    /**
     * Construtor desta classe.
     */
    public Perfil(){ }
    
    /**
     * Construtor desta classe.
     * @param tipo
     */
    public Perfil(String tipo){
        this.tipo = tipo;
    }

	/**
	 * Obtém o valor do campo <code>tipo</code>
	 *
	 * @return {@link String}
	 */
	public String getTipo() {
		return this.tipo;
	}

	/**
	 * Define o campo <code>tipo</code>.
	 *
	 * @param tipo 
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
}