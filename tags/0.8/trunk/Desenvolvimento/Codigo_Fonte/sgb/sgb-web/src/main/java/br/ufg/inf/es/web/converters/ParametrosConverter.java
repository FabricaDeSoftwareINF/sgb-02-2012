package br.ufg.inf.es.web.converters;

import br.ufg.inf.es.model.Parametros;
import br.ufg.inf.es.persistencia.ParametrosDAO;
import java.math.BigDecimal;
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
 * @author Victor Carvalho
 */
@FacesConverter("parametrosBiblioteca")
public class ParametrosConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.trim().length() == 0) {
            return null;
        }

        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        ParametrosDAO dao = context.getBean(ParametrosDAO.class);

        Parametros a = new Parametros();
        a.setValorFrete(new BigDecimal(value));
        Collection<Parametros> lista = dao.search(a);
        return lista.toArray()[0];

    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return "";
        }
        if (object instanceof Parametros) {
            Parametros o = (Parametros) object;
            return o.toString();
        } else {
            throw new IllegalArgumentException("O objeto´é de uma classe diferente do esperado");
        }
    }
}
