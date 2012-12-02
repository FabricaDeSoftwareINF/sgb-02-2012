package br.ufg.inf.es.base.service;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Cézar Augusto Ferreira
 */
public interface Auth extends Serializable {
    
    Collection<String> login(String user, String password);
}