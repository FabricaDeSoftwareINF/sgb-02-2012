/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.cotacao;

import br.ufg.inf.es.model.Livraria;
import com.buscape.developer.result.type.Offer;
import com.buscape.developer.result.type.PriceOffer;
import com.buscape.developer.result.type.Result;
import com.buscape.developer.result.type.Seller;
import com.google.gson.Gson;
import com.google.gson.internal.StringMap;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


/**
 *
 * @author usuario
 */
public class CotadorGoogleShop extends Cotador {

    private static String Key = "AIzaSyDzuxDAgvInL9nk7jIlBcs9cPSSnfbJfrw";
    private static String URL = "https://www.googleapis.com/shopping/search/v1/public/products?key=" + Key + "&country=US&alt=json&currency=USD&restrictBy=condition:new";
   

   

    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        CotadorGoogleShop g = new CotadorGoogleShop();
        String a = g.buscarLivro("9780470447628");
        System.out.println(a);
    }

    @Override
    public String gerarUrlDeBusca(String isbn) {
        return URL + "&q=" + isbn;
    }

    @Override
    public Map<Livraria, OfertaLivro> buscarOfertas(String isbn) {
        Gson gson = new Gson();
        String json = this.buscarLivro(isbn);
        Map map = gson.fromJson(json, Map.class);
        //Integer totalOfertas = (Integer) map.get("totalItems");
        Map<Livraria, OfertaLivro> resultado = new HashMap();
        
        List<StringMap> itens = (List<StringMap>) map.get("items");
        for (StringMap item : itens) {
          

            StringMap produto = (StringMap) item.get("product");
            String linkImagemLivro = null;
            String precoLivro = null;
            String moeda = null;
            for (StringMap stringMapImagem : (List<StringMap>) produto.get("images")) {
                if (stringMapImagem.containsKey("link")) {
                    linkImagemLivro = (String) stringMapImagem.get("link");
                }
            }
            List<StringMap> invetories = (List<StringMap>) produto.get("inventories");
            for (StringMap stringMap : invetories) {
                precoLivro = (String) stringMap.get("price").toString();
                moeda = (String) stringMap.get("currency");

            }
            String nomeLivraria = (String) ((StringMap) produto.get("author")).get("name");
            String nomeLivro= (String) produto.get("title");
            String descricaoLivro= (String) produto.get("description");
            String linkLivroNaLivraria=(String) produto.get("link");
            Livraria livraria = new Livraria();
            livraria.setNome(nomeLivraria);
            livraria.setSite(linkLivroNaLivraria);
           
            OfertaLivro oferta = new OfertaLivro(nomeLivro, descricaoLivro, precoLivro, moeda, nomeLivraria, linkLivroNaLivraria, linkImagemLivro, null);
            resultado.put(livraria, oferta);
        }
        return resultado;
    }
}
