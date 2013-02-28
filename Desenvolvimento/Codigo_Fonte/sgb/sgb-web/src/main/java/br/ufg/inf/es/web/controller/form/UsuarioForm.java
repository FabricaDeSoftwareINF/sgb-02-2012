package br.ufg.inf.es.web.controller.form;

import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.model.UsuarioPerfil;
import br.ufg.inf.es.web.datamodel.UsuarioDataModel;
import java.util.Collection;
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

    private Collection<UsuarioPerfil> perfis;
    /**
     * atributo userDataModel
     */
    private UsuarioDataModel userDataModel;
    /**
     * atributo usuariosSlecionados
     */
    private Collection<Usuario> usuariosSelecionados;

    public Collection<UsuarioPerfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(Collection<UsuarioPerfil> perfis) {
        this.perfis = perfis;
    }

    public UsuarioDataModel getUserDataModel() {
        return userDataModel;
    }

    public void setUserDataModel(UsuarioDataModel userDataModel) {
        this.userDataModel = userDataModel;
    }

    public Collection<Usuario> getUsuariosSelecionados() {
        return this.usuariosSelecionados;
    }

    public void setUsuariosSelecionados(Collection<Usuario> usuariosSelecionados) {
        this.usuariosSelecionados = usuariosSelecionados;
    }
}