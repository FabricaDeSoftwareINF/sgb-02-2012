package br.ufg.inf.es.enuns;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Victor Carvalho
 */
public class EnumTipoBibliografiaTest {

    /**
     * Test of getValue method, of class EnumTipoBibliografia.
     */
    @Test
    public void testGetValue() {
        String expResult = "Básica";
        String result = EnumTipoBibliografia.BASICA.getValue();
        assertEquals(expResult, result);
    }
}