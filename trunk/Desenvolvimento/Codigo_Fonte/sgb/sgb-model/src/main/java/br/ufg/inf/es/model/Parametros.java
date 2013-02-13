package br.ufg.inf.es.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Classe que representa a entidade parâmetros.
 * @author Victor Carvalho
 */
@Entity
@Table(name = "PARAMETROS")
public class Parametros extends AbstractEntityModel {

    /** Campo VALOR_INICIAL_PARAMETRO_MEC*/
    @Transient
    private static final int VALOR_INICIAL_PARAMETRO_MEC = 5;
    
    /** Campo valorFrete*/
    @Column(name = "valor_frete")
    private BigDecimal valorFrete = new BigDecimal("30.00");
    
    /** Campo parametroMEC*/
    @Column(name = "parametro_mec")
    private Integer parametroMEC = Parametros.VALOR_INICIAL_PARAMETRO_MEC;

	/**
	 * Obtém o valor do campo <code>valorFrete</code>
	 *
	 * @return {@link BigDecimal}
	 */
	public BigDecimal getValorFrete() {
		return this.valorFrete;
	}

	/**
	 * Define o campo <code>valorFrete</code>.
	 *
	 * @param valorFrete 
	 */
	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	/**
	 * Obtém o valor do campo <code>parametroMEC</code>
	 *
	 * @return {@link Integer}
	 */
	public Integer getParametroMEC() {
		return this.parametroMEC;
	}

	/**
	 * Define o campo <code>parametroMEC</code>.
	 *
	 * @param parametroMEC 
	 */
	public void setParametroMEC(Integer parametroMEC) {
		this.parametroMEC = parametroMEC;
	}

}
