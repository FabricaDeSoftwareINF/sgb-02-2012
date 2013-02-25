package br.ufg.inf.es.model;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;

/**
 * Entidade Disciplina
 *
 * @author Marco Camargo
 */
@Entity
@Table(name = "DISCIPLINA")
public class Disciplina extends AbstractEntityModel {

    /**
     * Campo nome
     */
    @Column(name = "nome")
    private String nome;
    /**
     * Campo codigo
     */
    @Column(name = "codigo")
    private String codigo;
    /**
     * Campo curso
     */
    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;
    /**
     * Campo bibliografias
     */
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Collection<Bibliografia> bibliografias = new ArrayList<Bibliografia>();

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
     * <code>codigo</code>
     *
     * @return {@link String}
     */
    public String getCodigo() {

        return this.codigo;
    }

    /**
     * Define o campo
     * <code>codigo</code>.
     *
     * @param codigo
     */
    public void setCodigo(String codigo) {

        this.codigo = codigo;
    }

    /**
     * Obtém o valor do campo
     * <code>curso</code>
     *
     * @return {@link Curso}
     */
    public Curso getCurso() {

        return this.curso;
    }

    /**
     * Define o campo
     * <code>curso</code>.
     *
     * @param curso
     */
    public void setCurso(Curso curso) {

        this.curso = curso;
    }

    /**
     * Obtém o valor do campo
     * <code>bibliografias</code>
     *
     * @return {@link Collection<Bibliografia>}
     */
    public Collection<Bibliografia> getBibliografias() {

        return this.bibliografias;
    }

    /**
     * Define o campo
     * <code>bibliografias</code>.
     *
     * @param bibliografias
     */
    public void setBibliografias(Collection<Bibliografia> bibliografias) {

        this.bibliografias = bibliografias;
    }

    @Override
    public String toString() {

        return "Disciplina{" + "nome=" + nome + ", codigo=" + codigo + '}';
    }
}
