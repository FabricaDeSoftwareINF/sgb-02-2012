package br.ufg.inf.es.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidade Comunicação
 *
 * @author igor
 */
@Entity
@Table(name = "COMUNICACAO_PARAMETROS")
public class Comunicacao extends AbstractEntityModel{
    
    /** Campo TAMANHO*/
    private static final int TAMANHO = 300;
    
    /** Campo service*/
    @Column(name="service", nullable=false)
    private String service;
    
    /** Campo ssl*/
    @Column(name="protocolo_ssl", nullable=false)
    private boolean ssl = false;
    
    /** Campo tsl*/
    @Column(name="protocolo_tsl", nullable=false)
    private boolean tsl = false;
    
    /** Campo port*/
    @Column(name="port", nullable=false)
    private String port;
    
    /** Campo usuario*/
    @Column(name="usuario", nullable=false)
    private String usuario;
    
    /** Campo senha*/
    @Column(name="senha", length=Comunicacao.TAMANHO,nullable=false)
    private byte[] senha;

	/**
	 * Obtém o valor do campo <code>service</code>
	 *
	 * @return {@link String}
	 */
	public String getService() {
		
		return this.service;
	}

	/**
	 * Define o campo <code>service</code>.
	 *
	 * @param service 
	 */
	public void setService(String service) {
		
		this.service = service;
	}

	/**
	 * Obtém o valor do campo <code>ssl</code>
	 *
	 * @return {@link boolean}
	 */
	public boolean isSsl() {
		
		return this.ssl;
	}

	/**
	 * Define o campo <code>ssl</code>.
	 *
	 * @param ssl 
	 */
	public void setSsl(boolean ssl) {
		
		this.ssl = ssl;
	}

	/**
	 * Obtém o valor do campo <code>tsl</code>
	 *
	 * @return {@link boolean}
	 */
	public boolean isTsl() {
		
		return this.tsl;
	}

	/**
	 * Define o campo <code>tsl</code>.
	 *
	 * @param tsl 
	 */
	public void setTsl(boolean tsl) {
		
		this.tsl = tsl;
	}

	/**
	 * Obtém o valor do campo <code>port</code>
	 *
	 * @return {@link String}
	 */
	public String getPort() {
		
		return this.port;
	}

	/**
	 * Define o campo <code>port</code>.
	 *
	 * @param port 
	 */
	public void setPort(String port) {
		
		this.port = port;
	}

	/**
	 * Obtém o valor do campo <code>usuario</code>
	 *
	 * @return {@link String}
	 */
	public String getUsuario() {
		
		return this.usuario;
	}

	/**
	 * Define o campo <code>usuario</code>.
	 *
	 * @param usuario 
	 */
	public void setUsuario(String usuario) {
		
		this.usuario = usuario;
	}

	/**
	 * Obtém o valor do campo <code>senha</code>
	 *
	 * @return {@link byte[]}
	 */
	public byte[] getSenha() {
		
		return this.senha;
	}

	/**
	 * Define o campo <code>senha</code>.
	 *
	 * @param senha 
	 */
	public void setSenha(byte[] senha) {
		
            if(senha != null) {
                
		this.senha = (byte[]) senha.clone();
            }
	}

}
