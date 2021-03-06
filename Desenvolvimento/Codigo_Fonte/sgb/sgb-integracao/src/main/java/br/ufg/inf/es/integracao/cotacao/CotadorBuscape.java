package br.ufg.inf.es.integracao.cotacao;

import br.ufg.inf.es.model.Livraria;
import com.buscape.developer.result.type.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Fonercer os servi&ccedil;os para a cota&ccedil&atilde;o via Buscap&eacute;
 *
 * @author Alunoinf_2
 */
public class CotadorBuscape extends Cotador {

    private Unmarshaller unmarshaller;

    /**
     * Construtor publico
     */
    public CotadorBuscape() {
        try {
            JAXBContext jc = JAXBContext.newInstance("com.buscape.developer.result.type");
            this.unmarshaller = jc.createUnmarshaller();
        } catch (JAXBException ex) {
            Logger.getLogger(CotadorGoogleShop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retorna uma cole&ccedil:&atilde;o de
     * <code>ResultadoCotacao</code>
     *
     * @param isbn Isbn utilizado na cota&ccedil:&atilde;o
     * @return
     */
    @Override
    public Collection<ResultadoCotacao> buscarOfertas(String isbn) {
        Collection<ResultadoCotacao> map = new ArrayList<ResultadoCotacao>();
        try {
            Result result = (Result) unmarshaller.unmarshal(new URL(gerarUrlDeBusca(isbn)));
            for (Offer offer : result.getOffers()) {
                PriceOffer priceOffer = offer.getPrice();
                Seller seller = offer.getSeller();
                String nomeLivro = offer.getOfferName();
                String descricaoLivro = offer.getShortDescription();
                String precoLivro = priceOffer.getValue();
                String moeda = priceOffer.getCurrency().getAbbreviation();
                String nomeLivraria = seller.getSellerName();
                String linkLivraria = seller.getLinks().getLinks().get(0).getUrl();
                String linkLivroNaLivraria = "http://buscape.com.br" + offer.getLinks().getLinks().get(0).getUrl();

                Thumbnail thumbanailLivro = offer.getThumbnail();
                String linkImagemLIvro = thumbanailLivro == null ? "" : thumbanailLivro.getUrl();

                Thumbnail thumbanailLivraria = offer.getSeller().getThumbnail();
                String linkLogoLivraria = thumbanailLivraria == null ? "" : thumbanailLivraria.getUrl();

                OfertaLivro oferta = new OfertaLivro(nomeLivro, descricaoLivro, precoLivro, moeda, nomeLivraria, linkLivroNaLivraria, linkImagemLIvro, linkLogoLivraria);
                Livraria livraria = new Livraria();
                livraria.setNome(nomeLivraria);
                livraria.setSite(linkLivraria);
                livraria.setUrlLogo(linkLogoLivraria);

                ResultadoCotacao resultadoCotacao = new ResultadoCotacao(livraria, oferta);
                map.add(resultadoCotacao);
            }
        } catch (Exception ex) {
            Logger.getLogger(CotadorGoogleShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

    /**
     * Gera a url de busca
     * @param isbn
     * @return 
     */
    @Override
    public String gerarUrlDeBusca(String isbn) {
        return "http://sandbox.buscape.com/service/findOfferList/564771466d477a4458664d3d/?categoryId=3482&keyword=" + isbn;
    }

    /**
     * Define um novo unmarshaller
     * @param u 
     */
    public void setUnmarshaller(Unmarshaller u) {
        this.unmarshaller = u;
    }
}