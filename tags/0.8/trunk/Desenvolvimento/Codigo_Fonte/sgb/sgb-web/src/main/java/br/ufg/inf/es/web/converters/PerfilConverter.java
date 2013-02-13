/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.converters;

import br.ufg.inf.es.model.UsuarioPerfil;
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

        ServletContext context = (ServletContext) facesContext.getExternalContext().getContext();

        UsuarioController usuarioController = WebApplicationContextUtils.getWebApplicationContext(context).getBean(UsuarioController.class);

        Collection<UsuarioPerfil> perfis = usuarioController.getPerfil();

        for (UsuarioPerfil perfil : perfis) {
            if (perfil.name().equals(value)) {
                return perfil;
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((UsuarioPerfil) value).name());
    }
}
