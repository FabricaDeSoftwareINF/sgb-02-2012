/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.converters;

import br.ufg.inf.es.model.Livro;
import br.ufg.inf.es.persistencia.LivroDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Rodrigo Andrade, Cássio Augusto Silva de Freitas
 */
@FacesConverter("livroConverter")
public class LivroConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        
        Livro livro = new Livro();
        if (value != null && !value.isEmpty() && !value.equals("null")) {  
            
            ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
            
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            
            LivroDAO dao = context.getBean(LivroDAO.class);
            
            livro = dao.find(Long.parseLong(value));
        }  
        return livro;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Livro) {
            return String.valueOf(((Livro) value).getId());  
        }
        return "";
    }
}
