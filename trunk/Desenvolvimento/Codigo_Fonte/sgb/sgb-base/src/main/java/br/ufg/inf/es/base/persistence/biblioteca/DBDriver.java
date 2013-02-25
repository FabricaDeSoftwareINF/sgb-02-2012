package br.ufg.inf.es.base.persistence.biblioteca;

/**
 * Enum com os dados dos drivers de banco de dados suportados;
 *
 * @author igor
 */
public enum DBDriver {

    /**
     * Campo Oracle
     */
    Oracle("jdbc:oracle:thin:", 1, "oracle.jdbc.driver.OracleDriver"),
    /**
     * Campo Postgres
     */
    Postgres("jdbc:postgresql:", 2, "org.postgresql.Driver"),
    /**
     * Campo MySQL
     */
    MySQL("jdbc:mysql:", 3, "com.mysql.jdbc.Driver"),
    /**
     * Campo Derby
     */
    Derby("jdbc:derby:", 4, "org.apache.derby.jdbc.ClientDriver");
    /**
     * Campo jdbc
     */
    private String jdbc;
    /**
     * Campo numero
     */
    private int numero;
    /**
     * Campo driver
     */
    private String driver;

    /**
     * Construtor desta classe.
     */
    DBDriver() {
    }

    /**
     * Construtor desta classe.
     *
     * @param jdbc
     * @param numero
     * @param driver
     */
    DBDriver(String jdbc, int numero, String driver) {
        this.jdbc = jdbc;
        this.numero = numero;
        this.driver = driver;
    }

    /**
     * Obtém o valor do campo<code>name</code>
     *
     * @return {@link String}
     */
    public String getName() {
        return this.name();
    }

    

    /**
     * Obtém o valor do campo
     * <code>jdbc</code>
     *
     * @return {@link String}
     */
    public String getJDBC() {

        return jdbc;
    }

    /**
     * Define o campo
     * <code>jdbc</code>.
     *
     * @param jdbc
     */
    public void setJDBC(String jdbc) {

        this.jdbc = jdbc;
    }

    /**
     * Obtém o valor do campo
     * <code>numero</code>
     *
     * @return {@link int}
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Define o campo
     * <code>numero</code>.
     *
     * @param numero
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Obtém o valor do campo
     * <code>driver</code>
     *
     * @return {@link String}
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Define o campo
     * <code>driver</code>.
     *
     * @param driver
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }
}
