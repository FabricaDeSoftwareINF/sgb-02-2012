package br.ufg.inf.es.integracao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufg.inf.es.base.service.Auth;
import br.ufg.inf.es.model.Usuario;

/**
 * Classe para Autenticação.
 *
 * @author Diogo Ribeiro, Victor Carvalho
 */
public class SgbAuth implements Auth {

    /**
     * Campo usuarioService
     */
    @Autowired
    private UsuarioService usuarioService;

    /**
     * Faz o login do usuario no sistema, validando o nome e a senha
     *
     */
    public Collection<String> login(String user, String password) {

        Collection<String> roles = new ArrayList<String>();
        Usuario usuario = usuarioService.authUser(user, password);

        if (usuario != null) {
            roles = usuario.getPerfil() != null ? usuario.getPerfil().getRoles()
                    : Arrays.asList("");
        }

        return roles;
    }

    /**
     * Método que obtém o Service do usuário.
     *
     * @return UsuarioService
     */
    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    /**
     * Método que define o Service do usuário.
     *
     * @param us
     */
    public void setUsuarioService(UsuarioService us) {
        this.usuarioService = us;
    }
}
