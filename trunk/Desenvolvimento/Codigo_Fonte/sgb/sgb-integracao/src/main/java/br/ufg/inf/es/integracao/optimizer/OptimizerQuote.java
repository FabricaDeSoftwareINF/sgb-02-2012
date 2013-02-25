package br.ufg.inf.es.integracao.optimizer;

import java.math.BigDecimal;
import java.util.*;

/**
 * Classe responsável pela otimização das cotações
 *
 * @author Cezar Augusto Ferreira
 */
public class OptimizerQuote {

	private OptimizerQuote(){
		
	}
	
    /**
     * Classe decoração/wrapper de uma DataQuotation
     */
    private static class DataQuotationWrapper implements Quotation {

        private Quotation dataQuotation;
        private Integer quantity = 0;

        public DataQuotationWrapper(Quotation dataQuotation) {

            this.dataQuotation = dataQuotation;
        }

        public DataQuotation dataQuotationValue() {

            this.quantity = 0;

            return DataQuotation.create(this.dataQuotation.getProductId(), this.dataQuotation.getPrice(), this.getQuantity());
        }

        public Integer getQuantity() {

            return quantity;
        }

        public void setQuantity(Integer quantity) {

            this.quantity = quantity;
        }

        public Comparable<?> getProductId() {

            return this.dataQuotation.getProductId();
        }

        public BigDecimal getTotalCost() {

            return this.dataQuotation.getTotalCost();
        }

        public int compareTo(Object o) {

            return this.dataQuotation.compareTo(o);
        }

        public BigDecimal getPrice() {

            return this.dataQuotation.getPrice();
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 97 * hash + (this.dataQuotation != null ? this.dataQuotation.hashCode() : 0);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final DataQuotationWrapper other = (DataQuotationWrapper) obj;
            if (this.dataQuotation != other.dataQuotation && (this.dataQuotation == null || !this.dataQuotation.equals(other.dataQuotation))) {
                return false;
            }
            return true;
        }
    }

    /**
     * Realizar otimização alocando um produto de cada
     *
     * @param in cotações
     * @param amountInner custo a ser gasto
     * @param result lista que será atualizada como resultado
     *
     * @return quantidade que sobrou do custo a ser gasto
     */
    private static BigDecimal buyOneOfEach(List<Quotation> in, BigDecimal amountInner, List<Quotation> result) {

        for (Quotation quotation : in) {

            if (quotation.getPrice().compareTo(amountInner) <= 0) {

                DataQuotationWrapper wrapper = new DataQuotationWrapper(quotation);

                if (!result.contains(wrapper)) {

                    wrapper.setQuantity(1);

                    result.add(wrapper);

                } else {

                    wrapper = (DataQuotationWrapper) result.get(result.indexOf(wrapper));

                    wrapper.setQuantity(wrapper.getQuantity() + 1);
                }

                amountInner = amountInner.subtract(wrapper.getPrice());
            }
        }

        return amountInner;
    }

    /**
     * Comparador de cotações para redefinir a ordenação natural para utilizar
     *
     * o preço do produto.
     */
    private static class PriceOrderQuotation implements Comparator<Quotation> {

        @Override
        public int compare(Quotation qa, Quotation qb) {

            return qa.getPrice().compareTo(qb.getPrice());
        }
    }

    /**
     * Otimiza uma lista de cotações com foco na quantidade
     *
     * @param quotations cotações a serem otimizadas
     *
     * @param amount quantia que deverá ser gasta
     *
     * @return lista das cotações contendo o maior número de produtos
     */
    public static List<Quotation> optimizeQuantity(final List<? extends Quotation> quotations, final BigDecimal amount) {

        return OptimizerQuote.optimize(quotations, amount, false);
    }

    /**
     * Otimiza uma lista de cotações com foco em abranger o maior custo
     *
     * @param quotations cotações a serem otimizadas
     *
     * @param amount quantia que deverá ser gasta
     *
     * @return lista das cotações contendo o maior custo
     */
    public static List<Quotation> optimizeCost(final List<? extends Quotation> quotations, final BigDecimal amount) {

        return OptimizerQuote.optimize(quotations, amount, true);
    }

    /**
     * Realiza as otimizações de cotação
     *
     * @param quotations cotações a serem otimizadas
     *
     * @param amount quantia que deverá ser gasta
     *
     * @param fullValue atender ao valor máximo
     *
     * @return lista das cotações otimizadas
     */
    private static List<Quotation> optimize(final List<? extends Quotation> quotations, final BigDecimal amount, boolean fullValue) {

        BigDecimal amountInner = BigDecimal.valueOf(amount.doubleValue());

        List<Quotation> in = new ArrayList<Quotation>(quotations);

        List<Quotation> result = new LinkedList<Quotation>();

        Collections.sort(in);

        Iterator<Quotation> iterator = in.iterator();

        while (iterator.hasNext()) {

            Quotation quotation = iterator.next();

            if (quotation.getTotalCost().compareTo(amountInner) <= 0) {

                iterator.remove();

                result.add(quotation);

                amountInner = amountInner.subtract(quotation.getTotalCost());
            }
        }

        if (fullValue && in.size() > 0) {

            Collections.sort(in, new PriceOrderQuotation());

            Quotation minData = in.get(0);

            while (amountInner.compareTo(minData.getPrice()) > 0) {

                amountInner = buyOneOfEach(in, amountInner, result);
            }
        }

        return result;
    }
}