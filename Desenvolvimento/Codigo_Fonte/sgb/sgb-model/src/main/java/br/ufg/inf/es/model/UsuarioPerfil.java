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

    private long idUsuario;
    private long idPerfil;

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(long idPerfil) {
        this.idPerfil = idPerfil;
    }
}
