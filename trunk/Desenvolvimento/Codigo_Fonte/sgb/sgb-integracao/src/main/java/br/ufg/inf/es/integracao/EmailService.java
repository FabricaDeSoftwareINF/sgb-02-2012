/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.integracao;

import br.ufg.inf.es.model.Usuario;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author diogo
 */
public class EmailService {
    public static void enviarNovaSenha(Usuario usuario){
        HtmlEmail email = new HtmlEmail();
        Properties properties = new Properties();
        try {
            properties.load(EmailService.class.getResourceAsStream("/email.properties"));
            String server = properties.getProperty("email.server");
            Boolean usarSsl = new Boolean(properties.getProperty("email.ssl"));
            Boolean usarTsl = new Boolean(properties.getProperty("email.tsl"));
            String porta = properties.getProperty("email.port");
            String emailUsuario = properties.getProperty("email.usuario");
            String senha = properties.getProperty("email.senha");
            
            email.setAuthentication(emailUsuario, senha);
            email.setSmtpPort(Integer.parseInt(porta));
            email.setHostName(server);
            email.setSSL(usarSsl);
            email.setTLS(usarTsl);
            email.setSubject("Nova Senha");
            email.setFrom(usuario.getEmail());
            email.addTo(usuario.getEmail(), usuario.getNome());
            email.setMsg("Nova senha: "+ usuario.getSenha());
            email.setDebug(true);
            email.send();
        } catch (Exception ex) { 
            ex.printStackTrace();
        }
    }
}
