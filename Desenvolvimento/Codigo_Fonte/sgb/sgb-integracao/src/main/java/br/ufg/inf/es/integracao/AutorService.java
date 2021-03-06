package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.integracao.annotations.RNG012;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.AutorDTO;
import br.ufg.inf.es.persistencia.AutorDAO;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Service da entidade Autor
 * 
 * @author Henrique Hirako, Cássio Augusto Silva de Freitas
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AutorService extends GenericService<Autor> {

    /** Campo dao*/
    @Autowired
    private AutorDAO dao;

    /**
     * obtem o dao do autor
     * @return 
     */
    @Override
    public AutorDAO getDAO() {
        return this.dao;
    }

    /**
     * Define o dao do autor
     * @param dao 
     */
    public void setDao(AutorDAO dao) {
        this.dao = dao;
    }

    /**
     * Método responsável por buscar todos os autores do Banco de dados
     * @author Cássio Augusto
     * @return Coleção de Autores
     */
    public Collection<AutorDTO> buscaTodosAutores(String filtroNome) {
        return this.getDAO().listarAutores(filtroNome);
    }

    /**
     * Método responsável por realizar a inserção de um novo Autor
     * @author Cássio Augusto Silva de Freitas
     * @param autor a ser inserido
     * @return id da nova entidade
     * @throws ValidationException
     */
    @Override
    @RNG012
    public Long insert(Autor autor) throws ValidationException {
        return this.getDAO().insert(autor);
    }

    /**
     * Método reponsável por realizar a edição de um determinado autor
     *
     * @author Cássio Augusto Silva de Freitas
     * @param entidade
     * @throws ValidationException
     */
    @RNG012
    public void editar(Autor entidade) throws ValidationException {
        this.getDAO().update(entidade);
    }


    /**
     * Obtem os autores que possuem os caracteres da query no nome
     * @param query 
     * @return lista de autores com nome equivalente à query
     */
    public Collection<Autor> complete(String query) {
        Collection<Autor> results = new ArrayList<Autor>();

        for(Autor autor : dao.list()){
            if(autor.getNome().toUpperCase().contains(query.toUpperCase())){
                results.add(autor);
            }
        }

        return results;
    }
}