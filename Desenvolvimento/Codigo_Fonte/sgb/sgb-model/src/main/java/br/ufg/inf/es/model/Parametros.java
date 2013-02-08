package br.ufg.inf.es.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Victor Carvalho
 */
@Entity
@Table(name = "PARAMETROS")
public class Parametros extends AbstractEntityModel {

    @Transient
    private static final int VALOR_INICIAL_PARAMETRO_MEC = 5;
    
    @Column(name = "valor_frete")
    private BigDecimal valorFrete = new BigDecimal("30.00");
    
    @Column(name = "parametro_mec")
    private Integer parametroMEC = Parametros.VALOR_INICIAL_PARAMETRO_MEC;

    public Integer getParametroMEC() {
        return parametroMEC;
    }

    public void setParametroMEC(Integer parametroMEC) {
        this.parametroMEC = parametroMEC;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }
}
