package br.ufg.inf.es.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * Entidade ListaCotacao
 * @author GeovaneFilho
 */
@Entity
@Table(name = "LISTA_COTACAO")
public class ListaCotacao extends AbstractEntityModel {

    /** Campo nome*/
    private String nome;
    
    /** Campo dataRealizada*/
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataRealizada;
    
    /** Campo user*/
    @JoinColumn(name="id_usuario")
    @ManyToOne(optional = false)
    private Usuario user;
    
    /** Campo cotacoes*/
    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
    @JoinTable(
            name="LISTACOTACAO_COTACOESLIVRO",
            joinColumns = @JoinColumn( name="id_lista_cotacao"),
            inverseJoinColumns = @JoinColumn( name="id_cotacoes_livro")
    )
    private Collection<CotacoesLivro> cotacoesLivro =  new ArrayList<CotacoesLivro>();

    /**
     * Obtém o valor do campo <code>nome</code>
     *
     * @return {@link String}
     */
    public String getNome() {
            return this.nome;
    }

    /**
     * Define o campo <code>nome</code>.
     *
     * @param nome 
     */
    public void setNome(String nome) {
            this.nome = nome;
    }

    /**
     * Obtém o valor do campo <code>dataRealizada</code>
     *
     * @return {@link Date}
     */
    public Date getDataRealizada() {
        
            return dataRealizada != null ? (Date) dataRealizada.clone() : dataRealizada;
    }

    /**
     * Obtém o valor do campo <code>user</code>
     *
     * @return {@link Usuario}
     */
    public Usuario getUser() {
        return user;
    }

    /**
     * Define o campo <code>user</code>.
     *
     * @param user 
     */
    public void setUser(Usuario user) {
        this.user = user;
    }
    

    /**
     * Define o campo <code>dataRealizada</code>.
     *
     * @param dataRealizada 
     */
    public void setDataRealizada(Date dataRealizada) {
        
            this.dataRealizada = dataRealizada != null ? (Date) dataRealizada.clone() : dataRealizada;
    }

    /**
     * Obtém o valor do campo <code>cotacoes</code>
     *
     * @return {@link List<Cotacao>}
     */
    public Collection<CotacoesLivro> getCotacoesLivro() {
            return this.cotacoesLivro;
    }

    /**
     * Define o campo <code>cotacoes</code>.
     *
     * @param cotacoes 
     */
    public void setCotacoesLivro(Collection<CotacoesLivro> cotacoesLivro) {
            this.cotacoesLivro = cotacoesLivro;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 13 * hash + (this.dataRealizada != null ? this.dataRealizada.hashCode() : 0);
        hash = 13 * hash + (this.cotacoesLivro != null ? this.cotacoesLivro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ListaCotacao other = (ListaCotacao) obj;
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if (this.dataRealizada != other.dataRealizada && (this.dataRealizada == null || !this.dataRealizada.equals(other.dataRealizada))) {
            return false;
        }
        if (this.cotacoesLivro != other.cotacoesLivro && (this.cotacoesLivro == null || !this.cotacoesLivro.equals(other.cotacoesLivro))) {
            return false;
        }

        return true;
    }
    
    public double getValor() {

        double valor = 0;
        ArrayList<CotacoesLivro> cotacoes = 
                new ArrayList<CotacoesLivro>(getCotacoesLivro());

        for (int i = 0; i < cotacoes.size(); i++) {
            valor += cotacoes.get(i).getValorMedio() * cotacoes.get(i).getQuantidade() ;
        }

        return valor;
       
    }
       
}
