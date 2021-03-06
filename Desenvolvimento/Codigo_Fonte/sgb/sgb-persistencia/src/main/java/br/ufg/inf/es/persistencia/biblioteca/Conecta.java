package br.ufg.inf.es.persistencia.biblioteca;

import br.ufg.inf.es.base.persistence.biblioteca.DBDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe de geração de conexão com banco de dados via JDBC
 * @author igor
 */
class Conecta {
	
	private Conecta(){
		
	}

    /**
     * Método que recupera uma sessão de conexão dom banco de dados via JDBC
     * @param driver de conexão com o banco de dados
     * @param url do servidor do banco de dados
     * @param port porta de conexão com o banco de dados
     * @param dataBase nome da base de dados (Schema)
     * @param userName nome do usuário de conexão com o banco de dados
     * @param password senha do usuário de conexão com o banco de dados
     * @return retornar uma instancia de conexão com o banco de dados
     */
    public static Connection getSessionConnection(DBDriver driver, String url,
            String port, String dataBase, String userName, String password) {
        
        Connection conecta = null;
        
        String urlDBService;

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
            
            Logger.getAnonymousLogger().log(Level.SEVERE, ex.getMessage());

            
        } catch (ClassNotFoundException ex) {
            
            Logger.getAnonymousLogger().log(Level.SEVERE, ex.getMessage());
        } 

        return conecta;
    }
}
