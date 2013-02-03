package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.service.Auth;
import br.ufg.inf.es.model.Usuario;
import br.ufg.inf.es.model.UsuarioPerfil;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Diogo Ribeiro
 */
public class SgbAuth implements Auth {

    @Autowired
    private UsuarioService usuarioService;

    public Collection<String> login(String user, String password) {
        Collection<String> roles = new ArrayList<String>();
        Usuario usuario = usuarioService.authUser(user, password);

        if (usuario != null) {
            if (usuario.getPerfil().equals(UsuarioPerfil.ADM)) {
                roles.add(UsuarioPerfil.ADM.name());
            }
            if (usuario.getPerfil().equals(UsuarioPerfil.CONSELHEIRO)) {
                roles.add(UsuarioPerfil.CONSELHEIRO.name());
            }
            if (usuario.getPerfil().equals(UsuarioPerfil.CONSELHEIRO_ADM)) {
                roles.add(UsuarioPerfil.ADM.name());
                roles.add(UsuarioPerfil.CONSELHEIRO.name());
            }
            if (usuario.getPerfil().equals(UsuarioPerfil.DOCENTE)) {
                roles.add(UsuarioPerfil.DOCENTE.name());
            }
            if (usuario.getPerfil().equals(UsuarioPerfil.DOCENTE_ADM)) {
                roles.add(UsuarioPerfil.ADM.name());
                roles.add(UsuarioPerfil.DOCENTE.name());
            }
            if (usuario.getPerfil().equals(UsuarioPerfil.DOCENTE_CONSELHEIRO)) {
                roles.add(UsuarioPerfil.DOCENTE.name());
                roles.add(UsuarioPerfil.CONSELHEIRO.name());
            }
            if (usuario.getPerfil().equals(UsuarioPerfil.DOCENTE_CONSELHEIRO_ADM)) {
                roles.add(UsuarioPerfil.ADM.name());
                roles.add(UsuarioPerfil.DOCENTE.name());
                roles.add(UsuarioPerfil.CONSELHEIRO.name());
            }
            if (usuario.getPerfil().equals(UsuarioPerfil.TECNICO)) {
                roles.add(UsuarioPerfil.TECNICO.name());
            }
            if (usuario.getPerfil().equals(UsuarioPerfil.TECNICO_ADM)) {
                roles.add(UsuarioPerfil.ADM.name());
                roles.add(UsuarioPerfil.TECNICO.name());
            }
        }

        return roles;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void SetUsuariioService(UsuarioService us) {
        this.usuarioService = us;
    }
}
