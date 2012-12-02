package br.ufg.inf.es.base.validation;

/**
 * @author Cézar Augusto Ferreira
 */
public class MultipleValidationException extends Exception {
    
    private String[] keysMessage;

    public MultipleValidationException(String[] keysMessage) {
        
        this.keysMessage = keysMessage;
    }
    
    public String[] keysMessage() {
        
        return this.keysMessage;
    }
}
