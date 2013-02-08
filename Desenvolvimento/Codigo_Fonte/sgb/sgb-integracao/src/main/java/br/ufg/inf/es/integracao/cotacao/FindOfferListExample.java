package br.ufg.inf.es.integracao.cotacao;

import br.ufg.inf.es.model.Livraria;
import com.buscape.developer.result.type.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class FindOfferListExample {

	/**
	 * 
	 * Executes a basic offer list search
	 * 
	 * Selected Product Id: 222016
	 * Selected Application Id: 564771466d477a4458664d3d (Test Id)
	 * 
	 * @param args
	 * @throws JAXBException
	 * @throws MalformedURLException
	 */
	public static void main(String[] args) throws JAXBException,
			MalformedURLException {

		JAXBContext jc = JAXBContext.newInstance("com.buscape.developer.result.type");

		Unmarshaller u = jc.createUnmarshaller();

		Result result = (Result) u.unmarshal(new URL("http://sandbox.buscape.com/service/findOfferList/564771466d477a4458664d3d/?categoryId=3482&keyword=9788576055631"));		
		Map<Livraria, OfertaLivro> map = new HashMap<Livraria, OfertaLivro>();
                
		for (Offer offer : result.getOffers()){
                    PriceOffer priceOffer  = offer.getPrice();
                    Seller seller = offer.getSeller();
                    
                    String nomeLivro = offer.getOfferName();
                    String descricaoLivro = offer.getShortDescription();
                    String precoLivro = priceOffer.getValue();
                    String moeda = priceOffer.getCurrency().getAbbreviation();
                    String nomeLivraria  = seller.getSellerName();
                    String linkLivraria = seller.getLinks().getLinks().get(0).getUrl();
                    String linkLivroNaLivraria = "http://buscape.com.br"+ offer.getLinks().getLinks().get(0).getUrl();
                    String linkImagemLIvro = offer.getThumbnail().getUrl();
                    String linkLogoLivraria = offer.getSeller().getThumbnail().getUrl();
                    
                    OfertaLivro oferta = new OfertaLivro(nomeLivro, descricaoLivro, precoLivro, moeda, nomeLivraria, linkLivroNaLivraria, linkImagemLIvro, linkLogoLivraria);
                    Livraria livraria = new Livraria();
                    map.put(livraria, oferta);
                    System.out.println(offer.getId() + "-" + offer.getSeller().getSellerName());
                        
		}
               
                for (Product product : result.getProducts()) {
                    System.out.println(product.getProductName() + " - " + product.getPriceMax() );
                    System.out.println(product.toString());
                
            }

	}

}
