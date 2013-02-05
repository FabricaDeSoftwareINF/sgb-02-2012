package br.ufg.inf.es.integracao.cotacao;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.buscape.developer.result.type.Offer;
import com.buscape.developer.result.type.Product;
import com.buscape.developer.result.type.Result;

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
		
		for (Offer offer : result.getOffers()) {
			System.out.println(offer.getId() + "-" + offer.getSeller().getSellerName());
                        
		}
                for (Product product : result.getProducts()) {
                    System.out.println(product.getProductName() + " - " + product.getPriceMax() );
                    System.out.println(product.toString());
                
            }

	}

}
