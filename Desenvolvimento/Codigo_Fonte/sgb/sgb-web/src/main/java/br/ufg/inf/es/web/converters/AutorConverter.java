/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.converters;

import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.persistencia.AutorDAO;
import java.util.Collection;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Henrique
 */
@FacesConverter("autorConverter")
public class AutorConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value.trim().equals("")) {  
            return null;  
        } else {  
            ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            AutorDAO dao = context.getBean(AutorDAO.class);
            
            Autor a = new Autor();
            a.setNome(value);
            Collection<Autor> lista = dao.search(a);
            return lista.toArray()[0];  
        }  
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Autor) value).getNome());  
        }  
    }
    
}
