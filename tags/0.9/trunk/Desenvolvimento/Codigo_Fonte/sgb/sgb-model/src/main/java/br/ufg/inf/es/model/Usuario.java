package br.ufg.inf.es.model;

import br.ufg.inf.es.base.model.annotations.OrderingProperty;
import br.ufg.inf.es.base.model.annotations.SortOrder;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author cézar Augusto Ferreira e Luã Silvério
 */
@Entity
@Table(name = "USUARIO")
public class Usuario extends AbstractEntityModel {

    /**
     * Campo senha
     */
    private String senha;
    /**
     * Campo email
     */
    @Column(nullable = false, unique = true)
    private String email;
    /**
     * Campo confirmacaoEmail
     */
    @Transient
    private String confirmacaoEmail;
    /**
     * Campo nome
     */
    @OrderingProperty(sortOrder = SortOrder.ASC)
    private String nome;
    /**
     * Campo sobrenome
     */
    private String sobrenome;
    /**
     * Campo dataCadastro
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
    /**
     * Campo status
     */
    private boolean status;
    /**
     * Campo perfil
     */
    @Enumerated(EnumType.STRING)
    private UsuarioPerfil perfil;

    /**
     * Obtém o valor do campo
     * <code>dataCadastros</code>
     *
     * @return {@link Date}
     */
    public Date getDataCadastro() {

        return (Date) dataCadastro.clone();
    }

    /**
     * Define o campo
     * <code>dataCadastro</code>.
     *
     * @param senha
     */
    public void setDataCadastro(Date dataCadastro) {

        this.dataCadastro = (Date) dataCadastro.clone();
    }

    /**
     * Obtém o valor do campo
     * <code>senha</code>
     *
     * @return {@link String}
     */
    public String getSenha() {
        return this.senha;
    }

    /**
     * Define o campo
     * <code>senha</code>.
     *
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Obtém o valor do campo
     * <code>email</code>
     *
     * @return {@link String}
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Define o campo
     * <code>email</code>.
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém o valor do campo
     * <code>confirmacaoEmail</code>
     *
     * @return {@link String}
     */
    public String getConfirmacaoEmail() {
        return this.confirmacaoEmail;
    }

    /**
     * Define o campo
     * <code>confirmacaoEmail</code>.
     *
     * @param confirmacaoEmail
     */
    public void setConfirmacaoEmail(String confirmacaoEmail) {
        this.confirmacaoEmail = confirmacaoEmail;
    }

    /**
     * Obtém o valor do campo
     * <code>nome</code>
     *
     * @return {@link String}
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Define o campo
     * <code>nome</code>.
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o valor do campo
     * <code>sobrenome</code>
     *
     * @return {@link String}
     */
    public String getSobrenome() {
        return this.sobrenome;
    }

    /**
     * Define o campo
     * <code>sobrenome</code>.
     *
     * @param sobrenome
     */
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    /**
     * Obtém o valor do campo
     * <code>status</code>
     *
     * @return {@link boolean}
     */
    public boolean getStatus() {
        return this.status;
    }

    /**
     * Define o campo
     * <code>status</code>.
     *
     * @param status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Obtém o valor do campo
     * <code>perfil</code>
     *
     * @return {@link UsuarioPerfil}
     */
    public UsuarioPerfil getPerfil() {
        return this.perfil;
    }

    /**
     * Define o campo
     * <code>perfil</code>.
     *
     * @param perfil
     */
    public void setPerfil(UsuarioPerfil perfil) {
        this.perfil = perfil;
    }
}