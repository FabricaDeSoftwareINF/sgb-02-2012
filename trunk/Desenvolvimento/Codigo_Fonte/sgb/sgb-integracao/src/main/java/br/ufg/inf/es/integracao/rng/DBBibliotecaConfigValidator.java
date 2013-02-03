package br.ufg.inf.es.integracao.rng;

import br.ufg.inf.es.base.validation.Validation;
import br.ufg.inf.es.base.validation.ValidationException;
import br.ufg.inf.es.model.biblioteca.DBBibliotecaConfig;
import org.springframework.stereotype.Component;

/**
 *
 * @author igor
 */
@Component
public class DBBibliotecaConfigValidator implements Validation<DBBibliotecaConfig>{
    
    /**
     * Método Responsável por validar os campos do a Entidade Autor
     * @param object a ser validado
     * @throws ValidationException 
     */
    public void validate(DBBibliotecaConfig object) throws ValidationException {
       
        
        if (object.getDriver() == null){
            
            throw new ValidationException("parametros.biblioteca.combobox.RNG018.tipoBanco");
            
        } else if (object.getUrl() == null || object.getUrl().equals("")) {
            
            throw new ValidationException("parametros.biblioteca.label.RNG018.urlService");
            
        } else if (object.getPorta() == null || object.getPorta().equals("")) {
            
            throw new ValidationException("parametros.biblioteca.label.RNG018.porta");
            
        } else if (object.getNameDataBase() == null || object.getNameDataBase().equals("")) {
            
            throw new ValidationException("parametros.biblioteca.label.RNG018.nomeBancoDados");
            
        } else if (object.getUserDataBase() == null || object.getUserDataBase().equals("")) {
            
            throw new ValidationException("parametros.biblioteca.label.RNG018.nomeUsuario");
            
        } else if (object.getPasswordDataBase() == null) {
            
            throw new ValidationException("parametros.biblioteca.label.RNG018.senhaUsuario");
            
        } else if (object.getTabela() == null || object.getTabela().equals("")) {
            
            throw new ValidationException("parametros.biblioteca.label.RNG018.tabela");
            
        } else if (object.getCampoTituloLivro() == null || object.getCampoTituloLivro().equals("")) {
            
            throw new ValidationException("parametros.biblioteca.label.RNG018.campoTituloLivro");
            
        }   
    }
}
