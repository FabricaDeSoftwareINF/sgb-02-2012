package br.ufg.inf.es.persistencia.biblioteca;

import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;
import br.ufg.inf.es.base.util.cripto.CriptoGeneric;
import java.sql.Connection;


/**
 *
 * @author igor
 */
public class BCDao {
    protected Connection connection;
    private String tabela = "tabela";
    
    public BCDao(String tabela){
       DBBibliotecaConfig dbConfig = new DBBibliotecaConfigDAO().getBibliotecaCfg();
       String pass = new String(new CriptoGeneric().decriptografa(dbConfig.getPasswordDataBaseByte()));
       this.tabela = tabela;
       
       this.connection = Conecta.getSessionConnection(dbConfig.getDriver(),
             dbConfig.getUrl(), dbConfig.getPorta(), dbConfig.getNameDataBase(), 
             dbConfig.getUserDataBase(), pass);
    }
    
    @Override
    protected void finalize() throws Throwable {
        this.connection.close();
    }
}
