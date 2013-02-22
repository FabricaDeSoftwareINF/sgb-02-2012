/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.optimizer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 * Classe de testes para otimização da lista.
 * @author Allan Vieira Ribeiro
 */
public class OptimizerQuoteTest {
    
    public OptimizerQuoteTest() {
   
    }

    private DataQuotation cotacao1;
    
    private DataQuotation cotacao2; 
    
    private DataQuotation cotacao3;
    
    private List<DataQuotation> cotacoes;
    
    @Before
    public void setUp() {
        
        cotacao1 =  new DataQuotation(1, new BigDecimal(100), 3);
        
        cotacao2 = new DataQuotation(2, new BigDecimal(32), 2);
        
        cotacao3 =  new DataQuotation(3, new BigDecimal(60), 4);
        
        cotacoes = new ArrayList<DataQuotation>();
        
        cotacoes.add(cotacao1);
        
        cotacoes.add(cotacao2);
        
        cotacoes.add(cotacao3);
    }

    /**
     * Test of optimizeQuantity method, of class OptimizerQuote.
     */
    @Test
    public void testOptimizeQuantity() {

        List<DataQuotation> quotations = new ArrayList<DataQuotation>();
        
        quotations.add(cotacao2);
        
        List result = OptimizerQuote.optimizeQuantity(cotacoes, new BigDecimal(100));
        
        assertArrayEquals(quotations.toArray(new DataQuotation[quotations.size()]), result.toArray(new DataQuotation[result.size()]));
    }

    /**
     * Test of optimizeCost method, of class OptimizerQuote.
     */
    @Test
    public void testOptimizeCost() {
        
        List<DataQuotation> quotations = new ArrayList<DataQuotation>();
        
        quotations.add(cotacao2);
        
        quotations.add(cotacao3);
        
        quotations.add(cotacao1);
        
        List<Quotation> result = OptimizerQuote.optimizeCost(cotacoes, new BigDecimal(540));
        
        List<DataQuotation> quotations2 = new ArrayList<DataQuotation>();
        
        for(Quotation cot: result){
            
            quotations2.add(new DataQuotation(cot.getProductId(), cot.getPrice(), cot.getQuantity()));
        }
        
        assertArrayEquals(quotations.toArray(new DataQuotation[quotations.size()]), quotations2.toArray(new DataQuotation[quotations2.size()]));
    }
}
