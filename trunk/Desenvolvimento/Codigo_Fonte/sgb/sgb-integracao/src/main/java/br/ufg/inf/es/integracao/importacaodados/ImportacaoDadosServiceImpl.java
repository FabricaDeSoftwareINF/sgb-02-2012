package br.ufg.inf.es.integracao.importacaodados;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.Collection;

/**
 *
 * @author Vinícius
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ImportacaoDadosServiceImpl<E> implements ImportacaoDadosService<E> {

    private ImportacaoStrategy<E> importacaoStrategy;
    
    public Collection<E> importar(String urlServico) {
        Collection<E> livros = importacaoStrategy.importarDados(urlServico);
        return livros;
    }

    public void setImportacaoStrategy(ImportacaoStrategy<E> strategy) {
        this.importacaoStrategy = strategy;
    }

}
