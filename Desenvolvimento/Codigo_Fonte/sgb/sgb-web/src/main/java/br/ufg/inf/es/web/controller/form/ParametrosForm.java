package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Parametros;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Victor Ribeiro de Carvalho
 */
@Component
@Scope("session")
public class ParametrosForm extends GenericForm<Parametros> {
}
