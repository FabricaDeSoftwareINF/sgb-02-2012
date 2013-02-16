/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model.exportacaodados.planilha;

/**
 * Entidade ItemPlanilha
 * @author Bruno Marquete da Silva
 */
public class ItemPlanilha {

    /** Campo numItem*/
    private int numItem = 0;
    
    /** Campo nomeAutor*/
    private String nomeAutor = "";
    
    /** Campo tituloObra*/
    private String tituloObra = "";
    
    /** Campo edicao*/
    private String edicao = "";
    
    /** Campo editora*/
    private String editora = "";
    
    /** Campo local*/
    private String local = "";
    
    /** Campo ano*/
    private String ano = "";
    
    /** Campo colecao*/
    private String colecao = "";
    
    /** Campo volume*/
    private String volume = "";
    
    /** Campo matriculaSophiaConselheiro*/
    private String matriculaSophiaConselheiro = "";
    
    /** Campo cursoDestino*/
    private String cursoDestino = "";
    
    /** Campo unidadeMedida*/
    private String unidadeMedida = "";
    
    /** Campo valorMedioUnitario*/
    private double valorMedioUnitario = 0.0;
    
    /** Campo quantExemplares*/
    private int quantExemplares = 0;
    
    /** Campo areaConhecimento*/
    private String areaConhecimento = "";
    
    /** Campo NUM_COLUNAS*/
    private static final short NUM_COLUNAS = 19;

    /**
     * Obtém o valor do campo <code>numItem</code>
     *
     * @return {@link int}
     */
    public int getNumItem() {
            return this.numItem;
    }

    /**
     * Define o campo <code>numItem</code>.
     *
     * @param numItem 
     */
    public void setNumItem(int numItem) {
            this.numItem = numItem;
    }

    /**
     * Obtém o valor do campo <code>nomeAutor</code>
     *
     * @return {@link String}
     */
    public String getNomeAutor() {
            return this.nomeAutor;
    }

    /**
     * Define o campo <code>nomeAutor</code>.
     *
     * @param nomeAutor 
     */
    public void setNomeAutor(String nomeAutor) {
            this.nomeAutor = nomeAutor;
    }

    /**
     * Obtém o valor do campo <code>tituloObra</code>
     *
     * @return {@link String}
     */
    public String getTituloObra() {
            return this.tituloObra;
    }

    /**
     * Define o campo <code>tituloObra</code>.
     *
     * @param tituloObra 
     */
    public void setTituloObra(String tituloObra) {
            this.tituloObra = tituloObra;
    }

    /**
     * Obtém o valor do campo <code>edicao</code>
     *
     * @return {@link String}
     */
    public String getEdicao() {
            return this.edicao;
    }

    /**
     * Define o campo <code>edicao</code>.
     *
     * @param edicao 
     */
    public void setEdicao(String edicao) {
            this.edicao = edicao;
    }

    /**
     * Obtém o valor do campo <code>editora</code>
     *
     * @return {@link String}
     */
    public String getEditora() {
            return this.editora;
    }

    /**
     * Define o campo <code>editora</code>.
     *
     * @param editora 
     */
    public void setEditora(String editora) {
            this.editora = editora;
    }

    /**
     * Obtém o valor do campo <code>local</code>
     *
     * @return {@link String}
     */
    public String getLocal() {
            return this.local;
    }

    /**
     * Define o campo <code>local</code>.
     *
     * @param local 
     */
    public void setLocal(String local) {
            this.local = local;
    }

    /**
     * Obtém o valor do campo <code>ano</code>
     *
     * @return {@link String}
     */
    public String getAno() {
            return this.ano;
    }

    /**
     * Define o campo <code>ano</code>.
     *
     * @param ano 
     */
    public void setAno(String ano) {
            this.ano = ano;
    }

    /**
     * Obtém o valor do campo <code>colecao</code>
     *
     * @return {@link String}
     */
    public String getColecao() {
            return this.colecao;
    }

    /**
     * Define o campo <code>colecao</code>.
     *
     * @param colecao 
     */
    public void setColecao(String colecao) {
            this.colecao = colecao;
    }

    /**
     * Obtém o valor do campo <code>volume</code>
     *
     * @return {@link String}
     */
    public String getVolume() {
            return this.volume;
    }

    /**
     * Define o campo <code>volume</code>.
     *
     * @param volume 
     */
    public void setVolume(String volume) {
            this.volume = volume;
    }

    /**
     * Obtém o valor do campo <code>matriculaSophiaConselheiro</code>
     *
     * @return {@link String}
     */
    public String getMatriculaSophiaConselheiro() {
            return this.matriculaSophiaConselheiro;
    }

    /**
     * Define o campo <code>matriculaSophiaConselheiro</code>.
     *
     * @param matriculaSophiaConselheiro 
     */
    public void setMatriculaSophiaConselheiro(String matriculaSophiaConselheiro) {
            this.matriculaSophiaConselheiro = matriculaSophiaConselheiro;
    }

    /**
     * Obtém o valor do campo <code>cursoDestino</code>
     *
     * @return {@link String}
     */
    public String getCursoDestino() {
            return this.cursoDestino;
    }

    /**
     * Define o campo <code>cursoDestino</code>.
     *
     * @param cursoDestino 
     */
    public void setCursoDestino(String cursoDestino) {
            this.cursoDestino = cursoDestino;
    }

    /**
     * Obtém o valor do campo <code>unidadeMedida</code>
     *
     * @return {@link String}
     */
    public String getUnidadeMedida() {
            return this.unidadeMedida;
    }

    /**
     * Define o campo <code>unidadeMedida</code>.
     *
     * @param unidadeMedida 
     */
    public void setUnidadeMedida(String unidadeMedida) {
            this.unidadeMedida = unidadeMedida;
    }

    /**
     * Obtém o valor do campo <code>valorMedioUnitario</code>
     *
     * @return {@link double}
     */
    public double getValorMedioUnitario() {
            return this.valorMedioUnitario;
    }

    /**
     * Define o campo <code>valorMedioUnitario</code>.
     *
     * @param valorMedioUnitario 
     */
    public void setValorMedioUnitario(double valorMedioUnitario) {
            this.valorMedioUnitario = valorMedioUnitario;
    }

    /**
     * Obtém o valor do campo <code>quantExemplares</code>
     *
     * @return {@link int}
     */
    public int getQuantExemplares() {
            return this.quantExemplares;
    }

    /**
     * Define o campo <code>quantExemplares</code>.
     *
     * @param quantExemplares 
     */
    public void setQuantExemplares(int quantExemplares) {
            this.quantExemplares = quantExemplares;
    }

    /**
     * Obtém o valor do campo <code>areaConhecimento</code>
     *
     * @return {@link String}
     */
    public String getAreaConhecimento() {
            return this.areaConhecimento;
    }

    /**
     * Define o campo <code>areaConhecimento</code>.
     *
     * @param areaConhecimento 
     */
    public void setAreaConhecimento(String areaConhecimento) {
            this.areaConhecimento = areaConhecimento;
    }

    /**
     * Obtém o valor do campo <code>numColunas</code>
     *
     * @return {@link short}
     */
    public static short getNumColunas() {
            return NUM_COLUNAS;
    }
}
