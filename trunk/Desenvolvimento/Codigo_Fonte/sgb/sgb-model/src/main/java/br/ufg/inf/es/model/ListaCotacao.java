package br.ufg.inf.es.model;

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
    
    /** Campo cotacoes*/
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="lista_cotacao_id")
    private Collection<CotacoesLivro> cotacoesLivro;
    
    /** Campo valor*/
    private double valor;

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

    /**
     * Obtém o valor do campo <code>valor</code>
     *
     * @return valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o campo <code>valor</code>.
     *
     * @param valor 
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
