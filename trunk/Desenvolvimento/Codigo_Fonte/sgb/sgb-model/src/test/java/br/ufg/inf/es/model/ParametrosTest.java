package br.ufg.inf.es.model;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author alunoufg, Victor Carvalho
 */
public class ParametrosTest {

    private Parametros parametros;
    private BigDecimal valorFrete = new BigDecimal("30.00");
    private Integer parametroMEC = 5;
    private String urlSeviceBibliografico = "url";

    /**
     * setup
     */
    @Before
    public void setUp() {
        parametros = new Parametros();

        parametros.setValorFrete(valorFrete);
        parametros.setParametroMEC(parametroMEC);
        parametros.setUrlSeviceBibliografico(urlSeviceBibliografico);
    }

    /**
     * Test of getParametroMEC method, of class Parametros.
     */
    @Test
    public void testGetParametroMEC() {
        assertEquals(parametroMEC, parametros.getParametroMEC());
    }

    /**
     * Test of getValorFrete method, of class Parametros.
     */
    @Test
    public void testGetValorFrete() {
        assertEquals(valorFrete, parametros.getValorFrete());

    }

    /**
     * Test of getUrlSeviceBibliografico method, of class Parametros.
     */
    @Test
    public void testGetUrlSeviceBibliografico() {
        assertEquals(urlSeviceBibliografico, parametros.getUrlSeviceBibliografico());
    }
}