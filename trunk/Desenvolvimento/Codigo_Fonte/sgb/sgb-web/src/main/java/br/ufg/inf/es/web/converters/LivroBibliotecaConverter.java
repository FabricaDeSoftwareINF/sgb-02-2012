/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.converters;

import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;
import br.ufg.inf.es.persistencia.biblioteca.LivrosBibliotecaDAO;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.NotFoundException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.ServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author igor
 */
@FacesConverter("livroBibliotecaConverter")
public class LivroBibliotecaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {

        if (value.trim().equals("")) {

            return null;

        } else {

            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();

            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);

            LivrosBibliotecaDAO dao = context.getBean(LivrosBibliotecaDAO.class);
            
            Long codigoLivro = 0l;
            try{
                codigoLivro = new Long(value);
            }catch (NumberFormatException ex){
                System.err.println("Código informado não é um número inteiro longo.");
            }    

            LivroBiblioteca livroBiblioteca = new LivroBiblioteca();
            try {
                livroBiblioteca = dao.getLivroBibliotecaCodigo(codigoLivro);
            } catch (NotFoundException ex) {
                Logger.getLogger(LivroBibliotecaConverter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(LivroBibliotecaConverter.class.getName()).log(Level.SEVERE, null, ex);
            }

            return livroBiblioteca;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value == null || value.equals("") || value.equals("null") || 
                (((LivroBiblioteca) value).getId() == null)) {
            return "";

        } else {

            return ((LivroBiblioteca) value).getId().toString();
        }
    }
}
