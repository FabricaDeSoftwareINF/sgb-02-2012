package br.ufg.inf.es.model;

import javax.persistence.*;

/**
 *
 * @author Henrique, Marco Aurélio
 */
@Entity
@Table(name = "BIBLIOGRAFIA")
public class Bibliografia extends AbstractEntityModel {

    @Column(name = "tipo", unique = false)
    private String tipo;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_livro")
    private Livro livro;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Bibliografia{" + "tipo=" + tipo + ", livro=" + livro + ", disciplina=" + disciplina + '}';
    }
}