package br.ufg.inf.es.model;

import java.util.Collection;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Jackeline Neves
 */
@Entity
@Table(name = "LISTA_COMPRAS")
public class ListaCompras extends AbstractEntityModel {

    @Column(name = "nome")
    private String nome;
    @Column(name = "data_criacao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCriacao;

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
    
}
