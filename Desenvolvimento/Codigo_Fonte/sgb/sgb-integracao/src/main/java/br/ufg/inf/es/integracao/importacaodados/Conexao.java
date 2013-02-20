/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao.importacaodados;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.http.HttpException;

/**
 *
 * @author vinicius
 */
public class Conexao {
    
    
    public String httpGet(String urlServico, String parametro) {
        StringBuilder urlParametro = new StringBuilder(urlServico);

        Client client = Client.create();
        WebResource webResource = client
                .resource(urlParametro.toString());
        if (parametro != null && (!parametro.isEmpty())) {
            if (urlServico.substring(urlServico.length() - 1, urlServico.length()).equals("/")) {
                webResource = client
                        .resource(urlParametro.append(parametro).toString());
            } else {
                webResource = client
                        .resource(urlParametro.append("/").append(parametro).toString());
            }
        }
        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);
    
        return response.getEntity(String.class);
    }
    
}
