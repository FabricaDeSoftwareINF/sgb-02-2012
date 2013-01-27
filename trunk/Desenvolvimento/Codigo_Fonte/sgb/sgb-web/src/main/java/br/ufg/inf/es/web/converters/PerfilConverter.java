/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.converters;

import br.ufg.inf.es.model.Perfil;
import br.ufg.inf.es.web.controller.UsuarioController;
import java.util.Collection;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Luã
 */
@FacesConverter("perfilConverter")
public class PerfilConverter implements Converter {
    
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {

        Long id = Long.parseLong(value);
        
        ServletContext context = (ServletContext) facesContext.getExternalContext().getContext();
        
        UsuarioController usuarioController = WebApplicationContextUtils.getWebApplicationContext(context).getBean(UsuarioController.class);
        
        Collection<Perfil> perfis = usuarioController.getPerfil();
        
        for (Perfil perfil : perfis) {
            
            if (perfil.getId().equals(id)) {
                
                return perfil;
            }
        }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Perfil)value).getId());
    }
    
}
