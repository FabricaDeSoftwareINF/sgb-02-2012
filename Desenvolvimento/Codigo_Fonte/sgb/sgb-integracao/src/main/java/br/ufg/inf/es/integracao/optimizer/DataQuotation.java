package br.ufg.inf.es.integracao.optimizer;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Representa os dados de uma cotação
 *
 * @author Cézar Augusto Ferreira
 */
public final class DataQuotation implements Quotation<DataQuotation> {

    /**
     * IDENTIFICADOR DO PRODUTO
     */
    private Comparable<?> productId;
    /**
     * VALOR MONETÁRIO DO PRODUTO
     */
    private BigDecimal price;
    /**
     * QUANTIDADE DO PRODUTO
     */
    private Integer quantity;

    /**
     * Realiza a construção de uma representação de cotação tendo como base
     * algum identificador de um produto qualquer, o valor monetário e a
     * quantidade do produto
     *
     * @param productId identificador do produt
     *
     * @param value valor monetário do produto
     *
     * @param quantity quantidade do produto
     */
    public DataQuotation(Comparable<?> productId, BigDecimal price, Integer quantity) {

        this.productId = productId;

        this.price = price;

        this.quantity = (quantity == null ? 0 : quantity);
    }

    /**
     * Realizar a criação de uma representação de cotação
     *
     * @param data dados da cotação [0] productId, [1] value, [2] amount
     *
     * @return representação criada
     */
    public static DataQuotation create(Object... data) {

        return new DataQuotation((Comparable<?>) data[0], (BigDecimal) data[1], (Integer) data[2]);
    }

    /**
     * {@link Quotation#getQuantity() }
     */
    public Integer getQuantity() {

        return quantity;
    }

    /**
     * {@link Quotation#getProductId() }
     */
    public Comparable<?> getProductId() {

        return productId;
    }

    /**
     * {@link Quotation#getPrice() }
     */
    public BigDecimal getPrice() {
        
        return price;
    }

    /**
     * {@link Quotation#getTotalCost() }
     */
    public BigDecimal getTotalCost() {

        return this.getPrice().multiply(BigDecimal.valueOf(this.getQuantity()));
    }

    /**
     * Obtem o estado interno da cotação apresentando os dados da seguinte
     * forma:
     *
     * Quote{productId, price, amount}
     *
     * @return estado interno do objeto
     */
    @Override
    public String toString() {

        return "Quote{" + "productId=" + productId + ", price=" + price + ", amount=" + quantity + '}';
    }

    /**
     * {@link Object#equals(java.lang.Object) }
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {

            return false;
        }
        if (getClass() != obj.getClass()) {

            return false;
        }

        final DataQuotation other = (DataQuotation) obj;

        if (!Objects.equals(this.productId, other.productId)) {

            return false;
        }

        if (!Objects.equals(this.price, other.price)) {

            return false;
        }

        return true;
    }

    /**
     * {@link Object#hashCode() }
     */
    @Override
    public int hashCode() {

        int hash = 7;

        hash = 11 * hash + Objects.hashCode(this.productId);

        hash = 11 * hash + Objects.hashCode(this.price);

        return hash;
    }

    /**
     * {@link Comparable#compareTo(java.lang.Object) }
     */
    @Override
    public int compareTo(DataQuotation o) {

        return this.getTotalCost().compareTo(o.getTotalCost());
    }
}