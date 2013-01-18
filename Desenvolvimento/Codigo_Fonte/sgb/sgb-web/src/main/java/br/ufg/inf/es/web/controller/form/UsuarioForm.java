package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Usuario;
import java.util.Collection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe de formulário para armazenar os dados das páginas
 * 
 * @author Cézar Augusto Ferreira
 */
@Component
@Scope("session")
public class UsuarioForm extends GenericForm<Usuario> {
 
    /**
     * Atributo tabelaUsuarios
     * @author Cássio Augusto Silva de Freitas
     */
    private Collection<Usuario> tabelaUsuarios;

    /**
     * Método getTabelaUsuarios()
     * @author Cássio Augusto Silva de Freitas
     * @return 
     */
    public Collection<Usuario> getTabelaUsuarios() {
        return tabelaUsuarios;
    }

    /**
     * Método setgetTabelaUsuarios()
     * @author Cássio Augusto Silva de Freitas
     * @param tabelaUsuarios 
     */
    public void setTabelaUsuarios(Collection<Usuario> tabelaUsuarios) {
        this.tabelaUsuarios = tabelaUsuarios;
    }
    
}