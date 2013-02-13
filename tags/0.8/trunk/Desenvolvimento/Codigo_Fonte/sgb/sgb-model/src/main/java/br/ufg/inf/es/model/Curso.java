package br.ufg.inf.es.model;

import br.ufg.inf.es.base.model.annotations.OrderingProperty;
import br.ufg.inf.es.base.model.annotations.SortOrder;
import java.util.Collection;
import javax.persistence.*;

/**
 * Classe que representa a entidade Curso.
 *
 * @author Diogo Gon&ccedil;alves Teodoro
 *
 */
@Entity
@Table(name = "CURSO")
public class Curso extends AbstractEntityModel {

    /** Campo nome*/
    @OrderingProperty(sortOrder = SortOrder.ASC)
    @Column(name = "nome")
    private String nome;
    
    /** Campo vagas*/
    @Column(name = "vagas")
    private Integer vagas;
    
    /** Campo disciplinas*/
    @OneToMany(mappedBy="curso", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Disciplina> disciplinas;

	/**
	 * Obtém o valor do campo <code>nome</code>
	 *
	 * @return {@link String}
	 */
	public String getNome() {
		
		return this.nome;
	}

	/**
	 * Define o campo <code>nome</code>.
	 *
	 * @param nome 
	 */
	public void setNome(String nome) {
		
		this.nome = nome;
	}

	/**
	 * Obtém o valor do campo <code>vagas</code>
	 *
	 * @return {@link Integer}
	 */
	public Integer getVagas() {
		
		return this.vagas;
	}

	/**
	 * Define o campo <code>vagas</code>.
	 *
	 * @param vagas 
	 */
	public void setVagas(Integer vagas) {
		
		this.vagas = vagas;
	}

	/**
	 * Obtém o valor do campo <code>disciplinas</code>
	 *
	 * @return {@link Collection<Disciplina>}
	 */
	public Collection<Disciplina> getDisciplinas() {
		
		return this.disciplinas;
	}

	/**
	 * Define o campo <code>disciplinas</code>.
	 *
	 * @param disciplinas 
	 */
	public void setDisciplinas(Collection<Disciplina> disciplinas) {
		
		this.disciplinas = disciplinas;
	}    
}
