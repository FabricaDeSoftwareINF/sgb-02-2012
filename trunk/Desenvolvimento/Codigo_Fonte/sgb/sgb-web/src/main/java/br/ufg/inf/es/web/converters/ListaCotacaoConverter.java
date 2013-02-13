/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.converters;

import br.ufg.inf.es.model.ListaCotacao;
import br.ufg.inf.es.persistencia.ListaCotacaoDAO;
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
 * @author Bruno Marquete
 */
@FacesConverter("listaCotacaoConverter")
public class ListaCotacaoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value.trim().equals("")) {
            return null;
        } else {
            ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            ListaCotacaoDAO dao = context.getBean(ListaCotacaoDAO.class);

            ListaCotacao a = new ListaCotacao();
            a.setNome(value);
            Collection<ListaCotacao> lista = dao.search(a);
            return lista.toArray()[0];
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((ListaCotacao) value).getNome());
        }
    }
}
