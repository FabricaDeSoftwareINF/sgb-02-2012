package br.ufg.inf.es.web.converters;

import br.ufg.inf.es.base.persistence.biblioteca.DBDriver;
import br.ufg.inf.es.enuns.EnumTipoBibliografia;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author vinicius
 */
@FacesConverter("enumTipoBibliografiaConverter")
public class EnumTipoBibliografiaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if ((value != null) || (!value.equals(""))) {
            return EnumTipoBibliografia.valueOf(value);
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null){
            return ((EnumTipoBibliografia) value).name();
        }
        return null;
    }
}