package br.ufg.inf.es.base.validation;

import java.util.Collection;

/**
 * Base para a validacao dos objetos
 *
 * @author Cézar Augusto Ferreira, Victor
 */
public abstract class Validation<T> {

    /**
     * Método que realiza a validação do objeto
     *
     * @param object
     * @throws ValidationException
     */
    public abstract void validate(T object) throws ValidationException;

    /**
     * Método que verifica se uma String é invalida.
     *
     * @param value
     * @return boolean
     */
    public boolean isInvalid(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Método que verifica se uma coleção é inválida.
     *
     * @param value
     * @return boolean
     */
    public boolean isInvalid(Collection value) {
        return value == null || value.isEmpty();
    }

    /**
     * Método que verifica se um objeto é inválido.
     *
     * @param value
     * @return boolean
     */
    public boolean isInvalid(Object value) {
        return value == null;
    }
}