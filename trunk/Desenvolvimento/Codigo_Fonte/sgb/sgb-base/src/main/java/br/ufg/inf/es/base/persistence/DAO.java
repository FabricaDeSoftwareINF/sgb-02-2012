package br.ufg.inf.es.base.persistence;

import br.ufg.inf.es.base.model.Entity;
import java.io.Serializable;
import java.util.Collection;

/**
 * Inteface que define os métodos de um Data Acess Object (DAO).
 * @author Cézar Augusto Ferreira
 */
public interface DAO<E extends Entity<ID>, ID extends Serializable> extends Serializable {

    /**
     * Método que obtém uma entidade de acordo com o id dela.
     *
     * @param id
     * @return Entidade
     */
    E find(final ID id);

    /**
     * Método que insere uma entidade.
     *
     * @param entidade
     * @return Id da entidade salva.
     */
    ID insert(final E entidade);

    /**
     * Método que atualiza uma entidade persistida no banco.
     *
     * @param entidade
     */
    void update(final E entidade);

    /**
     * Método que salva uma entidade ou realiza a atualização caso já esteja persistida.
     *
     * @param entidade
     */
    void save(final E entidade);

    /**
     * Método que remove uma entidade persistida.
     *
     * @param entidade
     */
    void remove(final E entidade);

    /**
     * Método que remove todas as entidade que estão na coleção.
     *
     * @param entidades Entidade para remoção.
     */
    void removeAll(final Collection<E> entidades);

    /**
     * Método que utiliza uma entidade para realização de uma busca de acordo com os campos preenchidos.
     *
     * @param entidade
     * @return Collection Resultados da busca
     */
    Collection<E> search(final E entidade);
    
    /**
     * Método que uma busca com o parâmetro da chave e os atributos a serem pesquisados.
     *
     * @param key
     * @param attributes
     * @return Resultados da busca
     */
    Collection<E> search(final String key, final String... attributes);

    
    /**
     * Método que lista todas as entidades persistidas.
     *
     * @return Todas as entidade persistidas.
     */
    Collection<E> list();

    /**
     * Método que atualiza os atributos de um objeto persistido com os valores salvos.
     *
     * @param entidade
     */
    void refresh(final E entidade);
}