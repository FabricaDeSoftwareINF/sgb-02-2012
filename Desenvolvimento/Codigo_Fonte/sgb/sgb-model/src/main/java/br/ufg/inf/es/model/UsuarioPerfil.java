/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

/**
 *
 * @author Geovane Pazine Filho
 */
public enum UsuarioPerfil {

    ADM, DOCENTE, CONSELHEIRO, TECNICO, DOCENTE_CONSELHEIRO, DOCENTE_ADM,
    CONSELHEIRO_ADM, TECNICO_ADM, DOCENTE_CONSELHEIRO_ADM;

    private UsuarioPerfil() {
    }
}
