package br.ufg.inf.es.model;

import br.ufg.inf.es.base.model.annotations.OrderingProperty;
import br.ufg.inf.es.base.model.annotations.SortOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.hibernate.collection.PersistentBag;

/**
 *
 * @author cézar Augusto Ferreira e Luã Silvério
 */
@Entity
@Table(name = "USUARIO")
public class Usuario extends AbstractEntityModel {
    
    private String senha;
    private String email;
    @Transient
    private String confirmacaoEmail;
    @OrderingProperty(sortOrder = SortOrder.ASC)
    private String nome;
    private String sobrenome;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro;
    private boolean status;
    @Transient
    private Collection<Perfil> perfil = new HashSet<Perfil>();

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
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Collection<Perfil> getPerfil() {
        return perfil;
    }

    public void setPerfil(Collection<Perfil> perfil) {
        this.perfil = perfil;
    }
    
    public String getStringPerfil() {
        String retorno = "";
        for (Perfil p : this.perfil) {
            retorno += p.getTipo() + "; ";
        }
        return retorno;
    }
    
    public String getConfirmacaoEmail() {
        return confirmacaoEmail;
    }

    public void setConfirmacaoEmail(String confirmacaoEmail) {
        this.confirmacaoEmail = confirmacaoEmail;
    }

}