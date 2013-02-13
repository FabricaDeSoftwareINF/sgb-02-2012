package br.ufg.inf.es.base.controller;

/**
 * Interface que define as ações de um Controlador para ações de um CRUD. 
 * 
 * @author Cézar Augusto Ferreira
 */
public interface MaintenanceController extends Controller {
 
	
    /**
     * Método que abre a tela de inserção.
     *
     */
    void openInsertView();
    
    /**
     * Método que insere uma entidade.
     *
     */
    void insert();
    
    /**
     * Método que abre a tela de pesquisa.
     *
     */
    void openSearchView();
    
    /**
     * Método que realiza a método de busca.
     *
     */
    void search();
    
    /**
     * Método que abre a tela de edição.
     *
     */
    void openEditView();
    
    /**
     * Método que realiza a edição de uma entidade.
     *
     */
    void edit();
    
    /**
     * Método que remove uma entidade.
     *
     */
    void remove();
}