package br.ufg.inf.es.base.util.cripto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Classe de enncriptação e decripatção utilizando o método de encritação RSA 
 * (encriptação de chave pública)
 * @author igor
 */
public class CriptoGeneric implements Serializable{

    private static final long serialVersionUID = -2197693393634639351L;
    private static final int TAMANHO_2048_BYTES = 2048;
    private SecureRandom random;
    private Keys keys = new Keys();

    public CriptoGeneric(){
        setUp();
    }

    private void setUp(){

        if (isKeys()) {
            recoveKeys();
        } else {
            createKey();
        }
    }

    /**
     * Método que gera as chaves de encritação e decriptação, as chaves Privada
     * e a chave ublica.
     */
    private void createKey(){
        this.random = new SecureRandom();

        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
            
            //gero uma senha de 2048 bytes.
            generator.initialize(CriptoGeneric.TAMANHO_2048_BYTES, this.random); 
            
            //Ele gera as duas senhas, uma publica e a outra privada
            KeyPair pair = generator.generateKeyPair(); 
            
            //senha publica
            PublicKey pubKey = pair.getPublic(); 
            
            this.keys.setPubKey(pubKey);
            
            //senha privada
            PrivateKey privKey = pair.getPrivate(); 
            
            this.keys.setPrivKey(privKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        saveKeys();
    }

    /**
     * Método que serializa as chaves e salva.
     */
    private void saveKeys(){
        try{
            FileOutputStream arquivoGrav = new FileOutputStream("sgb.dat");
            ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);

            //Grava o objeto cliente no arquivo
            objGravar.writeObject(this.keys);
            objGravar.flush();
            objGravar.close();
            arquivoGrav.flush();
            arquivoGrav.close();

        } catch (Exception e) {
            System.err.println("Mensagem de erro: " +e.getMessage());
            System.err.println("Causa do erro: " + e.getCause());
        }
    }

   /**
    * Método que verifica se existe as chaves salvas no sistema
    * @return <code>true</code> se existir a chave gravada no sistema 
    */
    private boolean isKeys(){
        boolean recove = false;
        try{
            File arquivo = new File("sgb.dat");
            if (arquivo.exists()){
                recove = true;
            }
        } catch (Exception e) {
            System.err.println("Mensagem de erro: " +e.getMessage());
            System.err.println("Causa do erro: " + e.getCause());
        }

        return recove;
    }

    /**
     * Método que recupera as chaves salvas no sistema.
     */
    private void recoveKeys(){
        try{
            FileInputStream arquivoLeitura = new FileInputStream("sgb.dat");
            ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);

            this.keys = (Keys) objLeitura.readObject();
        } catch (Exception e) {
            System.err.println("Mensagem de erro: " +e.getMessage());
            System.err.println("Causa do erro: " + e.getCause());
        }
    }

    /**
     * Método de encriptação;
     * @param texto a ser encriptado pelo método
     * @return texto encriptado antes passado como parâmetro
     */
    public byte[] criptografa(String texto){
        Cipher cipher;

        byte[] cipherText = null;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, this.keys.getPubKey(), this.random);
            //mensagem codificada
            cipherText = cipher.doFinal(texto.getBytes()); 
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (NoSuchPaddingException e1) {
            e1.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return cipherText;
    }

    /**
     * Método de decriptação;
     * @param texto encritado a ser decritado.
     * @return texto decritado em um array de bytes.
     */
    public byte[] decriptografa(byte[] texto){
        Cipher cipher;

        byte[] plainText = null;
        try {
            cipher = Cipher.getInstance("RSA");
            //decodifica com a privada
            cipher.init(Cipher.DECRYPT_MODE, this.keys.getPrivKey()); 
            //mensagem decodificada
            plainText = cipher.doFinal(texto); 
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (NoSuchPaddingException e1) {
            e1.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return plainText;
    }
}
