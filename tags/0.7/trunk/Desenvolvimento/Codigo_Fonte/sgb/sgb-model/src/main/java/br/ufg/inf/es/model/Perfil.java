/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Luã
 */
@Entity
@Table(name = "PERFIL")
public class Perfil extends AbstractEntityModel {
    
    private String tipo;

    public Perfil(){ }
    
    public Perfil(String tipo){
        this.tipo = tipo;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}