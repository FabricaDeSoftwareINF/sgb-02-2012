/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Luã
 */
@Entity
@Table(name = "PERFIL")
public class Perfil extends AbstractEntityModel {
    @ManyToMany(targetEntity = Usuario.class)
        @JoinTable(name = "USUARIO_PERFIL", joinColumns= {
        @JoinColumn(name = "id_perfil")},
    inverseJoinColumns = {
        @JoinColumn(name="id_usuario")})
    private Set<Usuario> usuarios;
    private String tipo;

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
