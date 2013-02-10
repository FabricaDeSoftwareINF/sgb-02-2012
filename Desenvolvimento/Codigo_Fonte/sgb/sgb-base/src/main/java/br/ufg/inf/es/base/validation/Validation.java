package br.ufg.inf.es.base.validation;

import java.util.Collection;

/**
 * Base para a validacao dos objetos
 *
 * @author Cézar Augusto Ferreira, Victor
 */
public abstract class Validation<T> {

    abstract public void validate(T object) throws ValidationException;

    public boolean isInvalid(String value) {
        return value == null || value.trim().isEmpty();
    }

    public boolean isInvalid(Collection value) {
        return value == null || value.isEmpty();
    }

    public boolean isInvalid(Object value) {
        return value == null;
    }
}