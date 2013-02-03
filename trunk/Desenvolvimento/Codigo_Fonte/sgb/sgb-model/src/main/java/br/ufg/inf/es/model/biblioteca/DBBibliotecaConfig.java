package br.ufg.inf.es.model.biblioteca;

import br.ufg.inf.es.base.persistence.biblioteca.DBDriver;
import br.ufg.inf.es.base.util.cripto.CriptoGeneric;
import br.ufg.inf.es.model.AbstractEntityModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author igor
 */
@Entity
@Table(name = "BIBLIOTECA_PARAMETROS")
public class DBBibliotecaConfig extends AbstractEntityModel{
    @Transient
    private final int TAMANHO_KEY = 16;
   
    @Enumerated(EnumType.STRING)
    @Column(name="tipo", nullable=false)
    private DBDriver driver;   
    
    @Column(name="url", nullable=false)
    private String url;
    
    @Column(name="porta", nullable=false)
    private String porta;
    
    @Column(name="nome_database", nullable=false)
    private String nameDataBase;
    
    @Column(name="user_database", nullable=false)
    private String userDataBase;
    
    @Column(name="password_database", length=300,nullable=false)
    private byte[] passwordDataBase;
    
    @Column(name="tabela", nullable=false)
    private String tabela;
    
    @Column(name="titulo_livro", nullable=false)
    private String campoTituloLivro;
    
    @Column(name="quantidade_livro", nullable=false)
    private String campoQuantidadeLivro;

    public DBBibliotecaConfig() {
    }

    public DBBibliotecaConfig(DBDriver dBDriver, String status, String url, String porta, String nameDataBase, 
            String userDataBase, byte[] passwordDataBase, String tabela, String campoTituloLivro) {
        this.driver = dBDriver;
        this.url = url;
        this.porta = porta;
        this.nameDataBase = nameDataBase;
        this.userDataBase = userDataBase;
        this.passwordDataBase = passwordDataBase;
        this.tabela = tabela;
        this.campoTituloLivro = campoTituloLivro;
    }

    public String getNameDataBase() {
        return nameDataBase;
    }

    public void setNameDataBase(String nameDataBase) {
        this.nameDataBase = nameDataBase;
    }

    public String getPasswordDataBase() {
        return (passwordDataBase != null)? new String(passwordDataBase) : "";
    }
    
    public byte[] getPasswordDataBaseByte(){
        return passwordDataBase;
    }

    public void setPasswordDataBase(String passwordDataBase) {
        byte[] passwordDataBaseCrip = new byte[TAMANHO_KEY];
        CriptoGeneric cripto = new CriptoGeneric();
        passwordDataBaseCrip = cripto.criptografa(passwordDataBase);
        this.passwordDataBase = passwordDataBaseCrip;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String port) {
        this.porta = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserDataBase() {
        return userDataBase;
    }

    public void setUserDataBase(String userDataBase) {
        this.userDataBase = userDataBase;
    }

    public DBDriver getDriver() {
        return driver;
    }

    public void setDriver(DBDriver driver) {
        this.driver = driver;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getCampoTituloLivro() {
        return campoTituloLivro;
    }

    public void setCampoTituloLivro(String campoTituloLivro) {
        this.campoTituloLivro = campoTituloLivro;
    }

    public String getCampoQuantidadeLivro() {
        return campoQuantidadeLivro;
    }

    public void setCampoQuantidadeLivro(String campoQuantidadeLivro) {
        this.campoQuantidadeLivro = campoQuantidadeLivro;
    }   

    @Override
    public String toString() {
        return "DBConfig{" + "dBDriver=" + driver + ", url=" + url + 
               ", port=" + porta + ", nameDataBase=" + nameDataBase + 
               ", userDataBase=" + userDataBase + 
               ", passwordDataBase=" + passwordDataBase + 
               ", tabela=" + tabela + ",s=" + campoTituloLivro +'}';
    }
}