package br.ufg.inf.es.model;

import br.ufg.inf.es.enuns.EnumTipoBibliografia;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Henrique, Marco Aurélio, Cássio Augusto
 */
@Entity
@Table(name = "BIBLIOGRAFIA")
public class Bibliografia extends AbstractEntityModel {

    @Column(name = "tipo", unique = false)
    @Enumerated(EnumType.STRING)
    private EnumTipoBibliografia tipo;
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

    public EnumTipoBibliografia getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipoBibliografia tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Bibliografia{" + "tipo=" + tipo + ", livro=" + livro + ", disciplina=" + disciplina + '}';
    }
}
