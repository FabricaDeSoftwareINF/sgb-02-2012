/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model.exportacaodados.planilha;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidade Planilha.
 * @author Bruno Marquete
 */
public class Planilha {
    
    private String tituloCabecalho;
    
    private List<ItemPlanilha> linhasPlanilha = new ArrayList<ItemPlanilha>();

    public List<ItemPlanilha> getLinhasPlanilha() {
        return linhasPlanilha;
    }

    public void setLinhasPlanilha(List<ItemPlanilha> linhasPlanilha) {
        this.linhasPlanilha = linhasPlanilha;
    }

    public String getTituloCabecalho() {
        return tituloCabecalho;
    }

    public void setTituloCabecalho(String tituloCabecalho) {
        this.tituloCabecalho = tituloCabecalho;
    }
    
}
