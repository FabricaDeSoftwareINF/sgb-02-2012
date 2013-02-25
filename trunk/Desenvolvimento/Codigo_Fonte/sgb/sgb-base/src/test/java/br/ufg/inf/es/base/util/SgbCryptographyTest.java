/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.base.util;

import junit.framework.TestCase;

/**
 *
 * @author diogo
 */
public class SgbCryptographyTest extends TestCase {

    SgbCryptography cryptographer = new SgbCryptography();
    String text = "string_to_be_encrypted";

    public void testException() {
        cryptographer.setAlgorithmEncrypt("AgoritmoInexistente");
        String retorno = cryptographer.encrypt("£");
        assertEquals("", retorno);
    }

    public void testShouldReturnAnMD5String() {
        String stringEncrypted = cryptographer.encrypt(text);

        assertNotNull(stringEncrypted);
    }

    public void testShouldReturnTheSameMD5StringForTheSameString() {
        String stringEncrypted = cryptographer.encrypt(text);
        String stringEncrypted2 = cryptographer.encrypt(text);

        assertNotNull(stringEncrypted);
        assertNotNull(stringEncrypted2);

        assertEquals("The generated hash md5 should be the same for the same string", stringEncrypted, stringEncrypted2);
    }

    public void testShouldReturnDifferentMD5StringsForDifferentStrings() {
        String stringEncrypted = cryptographer.encrypt(text);
        String stringEncrypted2 = cryptographer.encrypt("othe_text");

        assertNotNull(stringEncrypted);
        assertNotNull(stringEncrypted2);

        assertNotSame("The generated hash md5 should be different for different strings", stringEncrypted, stringEncrypted2);
    }
}
