package br.ufg.inf.es.model.importacaobibliografia;

import java.util.List;

/**
 * Entidade para a BibliografiaImportada
 *
 * @author Vinícius
 */
public class BibliografiaImportada {

    /**
     * Campo basica
     */
    private List<LivroImportado> basica;
    /**
     * Campo complementar
     */
    private List<LivroImportado> complementar;
    /**
     * Campo sugerida
     */
    private List<LivroImportado> sugerida;

    /**
     * Obtém o valor do campo
     * <code>basica</code>
     *
     * @return {@link List<LivroImportado>}
     */
    public List<LivroImportado> getBasica() {
        return this.basica;
    }

    /**
     * Define o campo
     * <code>basica</code>.
     *
     * @param basica
     */
    public void setBasica(List<LivroImportado> basica) {
        this.basica = basica;
    }

    /**
     * Obtém o valor do campo
     * <code>complementar</code>
     *
     * @return {@link List<LivroImportado>}
     */
    public List<LivroImportado> getComplementar() {
        return this.complementar;
    }

    /**
     * Define o campo
     * <code>complementar</code>.
     *
     * @param complementar
     */
    public void setComplementar(List<LivroImportado> complementar) {
        this.complementar = complementar;
    }

    /**
     * Obtém o valor do campo
     * <code>sugerida</code>
     *
     * @return {@link List<LivroImportado>}
     */
    public List<LivroImportado> getSugerida() {
        return this.sugerida;
    }

    /**
     * Define o campo
     * <code>sugerida</code>.
     *
     * @param sugerida
     */
    public void setSugerida(List<LivroImportado> sugerida) {
        this.sugerida = sugerida;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "BibliografiaImportada{" + "basica=" + basica
                + ", complementar=" + complementar + ", sugerida="
                + sugerida + '}';
    }
}
