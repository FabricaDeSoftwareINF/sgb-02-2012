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

    private String senha;
    @Column(nullable = false, unique = true)
    private String email;
    @Transient
    private String confirmacaoEmail;
    @OrderingProperty(sortOrder = SortOrder.ASC)
    private String nome;
    private String sobrenome;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
    private boolean status;
    @Enumerated(EnumType.STRING)
    private UsuarioPerfil perfil;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getDataCadastro() {

        return (Date) dataCadastro.clone();
    }

    public void setDataCadastro(Date dataCadastro) {

        this.dataCadastro = (Date) dataCadastro.clone();
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UsuarioPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(UsuarioPerfil perfil) {
        this.perfil = perfil;
    }

    public String getConfirmacaoEmail() {
        return confirmacaoEmail;
    }

    public void setConfirmacaoEmail(String confirmacaoEmail) {
        this.confirmacaoEmail = confirmacaoEmail;
    }
}