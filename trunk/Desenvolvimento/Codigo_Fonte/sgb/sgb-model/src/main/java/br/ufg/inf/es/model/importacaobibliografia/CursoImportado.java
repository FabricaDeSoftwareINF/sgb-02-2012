/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model.importacaobibliografia;

import java.util.List;

/**
 *
 * @author Vinícius
 */
public class CursoImportado {
    
    private String nome;
    private int vagas;
    private List<DisciplinaImportada> disciplinas;

    public CursoImportado() {
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

    public List<DisciplinaImportada> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<DisciplinaImportada> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public String toString() {
        return "CursoImportado{" + "nome=" + nome + ", vagas=" + vagas 
                + ", disciplinas=" + disciplinas + '}';
    }
    
    
}
