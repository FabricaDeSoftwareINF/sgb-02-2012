/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.cotacao;

import br.ufg.inf.es.model.Livraria;
import com.google.gson.Gson;
import com.google.gson.internal.StringMap;
import java.io.*;
import java.net.URISyntaxException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.springframework.http.HttpRequest;
import sun.misc.IOUtils;

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
        Map m = gson.fromJson(a, Map.class);
        Map<String, Map<String, String>> map = g.ConverterJsonParaDicionario(a);
        System.out.println(a);
    }
    
    @Override
    public Map<String, Map<String, String>> ConverterJsonParaDicionario(String json) {
        Gson gson = new Gson();
        Map map = gson.fromJson(json, Map.class);
        //Integer totalOfertas = (Integer) map.get("totalItems");
        Map<String, Map<String, String>> resultado = new HashMap<String, Map<String, String>>();
        
        List<StringMap> itens = (List<StringMap>) map.get("items");
        for (StringMap item : itens) {
            Map<String, String> oferta = new HashMap<String, String>();
            
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
            oferta.put("paisLivraria", (String) produto.get("country"));
            oferta.put("nomeLivro", (String) produto.get("title"));
            oferta.put("descricaoLivro", (String) produto.get("description"));
            oferta.put("linkLivroNaLivraria", (String) produto.get("link"));
            oferta.put("linkImagemDoLivro", linkImagemLivro);
            oferta.put("precoLivro", precoLivro);
            oferta.put("moeda", moeda);
            resultado.put(nomeLivraria, oferta);
        }
        return resultado;
    }
    
    @Override
    public String GerarUrlDeBusca(String isbn) {
        return URL + "&q=" + isbn;
    }
}
