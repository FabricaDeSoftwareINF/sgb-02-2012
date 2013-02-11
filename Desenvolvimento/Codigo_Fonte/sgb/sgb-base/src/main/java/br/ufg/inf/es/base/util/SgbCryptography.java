/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.base.util;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para realização de criptografia.
 * @author diogo
 */
public class SgbCryptography implements Serializable{
	
    /** Campo serialVersionUID*/
	private static final long serialVersionUID = 34017933313957449L;

	/**
	 * Método que encripta uma String.
	 *
	 * @param text
	 * @return A String encriptada.
	 */
	public String encrypt(String text) {
		
        try {
        	
            MessageDigest encrypter = MessageDigest.getInstance("MD5");
            
            encrypter.update(text.getBytes(Charset.forName("UTF-8")));
            
            byte[] result = encrypter.digest();

            return getStringFromBytes(result);
            
        } catch (NoSuchAlgorithmException e) {
            
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getLocalizedMessage());
            
            return "";
        }
    }
    
    /**
     * Método que obtém uma String de um array de bytes.
     *
     * @param bytes
     * @return A String obtida.
     */
    private String getStringFromBytes(byte[] bytes){
        
        StringBuffer result = new StringBuffer();
        
        for(byte actualByte: bytes){
            
            result.append(actualByte);
        }

        return result.toString().replaceAll("-", "");
    }
}
