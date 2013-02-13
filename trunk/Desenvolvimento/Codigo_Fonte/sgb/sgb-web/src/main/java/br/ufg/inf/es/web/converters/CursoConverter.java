/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.converters;

import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.persistencia.CursoDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Classe responsável por realizar a conversão da entidade Curso.
 * @author Henrique, Cássio Augusto, Marco Aurélio
 */
@FacesConverter("cursoConverter")
public class CursoConverter implements Converter {
   
    /**
     * Método responsável por converter uma <code>String</code> em um objeto.
     * @param facesContext
     * @param component
     * @param value
     * @return Objeto gerado a partir da <code>String</code>
     */
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
       
        Curso curso = new Curso();
        if (value != null && !value.isEmpty()) { 
            
            ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
            
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            
            CursoDAO dao = context.getBean(CursoDAO.class);
            
            try {
                curso = dao.find(Long.parseLong(value));
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }  
        return curso;
    }

    /**
     * Método responsável por converter um Curso em uma String.
     * @param context
     * @param component
     * @param value
     * @return A representação de um curso em formato String
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof Curso) {
            return String.valueOf(((Curso) value).getId());  
        }  
        return "";
    }
    
}
