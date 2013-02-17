package br.ufg.inf.es.model.importacaobibliografia;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Victor Carvalho
 */
public class MetodoImportacaoTest {

    /**
     * Test of values method, of class MetodoImportacao.
     */
    @Test
    public void testValues() {
        int expResult = 2;
        MetodoImportacao[] result = MetodoImportacao.values();
        assertEquals(expResult, result.length);
    }

    /**
     * Test of valueOf method, of class MetodoImportacao.
     */
    @Test
    public void testValueOf() {
        MetodoImportacao expResult = MetodoImportacao.POST;
        MetodoImportacao result = MetodoImportacao.valueOf("POST");
        assertEquals(expResult, result);
    }
}
