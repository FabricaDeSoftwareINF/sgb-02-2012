/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.cotacao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author Inael
 */
public class HttpUtil {
    /**
     * Faz requisição http GET em uma determinada URL.
     *
     * @param url Url String que se deseja requisitar
     * @return Json com o resultado da requisição.
     */
    
    public static String fazerRequisicaoHttpGet(String url) {
        StringBuilder sb = new StringBuilder();
        BufferedReader rd;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            // Get the response
            rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), Charset.forName("UTF-8")));
            String line = "";
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            
            rd.close();

        } catch (URISyntaxException ex) {
            Logger.getLogger(Cotador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HttpException ex) {
            Logger.getLogger(Cotador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cotador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            return sb.toString();
        }
    }
}
