package br.ufg.inf.es.integracao.optimizer;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * TAD para definir as características de uma cotação. A ordem natural de 
 * ordenação destas características deverá ser em relação ao custo total
 * 
 * @author Cézar Augusto Ferreira
 */
public interface Quotation<C> extends Serializable, Comparable<C> {

    /**
     * Obtem a quantidade do produto que foi cotado
     *
     * @return quantidade do produto que foi cotado
     */
    Integer getQuantity();

    /**
     * Obtem o identificador do produto que foi cotado
     *
     * @return identificador do produto que foi cotado
     */
    Comparable<?> getProductId();

    /**
     * Obtem o valor monetário do produto cotado
     *
     * @return valor monetário do produto cotado
     */
    BigDecimal getPrice();

    /**
     * Obtem o custo total da cotação a partir da seguinte fórmula:
     *
     * quantidade do produto * valor monetário
     *
     * @return custo total da cotação
     */
    BigDecimal getTotalCost();
}