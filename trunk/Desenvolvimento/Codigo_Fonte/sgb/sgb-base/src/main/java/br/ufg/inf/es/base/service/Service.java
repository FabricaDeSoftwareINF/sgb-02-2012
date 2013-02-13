package br.ufg.inf.es.base.service;

import br.ufg.inf.es.base.model.Entity;
import br.ufg.inf.es.base.validation.ValidationException;
import java.io.Serializable;
import java.util.Collection;

/**
 * Interface que define os métodos de uma class servidora que executa as regras de negócio da aplicação.
 * 
 * @author Cézar Augusto Ferreira
 */
public interface Service<E extends Entity<ID>, ID extends Serializable> extends Serializable {
 
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
     * @throws ValidationException
     */
    ID insert(final E entidade) throws ValidationException;

    /**
     * Método que atualiza uma entidade persistida no banco.
     *
     * @param entidade
     * @throws ValidationException
     */
    void update(final E entidade) throws ValidationException;

    /**
     * Método que salva uma entidade ou realiza a atualização caso já esteja persistida.
     *
     * @param entidade
     * @throws ValidationException
     */
    void save(final E entidade) throws ValidationException;

    /**
     * Método que remove uma entidade persistida.
     *
     * @param entidade
     * @throws ValidationException
     */
    void remove(final E entidade) throws ValidationException;

    /**
     * Método que remove todas as entidade que estão na coleção.
     *
     * @param entidades Entidade para remoção.
     * @throws ValidationException
     */
    void removeAll(final Collection<E> entidades) throws ValidationException;

    /**
     * Método que utiliza uma entidade para realização de uma busca de acordo com os campos preenchidos.
     *
     * @param entidade
     * @return Collection Resultados da busca
     */
    Collection<E> search(final E entidade);

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