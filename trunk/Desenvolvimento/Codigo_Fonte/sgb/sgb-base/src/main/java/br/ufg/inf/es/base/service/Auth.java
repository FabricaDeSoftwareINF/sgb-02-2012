package br.ufg.inf.es.base.service;

import java.io.Serializable;
import java.util.Collection;

/**
 * Inteface que define os métodos para Autenticação no sistema.
 * 
 * @author Cézar Augusto Ferreira
 */
public interface Auth extends Serializable {
    
    /**
     * Método que realiza o login do usuário;
     *
     * @param user
     * @param password
     * @return Collection<String>
     */
    Collection<String> login(String user, String password);
}