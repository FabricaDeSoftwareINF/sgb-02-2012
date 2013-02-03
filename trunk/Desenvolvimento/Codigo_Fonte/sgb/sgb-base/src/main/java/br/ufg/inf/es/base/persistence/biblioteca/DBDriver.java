package br.ufg.inf.es.base.persistence.biblioteca;

/**
 *
 * @author igor
 */
public enum DBDriver {
    
    Oracle("jdbc:oracle:thin:", 1, "oracle.jdbc.driver.OracleDriver"),
    Postgres("jdbc:postgresql:", 2,"org.postgresql.Driver"),
    MySQL("jdbc:mysql:", 3, "com.mysql.jdbc.Driver"),
    Derby("jdbc:derby:", 4, "org.apache.derby.jdbc.ClientDriver");
    
    private String jdbc;
    private int numero;
    private String driver;

    DBDriver(){}
    
    DBDriver(String jdbc, int numero, String driver) {
        this.jdbc = jdbc;
        this.numero = numero;
        this.driver = driver;
    }
    
    public String getName(){
        return this.name();
    }
    
    public void setName(String driver){
        
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }   
    
    public String getJDBC(){
        return this.jdbc;
    }
    
    public void set(String jdbc){
        this.jdbc = jdbc;
    }
}

