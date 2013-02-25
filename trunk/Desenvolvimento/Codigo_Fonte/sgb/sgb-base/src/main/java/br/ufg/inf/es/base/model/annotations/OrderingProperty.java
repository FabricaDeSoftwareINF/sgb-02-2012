package br.ufg.inf.es.base.model.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation para ordenação.
 * @author Cézar Augusto Ferreira
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OrderingProperty {
    
    /**
     * Método que define o tipo de ordenação.
     *
     * @return {@link SortOrder}
     */
    SortOrder sortOrder() default SortOrder.ASC;
}
