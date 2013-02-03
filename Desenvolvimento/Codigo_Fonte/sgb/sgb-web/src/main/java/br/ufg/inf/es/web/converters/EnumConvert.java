/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.converters;

import br.ufg.inf.es.base.persistence.biblioteca.DBDriver;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EnumType;

/**
 *
 * @author igor
 */
@FacesConverter("enumTypeConverter")
public class EnumConvert implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if ((value != null) || (!value.equals(""))) {
            return DBDriver.valueOf(value);
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null){// && value instanceof EnumType) {
            return ((DBDriver) value).name();
        }
        return null;
    }
}
