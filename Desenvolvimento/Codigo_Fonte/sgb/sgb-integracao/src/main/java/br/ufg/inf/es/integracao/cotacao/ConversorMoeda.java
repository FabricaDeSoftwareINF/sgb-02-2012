/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.cotacao;

import com.google.gson.Gson;
import java.util.Map;

/**
 *
 * @author Inael
 */
public class ConversorMoeda {

    private static String URL = "http://www.google.com/ig/calculator?hl=en&q=%sUSD=?BRL";

    
    /**
     * Converte um valor em dolar para um valor em real usando a api do google.
     * @param dolar Valor para converter em real
     * @return O valor em real
     */
    public static String ConverterDolarParaReal(String dolar) {
        String resultadoJson = HttpUtil.FazerRequisicaoHttpGet(String.format(URL, dolar));
        Gson gson = new Gson();
        Map map = gson.fromJson(resultadoJson, Map.class);
        return map.get("rhs").toString();
    }
    /**
     * Converte um valor em dolar para um valor em real usando a api do google.
     * @param dolar Valor para converter em real
     * @return O valor em real
     */
    public static Double ConverterDolarParaReal(Double dolar) {
        String resultadoJson = HttpUtil.FazerRequisicaoHttpGet(String.format(URL, dolar.toString()));
        Gson gson = new Gson();
        Map map = gson.fromJson(resultadoJson, Map.class);
        String stringReal = (String)map.get("rhs");
        
        return Double.parseDouble(stringReal.replaceAll("[a-zA-Z]", ""));
    }

    
}
