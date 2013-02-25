/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.base.util.cripto;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import junit.framework.TestCase;
import org.junit.*;
import static org.junit.Assert.*;
import sun.security.provider.DSAPrivateKey;
import sun.security.provider.DSAPublicKey;
import sun.security.provider.DSAPublicKeyImpl;
import sun.security.rsa.RSAPublicKeyImpl;

/**
 *
 * @author usuario
 */
public class KeysTest extends TestCase{
    
    

    /**
     * Test of getPubKey method, of class Keys.
     */
    @Test
    public void testGetPubKey() throws InvalidKeyException {
        Keys instance = new Keys();
        instance.setPubKey(new DSAPublicKey());
        PublicKey result = instance.getPubKey();
        assertNotNull (result);
        
    }

    /**
     * Test of setPubKey method, of class Keys.
     */
    @Test
    public void testSetPubKey() {
        
        PublicKey pubKey = new DSAPublicKey();
        Keys instance = new Keys();
        instance.setPubKey(pubKey);
        assertNotNull(instance.getPubKey());
    }

    /**
     * Test of getPrivKey method, of class Keys.
     */
    @Test
    public void testGetPrivKey() {
        Keys instance = new Keys();
        PrivateKey expResult = new DSAPrivateKey();
        instance.setPrivKey(expResult);
        PrivateKey result = instance.getPrivKey();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setPrivKey method, of class Keys.
     */
    @Test
    public void testSetPrivKey() {
        
        PrivateKey privKey = new DSAPrivateKey();
        Keys instance = new Keys();
        instance.setPrivKey(privKey);
        assertNotNull(instance.getPrivKey());
        
    }
}
