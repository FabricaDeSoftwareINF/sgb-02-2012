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
@Table(name = "PERFIL_USUARIO")
public class UsuarioPerfil extends AbstractEntityModel {

    private long id_usuario;
    private long id_perfil;

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public long getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(long id_perfil) {
        this.id_perfil = id_perfil;
    }
}
