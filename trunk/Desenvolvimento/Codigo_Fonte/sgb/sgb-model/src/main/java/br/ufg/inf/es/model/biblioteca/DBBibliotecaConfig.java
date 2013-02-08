package br.ufg.inf.es.model.biblioteca;

import br.ufg.inf.es.base.persistence.biblioteca.DBDriver;
import br.ufg.inf.es.model.AbstractEntityModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Classe que representa os parâmetros de comunicação com o banco de dados da 
 * biblioteca
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
    
    @Column(name="id_livro")
    private String campoIdLivroBiblioteca;
    
    @Column(name="titulo_livro", nullable=false)
    private String campoTituloLivro;
    
    @Column(name="isbn_livro", nullable=false)
    private String campoIsbnLivro;
    
    @Column(name="ano_livro")
    private String campoAnoLivro;
    
    @Column(name="edicao_livro")
    private String campoEdicao;
    
    @Column(name="editora_livro")
    private String campoEditora;
    
    @Column(name="autor_livro")
    private String campoAutor;
    
    @Column(name="quantidade_livro", nullable=false)
    private String campoQuantidadeLivro;

    public DBBibliotecaConfig() {
    }

    public DBDriver getDriver() {
        return driver;
    }

    public void setDriver(DBDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getNameDataBase() {
        return nameDataBase;
    }

    public void setNameDataBase(String nameDataBase) {
        this.nameDataBase = nameDataBase;
    }

    public String getUserDataBase() {
        return userDataBase;
    }

    public void setUserDataBase(String userDataBase) {
        this.userDataBase = userDataBase;
    }

    public byte[] getPasswordDataBase() {
        return passwordDataBase;
    }

    public void setPasswordDataBase(byte[] passwordDataBase) {
        this.passwordDataBase = passwordDataBase;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getCampoIdLivroBiblioteca() {
        return campoIdLivroBiblioteca;
    }

    public void setCampoIdLivroBiblioteca(String campoIdLivroBiblioteca) {
        this.campoIdLivroBiblioteca = campoIdLivroBiblioteca;
    }

    public String getCampoTituloLivro() {
        return campoTituloLivro;
    }

    public void setCampoTituloLivro(String campoTituloLivro) {
        this.campoTituloLivro = campoTituloLivro;
    }

    public String getCampoIsbnLivro() {
        return campoIsbnLivro;
    }

    public void setCampoIsbnLivro(String campoIsbnLivro) {
        this.campoIsbnLivro = campoIsbnLivro;
    }

    public String getCampoAnoLivro() {
        return campoAnoLivro;
    }

    public void setCampoAnoLivro(String campoAnoLivro) {
        this.campoAnoLivro = campoAnoLivro;
    }

    public String getCampoEdicao() {
        return campoEdicao;
    }

    public void setCampoEdicao(String campoEdicao) {
        this.campoEdicao = campoEdicao;
    }

    public String getCampoEditora() {
        return campoEditora;
    }

    public void setCampoEditora(String campoEditora) {
        this.campoEditora = campoEditora;
    }

    public String getCampoAutor() {
        return campoAutor;
    }

    public void setCampoAutor(String campoAutor) {
        this.campoAutor = campoAutor;
    }

    public String getCampoQuantidadeLivro() {
        return campoQuantidadeLivro;
    }

    public void setCampoQuantidadeLivro(String campoQuantidadeLivro) {
        this.campoQuantidadeLivro = campoQuantidadeLivro;
    }

    @Override
    public String toString() {
        return "DBBibliotecaConfig{" + "driver=" + driver + ", url=" + url + 
                ", porta=" + porta + ", nameDataBase=" + nameDataBase + 
                ", userDataBase=" + userDataBase + ", passwordDataBase=" + 
                passwordDataBase + ", tabela=" + tabela + ", campoIdLivroBiblioteca=" + 
                campoIdLivroBiblioteca + ", campoTituloLivro=" + campoTituloLivro + 
                ", campoIsbnLivro=" + campoIsbnLivro + ", campoAnoLivro=" + 
                campoAnoLivro + ", campoEdicao=" + campoEdicao + ", campoEditora=" + 
                campoEditora + ", campoAutor=" + campoAutor + ", "
                + "campoQuantidadeLivro=" + campoQuantidadeLivro + '}';
    }
}