/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.cotacao;

import br.ufg.inf.es.model.Livraria;
import com.google.gson.Gson;
import com.google.gson.internal.StringMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author usuario
 */
public class CotadorGoogleShop extends Cotador {

    private static final String KEY = "AIzaSyDzuxDAgvInL9nk7jIlBcs9cPSSnfbJfrw";
    
    private static final String URL = "https://www.googleapis.com/shopping/search/v1/public/products?key=" + KEY + "&country=US&alt=json&currency=USD&restrictBy=condition:new";  

    public static void main(String[] args) {

        CotadorGoogleShop g = new CotadorGoogleShop();
        
        String a = g.buscarLivro("9780470447628");
        
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
