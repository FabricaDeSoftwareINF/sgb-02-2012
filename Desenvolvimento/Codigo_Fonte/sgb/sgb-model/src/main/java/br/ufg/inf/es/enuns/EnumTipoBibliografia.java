/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.enuns;

/**
 *
 * @author Cássio Augusto
 */
public enum EnumTipoBibliografia {
    
    BASICA("Básica"),COMPLEMENTAR("Complementar"), SUGERIDA("Sugerida");
    
    private String value;

    private EnumTipoBibliografia(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
}
