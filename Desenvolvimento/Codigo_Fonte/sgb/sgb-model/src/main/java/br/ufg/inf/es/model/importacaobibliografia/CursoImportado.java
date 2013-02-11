/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model.importacaobibliografia;

import java.util.List;

/**
 * Entidade CursoImportado
 * @author Vinícius
 */
public class CursoImportado {
    
    /** Campo nome*/
    private String nome;
    
    /** Campo vagas*/
    private int vagas;
    
    /** Campo disciplinas*/
    private List<DisciplinaImportada> disciplinas;

    /**
     * Construtor desta classe.
     */
    public CursoImportado() {
    }
    
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
	 * @return {@link int}
	 */
	public int getVagas() {
		return this.vagas;
	}

	/**
	 * Define o campo <code>vagas</code>.
	 *
	 * @param vagas 
	 */
	public void setVagas(int vagas) {
		this.vagas = vagas;
	}

	/**
	 * Obtém o valor do campo <code>disciplinas</code>
	 *
	 * @return {@link List<DisciplinaImportada>}
	 */
	public List<DisciplinaImportada> getDisciplinas() {
		return this.disciplinas;
	}

	/**
	 * Define o campo <code>disciplinas</code>.
	 *
	 * @param disciplinas 
	 */
	public void setDisciplinas(List<DisciplinaImportada> disciplinas) {
		this.disciplinas = disciplinas;
	}

	/** 
	 * {@inheritDoc} 
	 */
	@Override
    public String toString() {
        return "CursoImportado{" + "nome=" + nome + ", vagas=" + vagas 
                + ", disciplinas=" + disciplinas + '}';
    }
    
    
}
