package br.ufg.inf.es.integracao.cotacao;

import com.google.gson.Gson;
import java.util.Map;

/**
 * Classe fornece servi&ccedil;os para a convers&atilde;o de moeda.
 * @author Inael
 */
class ConversorMoeda {
	
	private ConversorMoeda(){
		
	}

    private static final String URL = "http://www.google.com/ig/calculator?hl=en&q=%sUSD=?BRL";

    /**
     * Converte um valor em dolar para um valor em real usando a api do google.
     * @param dolar Valor para converter em real
     * @return O valor em real
     */
    public static String converterDolarParaReal(String dolar) {
        
        String resultadoJson = HttpUtil.fazerRequisicaoHttpGet(String.format(URL, dolar));
        
        Gson gson = new Gson();
        
        Map map = gson.fromJson(resultadoJson, Map.class);
        
        return map.get("rhs").toString();
    }
    /**
     * Converte um valor em dolar para um valor em real usando a api do google.
     * @param dolar Valor para converter em real
     * @return O valor em real
     */
    public static Double converterDolarParaReal(Double dolar) {
        
        String resultadoJson = HttpUtil.fazerRequisicaoHttpGet(String.format(URL, dolar.toString()));
        
        Gson gson = new Gson();
        
        Map map = gson.fromJson(resultadoJson, Map.class);
        
        String stringReal = (String)map.get("rhs");
        
        return Double.parseDouble(stringReal.replaceAll("[a-zA-Z]", ""));
    }

    
}
