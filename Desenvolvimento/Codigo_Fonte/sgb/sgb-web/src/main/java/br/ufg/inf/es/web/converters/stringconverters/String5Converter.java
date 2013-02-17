
package br.ufg.inf.es.web.converters.stringconverters;

import javax.faces.convert.FacesConverter;

/**
 *
 * @author Bruno Marquete
 */
@FacesConverter("string5Converter")
public class String5Converter extends StringConverter {

    private static final int LIMITE_MAXIMO = 5;
    
    public String5Converter() {
        super(LIMITE_MAXIMO);
    }
    
}
