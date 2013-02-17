package br.ufg.inf.es.model.importacaobibliografia;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Victor Carvalho
 */
public class TipoEntidadeTest {

    /**
     * Test of values method, of class TipoEntidade.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        int expResult = 3;
        TipoEntidade[] result = TipoEntidade.values();
        assertEquals(expResult, result.length);
    }

    /**
     * Test of valueOf method, of class TipoEntidade.
     */
    @Test
    public void testValueOf() {
        TipoEntidade expResult = TipoEntidade.Cursos;
        TipoEntidade result = TipoEntidade.valueOf("Cursos");
        assertEquals(expResult, result);
    }
}
