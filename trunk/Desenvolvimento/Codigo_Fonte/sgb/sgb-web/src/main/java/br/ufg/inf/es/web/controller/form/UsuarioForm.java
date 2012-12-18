package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Usuario;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Cézar Augusto Ferreira
 */
@Component
@Scope("session")
public class UsuarioForm extends GenericForm<Usuario> {
    
}