package br.ufg.inf.es.model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DISCIPLINA")
public class Disciplina extends AbstractEntityModel{
    
    @Column(name="nome")
    private String nome;
    @Column(name="codigo")
    private String codigo;
    @ManyToOne
    @JoinColumn(name="id_curso")
    private Curso curso;
    @OneToMany(mappedBy="disciplina")
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
