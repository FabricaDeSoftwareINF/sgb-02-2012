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
        EnumTipoBibliografia tipoBibliografia = null;
        if (value != null && !value.isEmpty()) {
            try {
                tipoBibliografia =  EnumTipoBibliografia.valueOf(value);
            } catch (IllegalArgumentException iae) {
                iae.printStackTrace();
            }
        }

        return tipoBibliografia;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof EnumTipoBibliografia){
            return ((EnumTipoBibliografia) value).name();
        }
        return null;
    }
}