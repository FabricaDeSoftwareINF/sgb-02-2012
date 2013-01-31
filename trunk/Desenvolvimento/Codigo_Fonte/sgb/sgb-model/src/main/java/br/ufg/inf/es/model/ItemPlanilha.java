/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

/**
 *
 * @author Bruno Marquete da Silva
 */
public class ItemPlanilha {

    private int numItem = 0;
    private String nomeAutor = "";
    private String tituloObra = "";
    private String edicao = "";
    private String editora = "";
    private String local = "";
    private String ano = "";
    private String colecao = "";
    private String volume = "";
    private String matriculaSophiaConselheiro = "";
    private String cursoDestino = "";
    private String unidadeMedida = "";
    private double valorMedioUnitario = 0.0;
    private int quantExemplares = 0;
    private String areaConhecimento = "";
    private static final short NUM_COLUNAS = 19;

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getAreaConhecimento() {
        return areaConhecimento;
    }

    public void setAreaConhecimento(String areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    public String getColecao() {
        return colecao;
    }

    public void setColecao(String colecao) {
        this.colecao = colecao;
    }

    public String getCursoDestino() {
        return cursoDestino;
    }

    public void setCursoDestino(String cursoDestino) {
        this.cursoDestino = cursoDestino;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getMatriculaSophiaConselheiro() {
        return matriculaSophiaConselheiro;
    }

    public void setMatriculaSophiaConselheiro(String matriculaSophiaConselheiro) {
        this.matriculaSophiaConselheiro = matriculaSophiaConselheiro;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public int getNumItem() {
        return numItem;
    }

    public void setNumItem(int numItem) {
        this.numItem = numItem;
    }

    public int getQuantExemplares() {
        return quantExemplares;
    }

    public void setQuantExemplares(int quantExemplares) {
        this.quantExemplares = quantExemplares;
    }

    public String getTituloObra() {
        return tituloObra;
    }

    public void setTituloObra(String tituloObra) {
        this.tituloObra = tituloObra;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public double getValorMedioUnitario() {
        return valorMedioUnitario;
    }

    public void setValorMedioUnitario(double valorMedioUnitario) {
        this.valorMedioUnitario = valorMedioUnitario;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public static short getNUM_COLUNAS() {
        return NUM_COLUNAS;
    }
}
