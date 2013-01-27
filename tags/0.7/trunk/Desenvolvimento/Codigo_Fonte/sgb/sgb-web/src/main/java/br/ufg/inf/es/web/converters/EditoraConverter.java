/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.converters;

import br.ufg.inf.es.model.Editora;
import br.ufg.inf.es.persistencia.EditoraDAO;
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
@FacesConverter("editoraConverter")
public class EditoraConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value.trim().equals("")) {  
            return null;  
        } else {  
            ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            EditoraDAO dao = context.getBean(EditoraDAO.class);
            Editora e = new Editora();
            e.setNome(value);
            Collection<Editora> lista = dao.search(e);
            return lista.toArray()[0];
        }  
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return String.valueOf(((Editora) value).getNome());  
        }  
    }
    
}
