
package br.ufg.inf.es.web.converters.stringconverters;

import javax.faces.convert.FacesConverter;

/**
 *
 * @author Bruno Marquete
 */
@FacesConverter("string15Converter")
public class String15Converter extends StringConverter {

    private static final int LIMITE_MAXIMO = 15;
    
    public String15Converter() {
        super(LIMITE_MAXIMO);
    }
    
}
