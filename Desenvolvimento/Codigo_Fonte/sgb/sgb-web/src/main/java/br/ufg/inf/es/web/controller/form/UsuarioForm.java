package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.web.datamodel.UsuarioDataModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe de formulário para armazenar os dados das páginas
 *
 * @author Cézar Augusto Ferreira, Cássio Augusto Silva de Freitas
 */
@Component
@Scope("session")
public class UsuarioForm extends GenericForm<Usuario> {

    /**
     * Atributo tabelaUsuarios
     *
     * @author Cássio Augusto Silva de Freitas
     */
    private Collection<Usuario> tabelaUsuarios;
    /**
     * atributo userDataModel
     */
    private UsuarioDataModel userDataModel;
    
    /**
     * atributo usuariosSlecionados
     */
    private Usuario[] usuariosSelecionados;

    /**
     * Método getTabelaUsuarios()
     *
     * @author Cássio Augusto Silva de Freitas
     * @return
     */
    public Collection<Usuario> getTabelaUsuarios() {
        return tabelaUsuarios;
    }

    /**
     * Método setgetTabelaUsuarios()
     *
     * @author Cássio Augusto Silva de Freitas
     * @param tabelaUsuarios
     */
    public void setTabelaUsuarios(Collection<Usuario> tabelaUsuarios) {
        this.tabelaUsuarios = tabelaUsuarios;
    }

    public UsuarioDataModel getUserDataModel() {
        
        List<Usuario> usuarios = new ArrayList<Usuario>(this.getTabelaUsuarios());

        userDataModel = new UsuarioDataModel(usuarios);

        return userDataModel;
    }

    public void setUserDataModel(UsuarioDataModel userDataModel) {
        this.userDataModel = userDataModel;
    }

    public Usuario[] getUsuariosSelecionados() {
        return usuariosSelecionados;
    }

    public void setUsuariosSelecionados(Usuario[] usuariosSelecionados) {
        this.usuariosSelecionados = usuariosSelecionados;
    }
    
}