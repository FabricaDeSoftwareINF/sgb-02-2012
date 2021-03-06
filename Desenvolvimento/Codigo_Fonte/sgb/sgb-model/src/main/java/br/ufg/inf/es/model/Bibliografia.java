package br.ufg.inf.es.model;

import br.ufg.inf.es.enuns.EnumTipoBibliografia;
import javax.persistence.*;

/**
 * Entidade Bibliografia
 *
 * @author Henrique, Marco Aurélio, Cássio Augusto
 */
@Entity
@Table(name = "BIBLIOGRAFIA")
public class Bibliografia extends AbstractEntityModel {

    /**
     * Campo tipo
     */
    @Column(name = "tipo", unique = false)
    @Enumerated(EnumType.STRING)
    private EnumTipoBibliografia tipo;
    /**
     * Campo livro
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_livro")
    private Livro livro;
    /**
     * Campo disciplina
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;
    /**
     * Campo SALTO
     */
    private static final int SALTO = 83;
    /**
     * Campo HASH
     */
    private static final int HASH = 7;

    /**
     * Obtém o valor do campo
     * <code>tipo</code>
     *
     * @return {@link EnumTipoBibliografia}
     */
    public EnumTipoBibliografia getTipo() {

        return this.tipo;
    }

    /**
     * Define o campo
     * <code>tipo</code>.
     *
     * @param tipo
     */
    public void setTipo(EnumTipoBibliografia tipo) {

        this.tipo = tipo;
    }

    /**
     * Obtém o valor do campo
     * <code>livro</code>
     *
     * @return {@link Livro}
     */
    public Livro getLivro() {

        return this.livro;
    }

    /**
     * Define o campo
     * <code>livro</code>.
     *
     * @param livro
     */
    public void setLivro(Livro livro) {

        this.livro = livro;
    }

    /**
     * Obtém o valor do campo
     * <code>disciplina</code>
     *
     * @return {@link Disciplina}
     */
    public Disciplina getDisciplina() {

        return this.disciplina;
    }

    /**
     * Define o campo
     * <code>disciplina</code>.
     *
     * @param disciplina
     */
    public void setDisciplina(Disciplina disciplina) {

        this.disciplina = disciplina;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Bibliografia{" + "tipo=" + tipo + ", livro=" + livro + ", disciplina=" + disciplina + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bibliografia other = (Bibliografia) obj;
        if (this.livro != other.livro && (this.livro == null || !this.livro.equals(other.livro))) {
            return false;
        }
        if (this.disciplina != other.disciplina && (this.disciplina == null || !this.disciplina.equals(other.disciplina))) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        int hash = Bibliografia.HASH;

        hash = Bibliografia.SALTO * hash + (this.livro != null ? this.livro.hashCode() : 0);
        hash = Bibliografia.SALTO * hash + (this.disciplina != null ? this.disciplina.hashCode() : 0);

        return hash;
    }
}
