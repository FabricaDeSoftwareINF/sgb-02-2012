/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model.exportacaodados.planilha;

import java.util.ArrayList;

/**
 * Entidade Planilha.
 * @author Bruno Marquete
 */
public class Planilha {
    
    private String tituloCabecalho;
    
    private ArrayList<ItemPlanilha> linhasPlanilha = new ArrayList<ItemPlanilha>();

    public ArrayList<ItemPlanilha> getLinhasPlanilha() {
        return linhasPlanilha;
    }

    public void setLinhasPlanilha(ArrayList<ItemPlanilha> linhasPlanilha) {
        this.linhasPlanilha = linhasPlanilha;
    }

    public String getTituloCabecalho() {
        return tituloCabecalho;
    }

    public void setTituloCabecalho(String tituloCabecalho) {
        this.tituloCabecalho = tituloCabecalho;
    }
    
}
