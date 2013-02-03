/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import java.io.Serializable;

/**
 *
 * @author Geovane Pazine Filho
 */
public enum UsuarioPerfil implements Serializable {

    ADM("ADM"), DOCENTE("DOCENTE"), CONSELHEIRO("CONSELHEIRO"),
    TECNICO("TECNICO"), DOCENTE_CONSELHEIRO("DOCENTE_CONSELHEIRO"),
    DOCENTE_ADM("DOCENTE_ADM"), CONSELHEIRO_ADM("CONSELHEIRO_ADM"),
    TECNICO_ADM("TECNICO_ADM"),
    DOCENTE_CONSELHEIRO_ADM("DOCENTE_CONSELHEIRO_ADM");
    private String name;

    private UsuarioPerfil(String tipo) {
        this.name = tipo;
    }

    public String getName() {
        return this.name;
    }
}
