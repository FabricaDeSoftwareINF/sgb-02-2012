
package br.ufg.inf.es.web.converters.stringconverters;

import javax.faces.convert.FacesConverter;

/**
 *
 * @author Bruno Marquete
 */
@FacesConverter("string10Converter")
public class String10Converter extends StringConverter {

    private static final int LIMITE_MAXIMO = 10;
    
    public String10Converter() {
        super(LIMITE_MAXIMO);
    }
    
}
