/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.persistencia.biblioteca;

import br.ufg.inf.es.base.persistence.biblioteca.DBDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.PowerMockRunner;
 import static org.powermock.api.mockito.PowerMockito.*;

/**
 *
 * @author Cassio Augusto Silva de Freitas
 */
 @RunWith(PowerMockRunner.class)
 @PrepareForTest(DriverManager.class)
public class ConectaTest {
    
     private Connection conn;
     
    
    @Before
    public void setUp() {
        
        this.conn =  mock(Connection.class);

    }
    
    /**
     * Test of getSessionConnection method, of class Conecta.
     */
    @Test
    public void testGetSessionConnection() throws SQLException {
        
        mockStatic(DriverManager.class);
        
        when(DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(conn);
        
        Connection result = Conecta.getSessionConnection(DBDriver.Oracle, "", "", "", "", "");
       
        assertNull(result);
    }
    
    
    @Test
    public void testGetSessionConnection02() throws SQLException, Exception {
        
        mockStatic(DriverManager.class);
       
        when(DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(conn);
        
        verifyStatic();
        
    }
    
    @Test
    public void testGetSessionConnection03() throws SQLException, Exception {
        
        mockStatic(DriverManager.class);
       
        when(DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(conn);

        verifyStatic();
        
        Connection sessionConnection = Conecta.getSessionConnection(DBDriver.MySQL, "localhost", "3306", "sgb", "admin", "root");
       
        assertNull(sessionConnection);
        
    }
}
