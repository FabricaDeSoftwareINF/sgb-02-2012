/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.cotacao;

import java.net.URL;
import java.util.Map;

/**
 *
 * @author Alunoinf_2
 */
public class CotadorBuscape extends Cotador{

    private static final String Key = "";
    private static final String URL = "http://sandbox.buscape.com/service/findProductList/<application_id>/?keyword=keyword";
    @Override
    public Map<String, Map<String, String>> ConverterJsonParaDicionario(String json) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override   
    public String GerarUrlDeBusca(String isbn) {
        return "http://sandbox.buscape.com/service/findOfferList/564771466d477a4458664d3d/?categoryId=3482&keyword="+isbn;
    }
 
   
    
}
