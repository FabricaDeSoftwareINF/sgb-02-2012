/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.base.model.annotations;

import junit.framework.TestCase;

/**
 *
 * @author usuario
 */
public class SortOrderTest extends TestCase {
    
  

    /**
     * Test of values method, of class SortOrder.
     */
    public void testValues() {
        SortOrder[] expResult = new SortOrder[2];
        expResult[0] = SortOrder.ASC;
        expResult[1] = SortOrder.DESC;
        SortOrder[] result = SortOrder.values();
        assertEquals(expResult[0], result[0]);
        assertEquals(expResult[1], result[1]);
        
    }

    /**
     * Test of valueOf method, of class SortOrder.
     */
    
    public void testValueOfAsc() {
        String name = "ASC";
        SortOrder expResult = SortOrder.ASC;
        SortOrder result = SortOrder.valueOf(name);
        assertEquals(expResult, result);
    }
      
   
    public void testValueOfDesc() {
        String name = "DESC";
        SortOrder expResult = SortOrder.DESC;
        SortOrder result = SortOrder.valueOf(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getHql method, of class SortOrder.
     */
    
    public void testGetHql() {
        String property = "A";
        SortOrder instance = SortOrder.DESC;
        String expResult = "order by A DESC";
        String result = instance.getHql(property);
        assertEquals(expResult, result);
        
    }
}
