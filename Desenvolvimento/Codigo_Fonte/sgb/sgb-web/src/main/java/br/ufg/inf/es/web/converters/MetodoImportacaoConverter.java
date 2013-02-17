/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.converters;

import br.ufg.inf.es.model.importacaobibliografia.MetodoImportacao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author igor
 */
@FacesConverter("enumMetodoImportacaoConverter")
public class MetodoImportacaoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if ((value != null) || (!value.equals("Selecione"))) {
            return MetodoImportacao.valueOf(value);
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null){// && value instanceof EnumType) {
            return ((MetodoImportacao) value).name();
        }
        return null;
    }
}