package br.ufg.inf.es.web.controller;

import br.ufg.inf.es.base.service.Auth;
import java.util.ArrayList;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Component;

/**
 * @author Cézar Augusto Ferreira
 */
@Component
@Scope("session")
public class LoginController extends JSFController implements AuthenticationProvider {

    private Auth auth;

    public Auth getAuth() {

        return auth;
    }

    public void setAuth(Auth auth) {
        
        this.auth = auth;
    }

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        
        String username = (String) a.getPrincipal();
        
        String password = (String) a.getCredentials();
        
        Collection<String> roles = this.getAuth().login(username, password);
        
        if (roles == null || roles.size() == 0)  {
//            this.addErrorMessage("Login inválido");
//            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login inválido", ""));
            throw new BadCredentialsException("msg.login.invalido");
        }
        
        return new UsernamePasswordAuthenticationToken(a.getPrincipal(), a.getCredentials(), this.createAuthorities(roles));
    }
    
    private Collection<GrantedAuthority> createAuthorities(Collection<String> roles) {
        
        Collection<GrantedAuthority> ga = new ArrayList<GrantedAuthority>();
        
        for (String role : roles) {
            
            ga.add(new GrantedAuthorityImpl(role));
        }
        
        return ga;
    }

    @Override
    public boolean supports(Class<?> type) {
        
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }
}
