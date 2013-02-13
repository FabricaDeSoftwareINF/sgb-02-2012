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
    
    private static final String SEPARATOR = "%&";
    
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value.trim().equals("")) {  
            return null;  
        } else {  
            ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            AutorDAO dao = context.getBean(AutorDAO.class);
            
            String[] atributos = value.split(SEPARATOR);
            Autor autor = new Autor();
            autor.setId(Long.parseLong(atributos[0]));
            autor.setNome(atributos[1]);
            autor.setSobrenome(atributos[2]);
            Collection<Autor> lista = dao.search(autor);
            return lista.toArray()[0];  
        }  
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            Autor autor = (Autor) value;
            StringBuilder sb = new StringBuilder();
            sb.append(autor.getId()).append(SEPARATOR);
            sb.append(autor.getNome()).append(SEPARATOR);
            sb.append(autor.getSobrenome()).append(SEPARATOR);
            return String.valueOf(sb.toString());  
        }  
    }
    
}
