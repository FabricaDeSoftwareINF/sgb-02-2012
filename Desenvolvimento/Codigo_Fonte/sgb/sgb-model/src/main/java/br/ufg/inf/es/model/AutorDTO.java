/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

/**
 * Data Transfer Object da Entidade Autor.
 * Está entidade serve para manipularmos os dados vindo do Banco de dados sem 
 * trabalharmos diretamente com a Entidade
 * @author Cassio
 */
public class AutorDTO extends AbstractEntityModel {
    
    private String nome;
    
    private String sobrenome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    
    
}
