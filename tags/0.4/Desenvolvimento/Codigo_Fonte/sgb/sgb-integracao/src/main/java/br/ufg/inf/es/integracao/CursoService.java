package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG004;
import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.persistencia.CursoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author Diogo Gon&ccedil;alves Teodoro
 * 
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CursoService extends GenericService<Curso> {

	@Autowired
	private CursoDAO dao;

	@Override
	public CursoDAO getDAO() {

		return this.dao;
	}

	public void setDao(CursoDAO dao) {

		this.dao = dao;
	}

	@Override
	@RNG004
	public Long insert(Curso entity) throws ValidationException {

		return super.insert(entity);
	}

}
