package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Comunicacao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author igor
 */
@Component
@Scope("session")
public class ComunicacaoForm extends GenericForm<Comunicacao> {
    
}
