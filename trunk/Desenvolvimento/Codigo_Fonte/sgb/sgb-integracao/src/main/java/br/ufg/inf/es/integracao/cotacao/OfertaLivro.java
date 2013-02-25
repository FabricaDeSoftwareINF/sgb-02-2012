package br.ufg.inf.es.integracao.cotacao;

import br.ufg.inf.es.model.Livro;

/**
 *
 * @author Inael Rodrigues
 */
public class OfertaLivro {

    private Livro livro;
    private String nomeLivro;
    private String descricaoLivro;
    private String precoLivro;
    private String moeda;
    private String nomeLivraria;
    private String linkLivroNaLivraria;
    private String linkImagemLIvro;
    private String linkLogoLivraria;

    public OfertaLivro() {
    }

    public OfertaLivro(String nomeLivro, String descricaoLivro, 
            String precoLivro, String moeda, String nomeLivraria, 
            String linkLivroNaLivraria, String linkImagemLIvro, 
            String linkLogoLivraria) {
        this.nomeLivro = nomeLivro;
        this.descricaoLivro = descricaoLivro;
        this.precoLivro = precoLivro;
        this.moeda = moeda;
        this.nomeLivraria = nomeLivraria;
        this.linkLivroNaLivraria = linkLivroNaLivraria;
        this.linkImagemLIvro = linkImagemLIvro;
        this.linkLogoLivraria = linkLogoLivraria;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
        
    public String getDescricaoLivro() {
        return descricaoLivro;
    }

    public void setDescricaoLivro(String descricaoLivro) {
        this.descricaoLivro = descricaoLivro;
    }

    public String getLinkImagemLivro() {
        return linkImagemLIvro;
    }

    public void setLinkImagemLIvro(String linkImagemLIvro) {
        this.linkImagemLIvro = linkImagemLIvro;
    }

    public String getLinkLivroNaLivraria() {
        return linkLivroNaLivraria;
    }

    public void setLinkLivroNaLivraria(String linkLivroNaLivraria) {
        this.linkLivroNaLivraria = linkLivroNaLivraria;
    }

    public String getLinkLogoLivraria() {
        return linkLogoLivraria;
    }

    public void setLinkLogoLivraria(String linkLogoLivraria) {
        this.linkLogoLivraria = linkLogoLivraria;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public String getNomeLivraria() {
        return nomeLivraria;
    }

    public void setNomeLivraria(String nomeLivraria) {
        this.nomeLivraria = nomeLivraria;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getPrecoLivro() {
        return precoLivro;
    }

    public void setPrecoLivro(String precoLivro) {
        this.precoLivro = precoLivro;
    }
    
}
