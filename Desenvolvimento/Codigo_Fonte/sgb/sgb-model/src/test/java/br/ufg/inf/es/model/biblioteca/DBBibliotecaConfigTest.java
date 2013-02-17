package br.ufg.inf.es.model.biblioteca;

import br.ufg.inf.es.base.persistence.biblioteca.DBDriver;
import javax.persistence.Column;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Carvalho
 */
public class DBBibliotecaConfigTest {

    private DBBibliotecaConfig dBBibliotecaConfig;
    private DBDriver driver = DBDriver.Derby;
    private String url = "url";
    private String porta = "porta";
    private String nameDataBase = "nameDataBase";
    private String userDataBase = "userDataBase";
    private byte[] passwordDataBase = "passwordDataBase".getBytes();
    private String tabela = "tabela";
    private String campoIdLivroBiblioteca = "campoIdLivroBiblioteca";
    private String campoTituloLivro = "campoTituloLivro";
    private String campoIsbnLivro = "campoIsbnLivro";
    private String campoAnoLivro = "campoAnoLivro";
    private String campoEdicao = "campoEdicao";
    private String campoEditora = "campoEditora";
    private String campoAutor = "campoAutor";
    private String campoQuantidadeLivro = "campoQuantidadeLivro";

    @Before
    public void setUp() {
        dBBibliotecaConfig = new DBBibliotecaConfig();

        dBBibliotecaConfig.setDriver(driver);
        dBBibliotecaConfig.setUrl(url);
        dBBibliotecaConfig.setPorta(porta);
        dBBibliotecaConfig.setNameDataBase(nameDataBase);
        dBBibliotecaConfig.setUserDataBase(userDataBase);
        dBBibliotecaConfig.setPasswordDataBase(passwordDataBase);
        dBBibliotecaConfig.setTabela(tabela);
        dBBibliotecaConfig.setCampoIdLivroBiblioteca(campoIdLivroBiblioteca);
        dBBibliotecaConfig.setCampoTituloLivro(campoTituloLivro);
        dBBibliotecaConfig.setCampoIsbnLivro(campoIsbnLivro);
        dBBibliotecaConfig.setCampoAnoLivro(campoAnoLivro);
        dBBibliotecaConfig.setCampoEdicao(campoEdicao);
        dBBibliotecaConfig.setCampoEditora(campoEditora);
        dBBibliotecaConfig.setCampoAutor(campoAutor);
        dBBibliotecaConfig.setCampoQuantidadeLivro(campoQuantidadeLivro);
    }

    /**
     * Test of getDriver method, of class DBBibliotecaConfig.
     */
    @Test
    public void testGetDriver() {
        assertEquals(driver, dBBibliotecaConfig.getDriver());
    }

    /**
     * Test of getUrl method, of class DBBibliotecaConfig.
     */
    @Test
    public void testGetUrl() {
        assertEquals(url, dBBibliotecaConfig.getUrl());
    }

    /**
     * Test of getPorta method, of class DBBibliotecaConfig.
     */
    @Test
    public void testGetPorta() {
        assertEquals(porta, dBBibliotecaConfig.getPorta());
    }

    /**
     * Test of getNameDataBase method, of class DBBibliotecaConfig.
     */
    @Test
    public void testGetNameDataBase() {
        assertEquals(nameDataBase, dBBibliotecaConfig.getNameDataBase());
    }

    /**
     * Test of getUserDataBase method, of class DBBibliotecaConfig.
     */
    @Test
    public void testGetUserDataBase() {
        assertEquals(userDataBase, dBBibliotecaConfig.getUserDataBase());
    }

    /**
     * Test of getPasswordDataBase method, of class DBBibliotecaConfig.
     */
    @Test
    public void testGetPasswordDataBase() {
        assertNotNull(dBBibliotecaConfig.getPasswordDataBase());
    }

    /**
     * Test of setPasswordDataBase method, of class DBBibliotecaConfig.
     */
    @Test
    public void testSetPasswordDataBaseNull() {
        DBBibliotecaConfig other = new DBBibliotecaConfig();
        other.setPasswordDataBase(null);
        assertNull(other.getPasswordDataBase());
    }

    /**
     * Test of getTabela method, of class DBBibliotecaConfig.
     */
    @Test
    public void testGetTabela() {
        assertEquals(tabela, dBBibliotecaConfig.getTabela());
    }

    /**
     * Test of getCampoIdLivroBiblioteca method, of class DBBibliotecaConfig.
     */
    @Test
    public void testGetCampoIdLivroBiblioteca() {
        assertEquals(campoIdLivroBiblioteca, dBBibliotecaConfig.getCampoIdLivroBiblioteca());
    }

    /**
     * Test of getCampoTituloLivro method, of class DBBibliotecaConfig.
     */
    @Test
    public void testGetCampoTituloLivro() {
        assertEquals(campoTituloLivro, dBBibliotecaConfig.getCampoTituloLivro());
    }

    /**
     * Test of getCampoIsbnLivro method, of class DBBibliotecaConfig.
     */
    @Test
    public void testGetCampoIsbnLivro() {
        assertEquals(campoIsbnLivro, dBBibliotecaConfig.getCampoIsbnLivro());
    }

    /**
     * Test of getCampoAnoLivro method, of class DBBibliotecaConfig.
     */
    @Test
    public void testGetCampoAnoLivro() {
        assertEquals(campoAnoLivro, dBBibliotecaConfig.getCampoAnoLivro());
    }

    /**
     * Test of getCampoEdicao method, of class DBBibliotecaConfig.
     */
    @Test
    public void testGetCampoEdicao() {
        assertEquals(campoEdicao, dBBibliotecaConfig.getCampoEdicao());
    }

    /**
     * Test of getCampoEditora method, of class DBBibliotecaConfig.
     */
    @Test
    public void testGetCampoEditora() {
        assertEquals(campoEditora, dBBibliotecaConfig.getCampoEditora());
    }

    /**
     * Test of getCampoAutor method, of class DBBibliotecaConfig.
     */
    @Test
    public void testGetCampoAutor() {
        assertEquals(campoAutor, dBBibliotecaConfig.getCampoAutor());
    }

    /**
     * Test of getCampoQuantidadeLivro method, of class DBBibliotecaConfig.
     */
    @Test
    public void testGetCampoQuantidadeLivro() {
        assertEquals(campoQuantidadeLivro, dBBibliotecaConfig.getCampoQuantidadeLivro());
    }

    /**
     * Test of toString method, of class DBBibliotecaConfig.
     */
    @Test
    public void testToString() {
        assertNotNull(dBBibliotecaConfig.toString());
    }
}