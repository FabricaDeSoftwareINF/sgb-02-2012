/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.base.util;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author diogo
 */
public class SgbCryptography implements Serializable{
    public String encrypt(String text) {
        try {
            MessageDigest encrypter = MessageDigest.getInstance("MD5");
            encrypter.update(text.getBytes());
            byte[] result = encrypter.digest();

            return getStringFromBytes(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
    
    private String getStringFromBytes(byte[] bytes){
        StringBuffer result = new StringBuffer();
        for(byte actualByte: bytes){
            result.append(actualByte);
        }

        return result.toString().replaceAll("-", "");
    }
}
