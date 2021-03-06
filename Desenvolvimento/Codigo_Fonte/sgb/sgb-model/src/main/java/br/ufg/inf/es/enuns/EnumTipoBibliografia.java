package br.ufg.inf.es.enuns;

/**
 * Enum para definir os tipos de bibliografia.
 *
 * @author Cássio Augusto
 */
public enum EnumTipoBibliografia {

    /**
     * Campo BASICA
     */
    BASICA("Básica"),
    /**
     * Campo COMPLEMENTAR
     */
    COMPLEMENTAR("Complementar"),
    /**
     * Campo SUGERIDA
     */
    SUGERIDA("Sugerida");
    /**
     * Campo value
     */
    private String value;

    /**
     * Construtor desta classe.
     *
     * @param value
     */
    private EnumTipoBibliografia(String value) {

        this.value = value;
    }

    /**
     * Obtém o valor do campo
     * <code>value</code>
     *
     * @return {@link String}
     */
    public String getValue() {
        return value;
    }
}