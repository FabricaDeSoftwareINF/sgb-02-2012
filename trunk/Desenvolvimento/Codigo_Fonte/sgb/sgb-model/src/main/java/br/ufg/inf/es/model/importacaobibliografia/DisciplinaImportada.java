/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model.importacaobibliografia;

/**
 *
 * @author Vinícius
 */
public class DisciplinaImportada {
    
    private String nome;
    private String codigo;
    private BibliografiaImportada bibliografia;

    public DisciplinaImportada() {
    }

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

    public BibliografiaImportada getBibliografia() {
        return bibliografia;
    }

    public void setBibliografia(BibliografiaImportada bibliografia) {
        this.bibliografia = bibliografia;
    }

    @Override
    public String toString() {
        return "DisciplinaImportada{" + "nome=" + nome + ", codigo=" 
                + codigo + ", bibliografia=" + bibliografia + '}';
    }

}
