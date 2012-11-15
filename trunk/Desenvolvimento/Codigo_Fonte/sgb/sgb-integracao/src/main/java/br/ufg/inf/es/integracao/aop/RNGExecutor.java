package br.ufg.inf.es.integracao.aop;

import br.ufg.inf.es.base.validation.MultipleValidationException;
import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.base.validation.annotations.Validator;
import br.ufg.inf.es.base.validation.annotations.Validators;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author Cézar Augusto Ferreira
 */
public class RNGExecutor implements MethodBeforeAdvice {

    @Autowired
    private ApplicationContext applicationContext;

    public void before(Method method, Object[] params, Object o) throws Throwable {

        if (method.isAnnotationPresent(Validators.class)) {
            
            Validators validators = method.getAnnotation(Validators.class);
            
            Collection<String> keysMessage = new ArrayList<String>();
            
            for (Validator validator : validators.value()) {
                
                try {
                    
                    this.executeValidation(validator, params[0]);
                    
                } catch (ValidationException ex) {
                    
                    keysMessage.add((String)ex.getKeyMessage());
                }
            }
            
            if (keysMessage.isEmpty()) {
                
                throw new MultipleValidationException(
                        keysMessage.toArray(new String[keysMessage.size()]));
            }
            
        } else if (method.isAnnotationPresent(Validator.class)) {

            Validator validator = method.getAnnotation(Validator.class);

            this.executeValidation(validator, params[0]);
        }
    }

    private void executeValidation(Validator validator, Object o) throws ValidationException {

        Validation validation = this.applicationContext.getBean(validator.validatorClass());
        
        validation.validate(o);
    }
}