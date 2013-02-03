package br.ufg.inf.es.persistencia.biblioteca;

import br.ufg.inf.es.base.persistence.biblioteca.DBDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author igor
 */
public class Conecta {

    /**
     *
     * @param driver
     * @param url
     * @param port
     * @param dataBase
     * @param userName
     * @param password
     * @return
     */
    public static Connection getSessionConnection(DBDriver driver, String url,
            String port, String dataBase, String userName, String password) {
        Connection conecta = null;
        
        String urlDBService = null;

        try {
            //Inserindo o driver de conexão com o banco de dados
            Class.forName(driver.getDriver());
            //Estabelecendo conexão com o banco de dados
            if (driver.name().equals(DBDriver.Oracle.name())) {
                urlDBService = driver.getJDBC() + "@" + url + ":" + port + ":" + 
                                dataBase;
                
            } else {
                urlDBService = driver.getJDBC() + "//" + url + ":" + port + "/" + 
                                dataBase;
            }
            
            conecta = DriverManager.getConnection(urlDBService, userName, password);

        } catch (SQLException ex) {
            System.err.println("Mensagem de erro: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found exception: " + ex.getMessage());
        } catch (Throwable e) {
            System.err.println("Causa do erro: " + e.getCause().getMessage());
        }

        return conecta;
    }
}
