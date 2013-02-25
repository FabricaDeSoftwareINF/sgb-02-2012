/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.converters;

import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.persistencia.AutorDAO;
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

    private WebApplicationContext webApplicationContext;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        Autor autor = new Autor();
        if (value != null && !value.isEmpty() && !value.equals("null")) {  
            
            ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
            
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            
            AutorDAO dao = context.getBean(AutorDAO.class);
            
            autor = dao.find(Long.parseLong(value));
        }  
        return autor;
    }

    public WebApplicationContext getWebApplicationContext() {

        return webApplicationContext;
    }

    public void setWebApplicationContext(WebApplicationContext webContext) {
        this.webApplicationContext = webContext;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof Autor) {
            Autor autor = (Autor) value;
            return String.valueOf(autor.getId());
        }
        return "";
    }
}
