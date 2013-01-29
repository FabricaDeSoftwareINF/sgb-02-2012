/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.converters;

import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.persistencia.CursoDAO;
import java.util.Collection;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Classe responsável por realizar a conversão da entidade Curso.
 * @author Henrique
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
       
        if (value.trim().equals("")) {  
            
            return null;  
            
        } else {  
            
            ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
            
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            
            CursoDAO dao = context.getBean(CursoDAO.class);
            
            Curso c = new Curso();
            
            c.setNome(value);
            
            Collection<Curso> lista = dao.search(c);
            
            if(lista.isEmpty()) {
             
                return null;
            }
            
            return lista.toArray()[0];
        }  
    }

    /**
     * Método responsável por converter um <code>Object</code> em uma <code>String</code>.
     * @param context
     * @param component
     * @param value
     * @return A <code>String</code> convertida
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if (value == null || value.equals("")) {  
            
            return "";  
        
        } else {  
         
            return String.valueOf(((Curso) value).getNome());  
        }  
    }
    
}
