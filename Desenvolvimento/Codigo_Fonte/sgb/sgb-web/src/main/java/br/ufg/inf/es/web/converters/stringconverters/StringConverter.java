
package br.ufg.inf.es.web.converters.stringconverters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Bruno Marquete
 */
@FacesConverter("stringConverter")
public abstract class StringConverter implements Converter {

    private int limiteMaximo;

    public StringConverter(int limiteMaximo) {
        this.limiteMaximo = limiteMaximo;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {

        if (value.trim().equals("")) {
            return null;
        } else {
            return value;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return estruturarString((String) value);
        }
    }

    private String estruturarString(String value) {

        StringBuilder builder = new StringBuilder();
        final int tamanho = value.length();

        if (tamanho > limiteMaximo) {

            for (int i = 1; i <= tamanho; i++) {

                if (i % limiteMaximo == 0) {
                    builder.append(value.substring(i - limiteMaximo, i)).append("<br/>");

                    if (tamanho - i < limiteMaximo && tamanho - i != 0) {
                        builder.append(value.substring(i, tamanho));
                        break;
                    }
                }
            }

        } else {
            builder.append(value);
        }

        return builder.toString();
    }
}
