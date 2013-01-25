package br.ufg.inf.es.base.model.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Cézar Augusto Ferreira
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OrderingProperty {
    
    SortOrder sortOrder() default SortOrder.ASC;
}
