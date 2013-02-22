
package br.ufg.inf.es.integracao.optimizer;

import java.math.BigDecimal;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Classe de teste para a classe DataQuotation
 * @author Allan Vieira Ribeiro
 */
public class DataQuotationTest {
    
    private DataQuotation data;
    private Integer quantidade;
    
    
    public DataQuotationTest() {
    }
    
    @Before
    public void setUp() {
        
        quantidade = 1;
        
        data = new DataQuotation(1L, BigDecimal.ZERO, quantidade);
    }
    

    /**
     * Teste do método obter quantidade.
     */
    @Test
    public void testGetQuantity() {
        
        assertEquals(data.getQuantity().doubleValue(), quantidade.doubleValue(), 0.00);
    }

    /**
     * Teste do método getProductId
     */
    @Test
    public void testGetProductId() {
        
        assertEquals(data.getProductId(), 1L);
    }

    /**
     * Teste do método getPrice.
     */
    @Test
    public void testGetPrice() {
        
        assertEquals(data, data);
    }

    /**
     * Teste do método getTotalCost.
     */
    @Test
    public void testGetTotalCost() {
        
        assertEquals(data.getTotalCost(), BigDecimal.ZERO);
    }

    
}
