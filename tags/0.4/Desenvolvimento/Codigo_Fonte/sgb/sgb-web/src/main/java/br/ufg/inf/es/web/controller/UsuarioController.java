package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.integracao.PerfilService;
import br.ufg.inf.es.integracao.UsuarioService;
import br.ufg.inf.es.model.Perfil;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.web.controller.form.UsuarioForm;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Cézar Augusto Ferreira
 */
@Component
@Scope("session")
public class UsuarioController
        extends SGBController<Usuario, UsuarioForm, UsuarioService> {

    @Autowired
    private UsuarioForm form;
    @Autowired
    private UsuarioService service;
    @Autowired
    private PerfilService perfilService;
    private Collection<Usuario> usuarioSelecionado;

    @Override
    public UsuarioForm getForm() {

        return this.form;
    }

    @Override
    public UsuarioService getService() {

        return this.service;
    }

    public void setForm(UsuarioForm form) {

        this.form = form;
    }

    public void setService(UsuarioService service) {

        this.service = service;
    }

    public PerfilService getPerfilService() {

        return perfilService;
    }

    public void setPerfilService(PerfilService perfilService) {

        this.perfilService = perfilService;
    }

    public List<Perfil> getPerfil() {
        return new ArrayList<Perfil>(this.perfilService.getDAO().list());
    }

    public Collection<Usuario> getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Collection<Usuario> usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }


    public void selecionaUsuario(Usuario usuario) {
        this.usuarioSelecionado.add(usuario);
    }

    public void removerUsuarioSelecionados() {
            this.service.getDAO().removeAll(usuarioSelecionado);
    }
}
