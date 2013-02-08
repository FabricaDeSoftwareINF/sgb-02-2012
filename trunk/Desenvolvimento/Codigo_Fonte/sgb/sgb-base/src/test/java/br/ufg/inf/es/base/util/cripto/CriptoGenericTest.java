package br.ufg.inf.es.base.util.cripto;

import junit.framework.TestCase;

/**
 *
 * @author igor
 */
public class CriptoGenericTest extends TestCase{
    
    public void testCriptografa(){
        String text = "texto criptografado";
        CriptoGeneric cript = new CriptoGeneric();
        byte[] cipher = cript.criptografa(text);
        String textCiph = new String(cipher);
        assertNotSame(text, textCiph);
    }
    
    public void testCriptografaTextosDistintos(){
        String text = "texto criptografado";
        CriptoGeneric cript = new CriptoGeneric();
        byte[] cipherText = cript.criptografa(text);
        String textCiph = new String(cipherText);
        
        String textDif = "texto diferente";
        byte[] cipherTextDif = cript.criptografa(textDif);
        String textCiphDif = new String(cipherTextDif);
        assertNotSame(textCiph, textCiphDif);
    }
    
    public void testDecriptografa(){
        String text = "texto criptografado";
        CriptoGeneric cript = new CriptoGeneric();
        byte[] cipher = cript.criptografa(text);
        byte[] decrip = cript.decriptografa(cipher);
        String textDecrip = new String(decrip);
        assertEquals(text, textDecrip);
    }
    
    public void testDecriptografaTextosDistintos(){
        String text = "texto criptografado";
        CriptoGeneric cript = new CriptoGeneric();
        byte[] cipherText = cript.criptografa(text);
        byte[] decripText = cript.decriptografa(cipherText);        
        
        String textDif = "texto diferente";
        byte[] cipherTextDif = cript.criptografa(textDif);
        String textCiphDif = new String(cipherTextDif);
        byte[] decripTextDif = cript.decriptografa(cipherTextDif);
        assertNotSame(decripText, decripTextDif);
    }
}
