package br.ufg.inf.es.base.validation;

/**
 * @author Cézar Augusto Ferreira
 */
public class ValidationException extends Exception {

    private String keyMessage;

    public ValidationException(String keyMessage) {

        this.keyMessage = keyMessage;
    }

    public String getKeyMessage() {

        return this.keyMessage;
    }
}