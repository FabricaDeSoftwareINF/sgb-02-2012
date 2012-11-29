package br.ufg.inf.es.web.controller.form;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.ufg.inf.es.model.Curso;

/**
 * @author Diogo Gon&ccedil;alves Teodoro
 * 
 */
@Component
@Scope("session")
public class CursoForm extends GenericForm<Curso> {

}
