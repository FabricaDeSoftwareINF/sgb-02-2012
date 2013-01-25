package br.ufg.inf.es.integracao;

import br.ufg.inf.es.base.service.Auth;
import br.ufg.inf.es.model.Perfil;
import br.ufg.inf.es.model.Usuario;
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
            Collection<Perfil> perfis = usuarioService.carreguePerfis(usuario);
            for (Perfil perfil : perfis) {
                roles.add(perfil.getTipo());
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
