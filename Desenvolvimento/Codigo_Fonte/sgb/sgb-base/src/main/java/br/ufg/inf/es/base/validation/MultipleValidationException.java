package br.ufg.inf.es.base.validation;

/**
 *  Classe que gerencia a ocorrência de múltiplas exceções.
 * 
 * @author Cézar Augusto Ferreira
 */
public class MultipleValidationException extends Exception {
    
    /**
     * Atributo keysMessage
     */
    private String[] keysMessage;

    /**
     * Setter da classe MultipleValidationException
     * 
     * @param keysMessage 
     */
    public MultipleValidationException(String[] keysMessage) {
        
        this.keysMessage = keysMessage.clone();
    }
    
    /**
     * Getter da classe MultipleValidationException
     */
    public String[] keysMessage() {
        
        return this.keysMessage.clone();
    }
}
