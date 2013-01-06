package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.persistencia.AutorDAO;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author Henrique Hirako
 */
@Component
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class AutorService extends GenericService<Autor> {

    @Autowired
    private AutorDAO dao;

    @Override
    public AutorDAO getDAO() {

        return this.dao;
    }

    public void setDao(AutorDAO dao) {

        this.dao = dao;
    }
  
    public Collection<Autor> complete(String query) {  
        Collection<Autor> results = new ArrayList<Autor>();
          
        for(Autor autor : dao.list()){
            if(autor.getNome().contains(query)){
                results.add(autor);
            }
        }
          
        return results;  
    }  
    /**
     * Método responsável por buscar todos os autores do Banco de dados
     * 
     * @author Cássio Augusto
     * @return Coleção de Autores
     */
    public Collection<Autor> buscaTodosAutores() {
        
        return this.getDAO().list();
    
    }
    
}