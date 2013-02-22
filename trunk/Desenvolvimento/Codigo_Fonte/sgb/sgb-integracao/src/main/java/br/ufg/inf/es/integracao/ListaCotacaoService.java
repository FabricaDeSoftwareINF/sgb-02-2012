
package br.ufg.inf.es.integracao;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.persistencia.ListaCotacaoDAO;

/**
 * Classe Service para a entidade ListaCotacao
 * @author Bruno Marquete
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ListaCotacaoService extends GenericService<ListaCotacao> {

    /** Campo dao*/
    @Autowired
    private ListaCotacaoDAO dao;
    
    /** 
     * {@inheritDoc} 
     */
    @Override
    public ListaCotacaoDAO getDAO() {
        return dao;
    }

    /**
     * Método que define o DAO da ListaCotacao
     *
     * @author User
     *
     * @param dao
     */
    public void setDao(ListaCotacaoDAO dao) {
    	
        this.dao = dao;
    }
    
    public void editar(ListaCotacao listaCotacao) throws ValidationException {

        this.getDAO().update(listaCotacao);

    }
    
     public Collection<ListaCotacao> listByUser(Usuario user) {

         Collection<ListaCotacao> listasCotacaoUserLogado =
                 new ArrayList<ListaCotacao>();
         ArrayList<ListaCotacao> todasListasCotacao =
                 new ArrayList<ListaCotacao>(this.getDAO().list());
         
         for(ListaCotacao listaCotacao : todasListasCotacao) {
             
             if (listaCotacao.getUser().getEmail().equals(user.getEmail())) {
                 listasCotacaoUserLogado.add(listaCotacao);
             }
             
         }
         
        return (Collection) listasCotacaoUserLogado;
    }
    
}
