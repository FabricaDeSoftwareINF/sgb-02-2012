package br.ufg.inf.es.base.validation;

/**
 * @author Cézar Augusto Ferreira
 */
public interface Validation<T> {
    
    void validate(T object) throws ValidationException;
}