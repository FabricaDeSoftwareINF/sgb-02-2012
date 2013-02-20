package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Livro;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author vinicius
 */
@Component
@Scope("session")
public class IntegracaoSdbForm extends GenericForm<Livro> {
    
}
