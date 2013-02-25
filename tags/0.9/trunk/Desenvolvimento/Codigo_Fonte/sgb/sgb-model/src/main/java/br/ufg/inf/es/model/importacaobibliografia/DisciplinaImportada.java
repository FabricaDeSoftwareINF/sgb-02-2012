package br.ufg.inf.es.model.importacaobibliografia;

/**
 * Entidade DisciplinaImportada
 *
 * @author Vinícius
 */
public class DisciplinaImportada {

    /**
     * Campo nome
     */
    private String nome;
    /**
     * Campo codigo
     */
    private String codigo;
    /**
     * Campo bibliografia
     */
    private BibliografiaImportada bibliografia;

    /**
     * Obtém o valor do campo
     * <code>nome</code>
     *
     * @return {@link String}
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Define o campo
     * <code>nome</code>.
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o valor do campo
     * <code>codigo</code>
     *
     * @return {@link String}
     */
    public String getCodigo() {
        return this.codigo;
    }

    /**
     * Define o campo
     * <code>codigo</code>.
     *
     * @param codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtém o valor do campo
     * <code>bibliografia</code>
     *
     * @return {@link BibliografiaImportada}
     */
    public BibliografiaImportada getBibliografia() {
        return this.bibliografia;
    }

    /**
     * Define o campo
     * <code>bibliografia</code>.
     *
     * @param bibliografia
     */
    public void setBibliografia(BibliografiaImportada bibliografia) {
        this.bibliografia = bibliografia;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "DisciplinaImportada{" + "nome=" + nome + ", codigo="
                + codigo + ", bibliografia=" + bibliografia + '}';
    }
}
