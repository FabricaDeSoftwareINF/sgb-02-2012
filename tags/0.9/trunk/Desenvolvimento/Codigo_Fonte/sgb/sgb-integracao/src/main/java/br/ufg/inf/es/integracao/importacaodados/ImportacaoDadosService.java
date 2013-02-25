package br.ufg.inf.es.integracao.importacaodados;

import java.util.Collection;

import br.ufg.inf.es.integracao.GenericService;
import br.ufg.inf.es.model.AbstractEntityModel;
import br.ufg.inf.es.model.Livro;

/**
 * Interface de comunicação com Serviços de Importação
 * @author Vinícius Gonçalves
 */
public abstract class ImportacaoDadosService<E extends AbstractEntityModel> 
        extends GenericService<E> {
    
    public abstract void setImportacaoStrategy(ImportacaoStrategy<E> strategy);
    
    /**
     * Busca os livros em formato json na url especificada e faz
     * o parser para a entidade #{@link Livro}
     */
    public abstract Collection<E> importar(String urlServico);
 
}
