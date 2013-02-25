package br.ufg.inf.es.web.converters.stringconverters;

import javax.faces.convert.FacesConverter;

/**
 *
 * @author Bruno Marquete
 */
@FacesConverter("string20Converter")
public class String20Converter extends StringConverter {

    private static final int LIMITE_MAXIMO = 20;

    /**
     * Adiciona quebras de linha a cada 20 caracteres
     */
    public String20Converter() {
        super(LIMITE_MAXIMO);
    }
}
