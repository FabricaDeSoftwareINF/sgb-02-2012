/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.cotacao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
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
    
    public static String FazerRequisicaoHttpGet(String url) {
        StringBuilder sb = new StringBuilder();

        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }


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
