package br.ufg.inf.es.base.controller;

import java.io.Serializable;

/**
 * Interface que define um Controlador.
 * 
 * @author Cézar Augusto Ferreira
 */
public interface Controller extends Serializable {
    
    
	/**
	 * Método para iniciar os dados do controlador. 
	 */
	void initData();
    
	/**
	 * Método para abrir a página inicial gerida por esse controlador.
	 */
    void openInitialView();
}