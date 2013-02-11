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
   
	/** Campo TAMANHO_KEY*/
	@Transient
    private static final int TAMANHO_KEY = 16;
    
    /** Campo TAMANHO_SENHA*/
    private static final int TAMANHO_SENHA = 300;
   
    /** Campo driver*/
    @Enumerated(EnumType.STRING)
    @Column(name="tipo", nullable=false)
    private DBDriver driver;   
    
    /** Campo url*/
    @Column(name="url", nullable=false)
    private String url;
    
    /** Campo porta*/
    @Column(name="porta", nullable=false)
    private String porta;
    
    /** Campo nameDataBase*/
    @Column(name="nome_database", nullable=false)
    private String nameDataBase;
    
    /** Campo userDataBase*/
    @Column(name="user_database", nullable=false)
    private String userDataBase;
    
    /** Campo passwordDataBase*/
    @Column(name="password_database", length=DBBibliotecaConfig.TAMANHO_SENHA, nullable=false)
    private byte[] passwordDataBase;
    
    /** Campo tabela*/
    @Column(name="tabela", nullable=false)
    private String tabela;
    
    /** Campo campoIdLivroBiblioteca*/
    @Column(name="id_livro")
    private String campoIdLivroBiblioteca;
    
    /** Campo campoTituloLivro*/
    @Column(name="titulo_livro", nullable=false)
    private String campoTituloLivro;
    
    /** Campo campoIsbnLivro*/
    @Column(name="isbn_livro", nullable=false)
    private String campoIsbnLivro;
    
    /** Campo campoAnoLivro*/
    @Column(name="ano_livro")
    private String campoAnoLivro;
    
    /** Campo campoEdicao*/
    @Column(name="edicao_livro")
    private String campoEdicao;
    
    /** Campo campoEditora*/
    @Column(name="editora_livro")
    private String campoEditora;
    
    /** Campo campoAutor*/
    @Column(name="autor_livro")
    private String campoAutor;
    
    /** Campo campoQuantidadeLivro*/
    @Column(name="quantidade_livro", nullable=false)
    private String campoQuantidadeLivro;

    /**
     * Construtor desta classe.
     */
    public DBBibliotecaConfig() {
    }

    /**
	 * Obtém o valor do campo <code>driver</code>
	 *
	 * @return {@link DBDriver}
	 */
	public DBDriver getDriver() {
		return this.driver;
	}

	/**
	 * Define o campo <code>driver</code>.
	 *
	 * @param driver 
	 */
	public void setDriver(DBDriver driver) {
		this.driver = driver;
	}

	/**
	 * Obtém o valor do campo <code>url</code>
	 *
	 * @return {@link String}
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * Define o campo <code>url</code>.
	 *
	 * @param url 
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Obtém o valor do campo <code>porta</code>
	 *
	 * @return {@link String}
	 */
	public String getPorta() {
		return this.porta;
	}

	/**
	 * Define o campo <code>porta</code>.
	 *
	 * @param porta 
	 */
	public void setPorta(String porta) {
		this.porta = porta;
	}

	/**
	 * Obtém o valor do campo <code>nameDataBase</code>
	 *
	 * @return {@link String}
	 */
	public String getNameDataBase() {
		return this.nameDataBase;
	}

	/**
	 * Define o campo <code>nameDataBase</code>.
	 *
	 * @param nameDataBase 
	 */
	public void setNameDataBase(String nameDataBase) {
		this.nameDataBase = nameDataBase;
	}

	/**
	 * Obtém o valor do campo <code>userDataBase</code>
	 *
	 * @return {@link String}
	 */
	public String getUserDataBase() {
		return this.userDataBase;
	}

	/**
	 * Define o campo <code>userDataBase</code>.
	 *
	 * @param userDataBase 
	 */
	public void setUserDataBase(String userDataBase) {
		this.userDataBase = userDataBase;
	}

	/**
	 * Obtém o valor do campo <code>passwordDataBase</code>
	 *
	 * @return {@link byte[]}
	 */
	public byte[] getPasswordDataBase() {
		return this.passwordDataBase;
	}

	/**
	 * Define o campo <code>passwordDataBase</code>.
	 *
	 * @param passwordDataBase 
	 */
	public void setPasswordDataBase(byte[] passwordDataBase) {
		this.passwordDataBase = passwordDataBase;
	}

	/**
	 * Obtém o valor do campo <code>tabela</code>
	 *
	 * @return {@link String}
	 */
	public String getTabela() {
		return this.tabela;
	}

	/**
	 * Define o campo <code>tabela</code>.
	 *
	 * @param tabela 
	 */
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}

	/**
	 * Obtém o valor do campo <code>campoIdLivroBiblioteca</code>
	 *
	 * @return {@link String}
	 */
	public String getCampoIdLivroBiblioteca() {
		return this.campoIdLivroBiblioteca;
	}

	/**
	 * Define o campo <code>campoIdLivroBiblioteca</code>.
	 *
	 * @param campoIdLivroBiblioteca 
	 */
	public void setCampoIdLivroBiblioteca(String campoIdLivroBiblioteca) {
		this.campoIdLivroBiblioteca = campoIdLivroBiblioteca;
	}

	/**
	 * Obtém o valor do campo <code>campoTituloLivro</code>
	 *
	 * @return {@link String}
	 */
	public String getCampoTituloLivro() {
		return this.campoTituloLivro;
	}

	/**
	 * Define o campo <code>campoTituloLivro</code>.
	 *
	 * @param campoTituloLivro 
	 */
	public void setCampoTituloLivro(String campoTituloLivro) {
		this.campoTituloLivro = campoTituloLivro;
	}

	/**
	 * Obtém o valor do campo <code>campoIsbnLivro</code>
	 *
	 * @return {@link String}
	 */
	public String getCampoIsbnLivro() {
		return this.campoIsbnLivro;
	}

	/**
	 * Define o campo <code>campoIsbnLivro</code>.
	 *
	 * @param campoIsbnLivro 
	 */
	public void setCampoIsbnLivro(String campoIsbnLivro) {
		this.campoIsbnLivro = campoIsbnLivro;
	}

	/**
	 * Obtém o valor do campo <code>campoAnoLivro</code>
	 *
	 * @return {@link String}
	 */
	public String getCampoAnoLivro() {
		return this.campoAnoLivro;
	}

	/**
	 * Define o campo <code>campoAnoLivro</code>.
	 *
	 * @param campoAnoLivro 
	 */
	public void setCampoAnoLivro(String campoAnoLivro) {
		this.campoAnoLivro = campoAnoLivro;
	}

	/**
	 * Obtém o valor do campo <code>campoEdicao</code>
	 *
	 * @return {@link String}
	 */
	public String getCampoEdicao() {
		return this.campoEdicao;
	}

	/**
	 * Define o campo <code>campoEdicao</code>.
	 *
	 * @param campoEdicao 
	 */
	public void setCampoEdicao(String campoEdicao) {
		this.campoEdicao = campoEdicao;
	}

	/**
	 * Obtém o valor do campo <code>campoEditora</code>
	 *
	 * @return {@link String}
	 */
	public String getCampoEditora() {
		return this.campoEditora;
	}

	/**
	 * Define o campo <code>campoEditora</code>.
	 *
	 * @param campoEditora 
	 */
	public void setCampoEditora(String campoEditora) {
		this.campoEditora = campoEditora;
	}

	/**
	 * Obtém o valor do campo <code>campoAutor</code>
	 *
	 * @return {@link String}
	 */
	public String getCampoAutor() {
		return this.campoAutor;
	}

	/**
	 * Define o campo <code>campoAutor</code>.
	 *
	 * @param campoAutor 
	 */
	public void setCampoAutor(String campoAutor) {
		this.campoAutor = campoAutor;
	}

	/**
	 * Obtém o valor do campo <code>campoQuantidadeLivro</code>
	 *
	 * @return {@link String}
	 */
	public String getCampoQuantidadeLivro() {
		return this.campoQuantidadeLivro;
	}

	/**
	 * Define o campo <code>campoQuantidadeLivro</code>.
	 *
	 * @param campoQuantidadeLivro 
	 */
	public void setCampoQuantidadeLivro(String campoQuantidadeLivro) {
		this.campoQuantidadeLivro = campoQuantidadeLivro;
	}

	/** 
	 * {@inheritDoc} 
	 */
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