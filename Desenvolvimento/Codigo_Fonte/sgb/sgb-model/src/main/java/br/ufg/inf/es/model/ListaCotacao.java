package br.ufg.inf.es.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author GeovaneFilho
 */
@Entity
public class ListaCotacao extends AbstractEntityModel {

    private String nome;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataRealizada;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Cotacao> cotacoes;

    public List<Cotacao> getCotacoes() {
        return cotacoes;
    }

    public void setCotacoes(List<Cotacao> cotacoes) {
        this.cotacoes = cotacoes;
    }

    public Date getDataRealizada() {
        return dataRealizada;
    }

    public void setDataRealizada(Date dataRealizada) {
        this.dataRealizada = dataRealizada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
