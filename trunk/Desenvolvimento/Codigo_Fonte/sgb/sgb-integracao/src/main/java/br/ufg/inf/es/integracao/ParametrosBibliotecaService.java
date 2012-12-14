package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RN006_ParametroFrete;
import br.ufg.inf.es.model.ParametrosBiblioteca;
import br.ufg.inf.es.persistencia.ParametrosBibliotecaDAO;
import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author Victor Carvalho
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ParametrosBibliotecaService extends GenericService<ParametrosBiblioteca> {

    @Autowired
    private ParametrosBibliotecaDAO dao;

    @Override
    public ParametrosBibliotecaDAO getDAO() {
        return this.dao;
    }

    public BigDecimal obtenhaValorFrete() throws ValidationException {
        ParametrosBiblioteca parametros = this.find();
        return parametros.getValorFrete();
    }

    public ParametrosBiblioteca find() throws ValidationException {
        ParametrosBiblioteca parametros;
        Collection<ParametrosBiblioteca> listaDeParametros = this.list();
        if (!listaDeParametros.isEmpty()) {
            parametros = (ParametrosBiblioteca) listaDeParametros.toArray()[0];
        } else {
            parametros = obtenhaParametroValido();
            this.save(parametros);
        }

        return parametros;
    }

    private ParametrosBiblioteca obtenhaParametroValido() {
        return new ParametrosBiblioteca();
    }

    @Override
    @RN006_ParametroFrete
    public void update(ParametrosBiblioteca entity) throws ValidationException {
        super.update(entity);

    }
}
