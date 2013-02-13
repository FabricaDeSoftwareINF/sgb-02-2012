/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

/**
 * Data Transfer Object da Entidade Autor.
 * Está entidade serve para manipularmos os dados vindo do Banco de dados sem 
 * trabalharmos diretamente com a Entidade
 * @author Cassio
 */
public class AutorDTO extends AbstractEntityModel {
    
    /** Campo nome*/
    private String nome;
    
    /** Campo sobrenome*/
    private String sobrenome;

	/**
	 * Obtém o valor do campo <code>nome</code>
	 *
	 * @return {@link String}
	 */
	public String getNome() {
		
		return this.nome;
	}

	/**
	 * Define o campo <code>nome</code>.
	 *
	 * @param nome 
	 */
	public void setNome(String nome) {
		
		this.nome = nome;
	}

	/**
	 * Obtém o valor do campo <code>sobrenome</code>
	 *
	 * @return {@link String}
	 */
	public String getSobrenome() {
		
		return this.sobrenome;
	}

	/**
	 * Define o campo <code>sobrenome</code>.
	 *
	 * @param sobrenome 
	 */
	public void setSobrenome(String sobrenome) {
		
		this.sobrenome = sobrenome;
	}

}
