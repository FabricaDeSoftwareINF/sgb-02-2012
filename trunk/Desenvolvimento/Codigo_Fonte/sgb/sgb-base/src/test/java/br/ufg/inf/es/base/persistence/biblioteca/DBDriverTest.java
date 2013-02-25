/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.base.persistence.biblioteca;

import junit.framework.TestCase;

/**
 *
 * @author usuario
 */
public class DBDriverTest extends TestCase {

    /**
     * Test of values method, of class DBDriver.
     */
    public void testValues() {
        DBDriver[] expResult = new DBDriver[4];
        expResult[0] = DBDriver.Oracle;
        expResult[1] = DBDriver.Postgres;
        expResult[2] = DBDriver.MySQL;
        expResult[3] = DBDriver.Derby;
        
        DBDriver[] result = DBDriver.values();
        assertEquals(expResult[0], result[0]);
        assertEquals(expResult[1], result[1]);
        assertEquals(expResult[2], result[2]);
        assertEquals(expResult[3], result[3]);

    }

    /**
     * Test of valueOf method, of class DBDriver.
     */
    public void testValueOf() {

        String name = "Derby";
        DBDriver expResult = DBDriver.Derby;
        DBDriver result = DBDriver.valueOf(name);
        assertEquals(expResult, result);

    }

    /**
     * Test of getName method, of class DBDriver.
     */
    public void testGetName() {
        DBDriver instance = DBDriver.MySQL;
        String expResult = "MySQL";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

   

    /**
     * Test of getJDBC method, of class DBDriver.
     */
    
    public void testGetJDBC() {
        DBDriver instance = DBDriver.Derby;
        String expResult = "jdbc:derby:";
        String result = instance.getJDBC();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setJDBC method, of class DBDriver.
     */
   
    public void testSetJDBC() {
        String jdbc = "derby";
        DBDriver instance = DBDriver.Derby;
        instance.setJDBC(jdbc);
        assertEquals(jdbc, instance.getJDBC());
    }

    /**
     * Test of getNumero method, of class DBDriver.
     */
    
    public void testGetNumero() {
        DBDriver instance = DBDriver.Derby;
        int expResult = 4;
        int result = instance.getNumero();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setNumero method, of class DBDriver.
     */
    
    public void testSetNumero() {
        int numeroOracle = 0;
        DBDriver instance = DBDriver.Derby;//numero 4
        instance.setNumero(numeroOracle);
        assertEquals(instance.getNumero(), numeroOracle);
    }

    /**
     * Test of getDriver method, of class DBDriver.
     */
    
    public void testGetDriver() {
        DBDriver instance = DBDriver.Derby;
        String expResult = "org.apache.derby.jdbc.ClientDriver";
        String result = instance.getDriver();
        assertEquals(expResult, result);
    
    }

    /**
     * Test of setDriver method, of class DBDriver.
     */
    
    public void testSetDriver() {
        String driver = "org.apache.derby.jdbc.ClientDriver";
        DBDriver instance = DBDriver.Oracle;
        instance.setDriver(driver);
        assertEquals(driver, instance.getDriver());
    }
}
