package br.ufg.inf.es.base.validation;

/**
 * Exceção utilizada para a validação de objetos.
 * 
 * @author Cézar Augusto Ferreira
 */
public class ValidationException extends Exception {

    /** Campo keyMessage*/
    private String keyMessage;

    /**
     * Construtor desta classe.
     * @param keyMessage
     */
    public ValidationException(String keyMessage) {

        this.keyMessage = keyMessage;
    }

    /**
     * Método que obtém a mensagem da exceção.
     *
     * @return A mensagem
     */
    public String getKeyMessage() {

        return this.keyMessage;
    }
}