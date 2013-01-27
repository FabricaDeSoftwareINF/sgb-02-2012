package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RN006Parametros;
import br.ufg.inf.es.model.Parametros;
import br.ufg.inf.es.persistencia.ParametrosDAO;
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
public class ParametrosService extends GenericService<Parametros> {

    @Autowired
    private ParametrosDAO dao;

    @Override
    public ParametrosDAO getDAO() {
        return this.dao;
    }

    public BigDecimal obtenhaValorFrete() throws ValidationException {
        Parametros parametros = this.find();
        return parametros.getValorFrete();
    }
    
    public Integer obtenhaParametroMEC() throws ValidationException {
        Parametros parametros = this.find();
        return parametros.getParametroMEC();
    }

    public Parametros find() throws ValidationException {
        Parametros parametros;
        Collection<Parametros> listaDeParametros = this.list();
        if (!listaDeParametros.isEmpty()) {
            parametros = (Parametros) listaDeParametros.toArray()[0];
        } else {
            parametros = obtenhaParametroValido();
            this.save(parametros);
        }

        return parametros;
    }

    private Parametros obtenhaParametroValido() {
        return new Parametros();
    }

    @Override
    @RN006Parametros
    public void save(Parametros entity) throws ValidationException {
        super.save(entity);

    }
}
