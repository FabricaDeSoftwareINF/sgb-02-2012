/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author cezar
 */
@Entity
@Table(name = "sgb_livros")
public class Disciplina extends AbstractEntityModel{
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
