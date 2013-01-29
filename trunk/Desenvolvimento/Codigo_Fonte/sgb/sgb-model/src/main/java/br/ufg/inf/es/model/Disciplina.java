package br.ufg.inf.es.model;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author 
 */
@Entity
@Table(name = "DISCIPLINA")
public class Disciplina extends AbstractEntityModel {

    @Column(name = "nome")
    private String nome;
    
    @Column(name = "codigo")
    private String codigo;
    
    @ManyToOne
    private Curso curso;
    
    @OneToMany(targetEntity = Bibliografia.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Bibliografia> bibliografias;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Collection<Bibliografia> getBibliografias() {
        return bibliografias;
    }

    public void setBibliografias(Collection<Bibliografia> bibliografias) {
        this.bibliografias = bibliografias;
    }
}
