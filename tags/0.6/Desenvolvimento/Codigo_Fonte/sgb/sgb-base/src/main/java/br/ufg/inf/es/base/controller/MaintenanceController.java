package br.ufg.inf.es.base.controller;

/**
 * @author Cézar Augusto Ferreira
 */

public interface MaintenanceController extends Controller {
 
    void openInsertView();
    
    void insert();
    
    void openSearchView();
    
    void search();
    
    void openEditView();
    
    void edit();
    
    void remove();
}