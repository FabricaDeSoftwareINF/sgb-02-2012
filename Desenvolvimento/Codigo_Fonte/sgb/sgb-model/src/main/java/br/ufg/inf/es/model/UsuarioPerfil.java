/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import java.io.Serializable;

/**
 * Enum UsuarioPerfil
 * @author Geovane Pazine Filho
 */
public enum UsuarioPerfil implements Serializable {

    /** Campo ADM*/
    ADM("ADM"),
    
    /** Campo DOCENTE*/
    DOCENTE("DOCENTE"),
    
    /** Campo CONSELHEIRO*/
    CONSELHEIRO("CONSELHEIRO"),
        
    /** Campo TECNICO*/
    TECNICO("TECNICO"),
    
    /** Campo DOCENTE_CONSELHEIRO*/
    DOCENTE_CONSELHEIRO("DOCENTE_CONSELHEIRO"),
        
    /** Campo DOCENTE_ADM*/
    DOCENTE_ADM("DOCENTE_ADM"),
    
    /** Campo CONSELHEIRO_ADM*/
    CONSELHEIRO_ADM("CONSELHEIRO_ADM"),
    
    /** Campo TECNICO_ADM*/
    TECNICO_ADM("TECNICO_ADM"),
    
    /** Campo DOCENTE_CONSELHEIRO_ADM*/
    DOCENTE_CONSELHEIRO_ADM("DOCENTE_CONSELHEIRO_ADM");
    
    /** Campo name*/
    private String name;

    /**
     * Construtor desta classe.
     * @param tipo
     */
    private UsuarioPerfil(String tipo) {
    	
        this.name = tipo;
    }

    /**
        * Obtém o valor do campo <code>name</code>
        *
        * @return {@link String}
        */
    public String getName() {
            return this.name;
    }
    
}
