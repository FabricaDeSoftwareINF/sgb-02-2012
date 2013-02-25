/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidade Livraria
 *
 * @author GeovaneFilho
 */
@Entity
@Table(name="LIVRARIA")
public class Livraria extends AbstractEntityModel {

	private static final int SITE_LENGTH = 2038;
	
    /**
     * Campo nome
     */
    private String nome;
    /**
     * Campo site
     */
    @Column(length=SITE_LENGTH)
    private String site;
    /**
     * Campo urlLogo
     */
    private String urlLogo;

    /**
     * Construtor desta classe.
     */
    public Livraria() {
    }

    /**
     * Obtém o valor do campo
     * <code>nome</code>
     *
     * @return {@link String}
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Define o campo
     * <code>nome</code>.
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o valor do campo
     * <code>site</code>
     *
     * @return {@link String}
     */
    public String getSite() {
        return this.site;
    }

    /**
     * Define o campo
     * <code>site</code>.
     *
     * @param site
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * Obtém o valor do campo
     * <code>urlLogo</code>
     *
     * @return {@link String}
     */
    public String getUrlLogo() {
        return this.urlLogo;
    }

    /**
     * Define o campo
     * <code>urlLogo</code>.
     *
     * @param urlLogo
     */
    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }
}
