package br.ufg.inf.es.model;

import java.io.Serializable;
import java.util.*;

/**
 * Enum UsuarioPerfil
 *
 * @author Geovane Pazine Filho, Victor Carvalho
 */
public enum UsuarioPerfil implements Serializable {

    /**
     * Campo ADM
     */
    ADM("ADM"),
    /**
     * Campo DOCENTE
     */
    COORDENADOR("COORDENADOR"),
    /**
     * Campo DOCENTE
     */
    DOCENTE("DOCENTE"),
    /**
     * Campo CONSELHEIRO
     */
    CONSELHEIRO("CONSELHEIRO"),
    /**
     * Campo TECNICO
     */
    TECNICO("TECNICO");
    
    private String name;

    /**
     * Construtor desta classe.
     *
     * @param tipo
     */
    private UsuarioPerfil(String tipo) {

        this.name = tipo;
    }

    /**
     * Obtém o valor do campo
     * <code>name</code>
     *
     * @return {@link String}
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtem os papeis do usuario de acordo com o UsuarioPerfil
     *
     * @param role nome do papel
     * @return lista com os nomes do papeis
     */
    public Collection<String> getRoles() {
        Collection<String> roles = null;

        switch (this) {
            case ADM:
                roles = Arrays.asList(ADM.name());
                break;
            case DOCENTE:
                roles = Arrays.asList(DOCENTE.name());
                break;
            case CONSELHEIRO:
                roles = Arrays.asList(CONSELHEIRO.name());
                break;
            case TECNICO:
                roles = Arrays.asList(TECNICO.name());
                break;
        }

        return roles;
    }
}