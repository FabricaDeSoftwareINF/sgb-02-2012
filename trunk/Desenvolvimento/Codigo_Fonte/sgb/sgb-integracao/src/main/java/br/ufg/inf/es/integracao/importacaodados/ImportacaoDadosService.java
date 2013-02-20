package br.ufg.inf.es.integracao.importacaodados;

import br.ufg.inf.es.model.Livro;
import java.util.Collection;

/**
 * Interface de comunicação com Serviços de Importação
 * @author Vinícius Gonçalves
 */
public interface ImportacaoDadosService<E> {
    
    public void setImportacaoStrategy(ImportacaoStrategy<E> strategy);
    
    /**
     * Busca os livros em formato json na url especificada e faz
     * o parser para a entidade #{@link Livro}
     */
    public Collection<E> importar(String urlServico);
 
}
