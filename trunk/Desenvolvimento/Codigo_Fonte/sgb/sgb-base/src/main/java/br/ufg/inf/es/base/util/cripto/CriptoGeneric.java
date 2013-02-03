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
 *
 * @author igor
 */
public class CriptoGeneric implements Serializable{

    private static final long serialVersionUID = -2197693393634639351L;

    private SecureRandom random;
    private Keys keys = new Keys();

    public CriptoGeneric(){
        setUp();
    }

    private void setUp(){
        //verifica se existe a chave
        if (isKeys()) {
            recoveKeys();
        } else {
            createKey();
        }
    }


    private void createKey(){
        this.random = new SecureRandom();

        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048, this.random); //gero uma senha de 2048 bytes.
            KeyPair pair = generator.generateKeyPair(); //Ele gera as duas senhas, uma publica e a outra privada
            PublicKey pubKey = pair.getPublic(); //senha publica
            this.keys.setPubKey(pubKey);
            PrivateKey privKey = pair.getPrivate(); //senha privada
            this.keys.setPrivKey(privKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        saveKeys();
    }

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

    public byte[] criptografa(String texto){
        Cipher cipher;

        byte[] cipherText = null;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, this.keys.getPubKey(), this.random);
            cipherText = cipher.doFinal(texto.getBytes()); //mensagem codificada
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

    public byte[] decriptografa(byte[] texto){
        Cipher cipher;

        byte[] plainText = null;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, this.keys.getPrivKey()); //decodifica com a privada
            plainText = cipher.doFinal(texto); //mensagem decodificada
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
