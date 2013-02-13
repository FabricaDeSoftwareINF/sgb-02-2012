/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.converters;

import br.ufg.inf.es.base.util.UtilObjeto;
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
        
        if (Long.parseLong(value) == 0 || value == null) {  
            
            return null;  
            
        } else {  
            
            ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
            
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            
            LivroDAO dao = context.getBean(LivroDAO.class);
            
            Livro c = dao.find(Long.parseLong(value));
            
            if(!UtilObjeto.isReferencia(c)) {
             
                return null;
            }
            
            return c;
        }  
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
     
        if (value == null || value.equals("")) {
        
            return "";
        
        } else {
        
            return String.valueOf(((Livro) value).getId());
        
        }
    }
}
