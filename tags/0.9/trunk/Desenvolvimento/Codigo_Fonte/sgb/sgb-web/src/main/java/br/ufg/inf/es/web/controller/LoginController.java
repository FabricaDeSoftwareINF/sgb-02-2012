package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.service.Auth;
import br.ufg.inf.es.base.util.SgbCryptography;
import java.util.ArrayList;
import java.util.Collection;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Component;

/**
 * Controlador da tela de login
 *
 * @author Cézar Augusto Ferreira
 */
@Component
@Scope("session")
public class LoginController extends JSFController implements AuthenticationProvider {

    private Auth auth;
    private SgbCryptography cryptography = new SgbCryptography();

    /**
     * Faz a autenticação de um usuário a partir dos dados de login fornacidos
     * na tela
     *
     * @param a dados para autenticacao
     * @return resultado da autenticacao
     */
    @Override
    public Authentication authenticate(Authentication a) {
        String username = (String) a.getPrincipal();
        String password = (String) a.getCredentials();
        password = cryptography.encrypt(password);

        Collection<String> roles = this.getAuth().login(username, password);

        if (roles == null || roles.isEmpty()) {
            throw new BadCredentialsException("msg.login.invalido");
        }

        return new UsernamePasswordAuthenticationToken(username, a.getCredentials(),
                this.createAuthorities(roles));
    }

    /**
     * Define os papeis do usuario
     *
     * @param roles papeis do usuario
     * @return lista com os papeis do usuario
     */
    private Collection<GrantedAuthority> createAuthorities(Collection<String> roles) {
        Collection<GrantedAuthority> ga = new ArrayList<GrantedAuthority>();

        for (String role : roles) {
            ga.add(new GrantedAuthorityImpl(role));
        }

        return ga;
    }

    /**
     * Verifica a classe corresponde a UsernamePasswordAuthenticationToken
     *
     * @param type classe a ser comparada
     * @return true caso seja uma UsernamePasswordAuthenticationToken, false
     * caso contrario
     * @see UsernamePasswordAuthenticationToken
     */
    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }

    /**
     * Obtem o nome do usuario logado
     *
     * @return nome do usuario logado
     */
    public String getUsuarioLogado() {
        HttpServletRequest servletRequest = ((HttpServletRequest) FacesContext.getCurrentInstance().
                getExternalContext().getRequest());

        return servletRequest.getUserPrincipal().getName();
    }

    /**
     * obtem o objeto auth
     *
     * @return
     */
    public Auth getAuth() {
        return auth;
    }

    /**
     * Define um novo valor para o objeto Auth
     *
     * @param auth Novo auth
     */
    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}