package br.ufg.inf.es.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.hibernate.annotations.Cascade;

/**
 * Entidade ListaCotacao
 *
 * @author GeovaneFilho
 */
@Entity
@Table(name = "LISTA_COTACAO")
public class ListaCotacao extends AbstractEntityModel {

    private static final int HASH = 7;
    private static final int SALTO = 13;
    /**
     * Campo nome
     */
    private String nome;
    /**
     * Campo dataRealizada
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataRealizada;
    /**
     * Campo user
     */
    @JoinColumn(name = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario user;
    /**
     * Campo cotacoes
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "LISTA_COTACAO_ITEM_LISTA_COTACAO",
    joinColumns =
    @JoinColumn(name = "id_lista_cotacao"),
    inverseJoinColumns =
    @JoinColumn(name = "id_item_lista_cotacao"))
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Collection<ItemListaCotacao> itensListaCotacao = new ArrayList<ItemListaCotacao>();
    
    @Transient
    private double valor;
    
    

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
     * <code>dataRealizada</code>
     *
     * @return {@link Date}
     */
    public Date getDataRealizada() {

        return dataRealizada != null ? (Date) dataRealizada.clone() : dataRealizada;
    }

    /**
     * Obtém o valor do campo
     * <code>user</code>
     *
     * @return {@link Usuario}
     */
    public Usuario getUser() {
        return user;
    }

    /**
     * Define o campo
     * <code>user</code>.
     *
     * @param user
     */
    public void setUser(Usuario user) {
        this.user = user;
    }

    /**
     * Define o campo
     * <code>dataRealizada</code>.
     *
     * @param dataRealizada
     */
    public void setDataRealizada(Date dataRealizada) {

        this.dataRealizada = dataRealizada != null ? (Date) dataRealizada.clone() : dataRealizada;
    }

    /**
     * Obtém o valor do campo
     * <code>cotacoes</code>
     *
     * @return {@link List<Cotacao>}
     */
    public Collection<ItemListaCotacao> getItensListaCotacao() {
        return this.itensListaCotacao;
    }

    /**
     * Define o campo
     * <code>cotacoes</code>.
     *
     * @param cotacoes
     */
    public void setItensListaCotacao(Collection<ItemListaCotacao> cotacoesLivro) {
        this.itensListaCotacao = cotacoesLivro;
    }

    @Override
    public int hashCode() {
        int hash = HASH;
        hash = SALTO * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = SALTO * hash + (this.dataRealizada != null ? this.dataRealizada.hashCode() : 0);
        hash = SALTO * hash + (this.itensListaCotacao != null ? this.itensListaCotacao.hashCode() : 0);
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
        if (this.itensListaCotacao != other.itensListaCotacao && (this.itensListaCotacao == null || !this.itensListaCotacao.equals(other.itensListaCotacao))) {
            return false;
        }

        return true;
    }

    public double getValor() {
        double valorTotalLista = 0.0;
        DecimalFormat formatador = new DecimalFormat("0.00");
        String strValorComVirgula;
        String[] strPartesValor;
        String strValorComPonto;

        try {

            ArrayList<ItemListaCotacao> cotacoes = new ArrayList<ItemListaCotacao>(getItensListaCotacao());

            for (int i = 0; i < cotacoes.size(); i++) {

                double valorTotalItem;
                valorTotalItem = cotacoes.get(i).getValorMedio()
                        * cotacoes.get(i).getQuantidade();

                strValorComVirgula = formatador.format(valorTotalItem);
                strPartesValor = strValorComVirgula.split("[,]");
                strValorComPonto = strPartesValor[0] + "." + strPartesValor[1];

                valorTotalLista += Double.parseDouble(strValorComPonto);
            }

            strValorComVirgula = formatador.format(valorTotalLista);
            strPartesValor = strValorComVirgula.split("[,]");
            strValorComPonto = strPartesValor[0] + "." + strPartesValor[1];

            valorTotalLista = Double.parseDouble(strValorComPonto);
        } catch (Exception ex) {
            return 0.0;
        }
        
        this.valor = valorTotalLista;
        
        return this.valor;

    }
}
