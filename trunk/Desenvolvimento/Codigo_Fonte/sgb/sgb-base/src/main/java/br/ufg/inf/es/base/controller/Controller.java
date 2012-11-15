package br.ufg.inf.es.base.controller;

import java.io.Serializable;

/**
 * @author Cézar Augusto Ferreira
 */
public interface Controller extends Serializable {
    
    void initData();
    
    void openInitialView();
}