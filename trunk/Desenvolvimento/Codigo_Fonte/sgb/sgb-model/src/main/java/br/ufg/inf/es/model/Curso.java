package br.ufg.inf.es.model;

import br.ufg.inf.es.base.model.annotations.OrderingProperty;
import br.ufg.inf.es.base.model.annotations.SortOrder;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Diogo Gon&ccedil;alves Teodoro
 *
 */
@Entity
@Table(name = "CURSO")
public class Curso extends AbstractEntityModel {

    @OrderingProperty(sortOrder = SortOrder.ASC)
    @Column(name="nome")
    private String nome;
    @Column(name="vagas")
    private int vagas;
    @OneToMany(targetEntity=Disciplina.class)
    private Collection<Disciplina> disciplinas;

    public Collection<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Collection<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }
}