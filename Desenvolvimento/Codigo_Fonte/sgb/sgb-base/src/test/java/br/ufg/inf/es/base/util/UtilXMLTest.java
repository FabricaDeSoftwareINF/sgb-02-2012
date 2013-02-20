package br.ufg.inf.es.base.util;

import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;

/**
 *
 * @author Victor Ribeiro de Carvalho
 */
public class UtilXMLTest extends TestCase {

    ObjetoXML objeto;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        objeto = new ObjetoXML();
    }

    /**
     * Test of convertaObjetoParaXML method, of class UtilXML.
     */
    public void testConvertaObjetoParaXML() {
        String expResult = obtenhaXmlEsperado();

        String result = UtilXML.convertaObjetoParaXML(objeto, obtenhaParametrosPadroes());
        assertEquals("O xml gerado a partir do objeto está diferente do esperado",
                expResult, result);
    }
    public void testConvertaObjetoParaXMLException() {
        
        boolean result = UtilXML.convertaObjetoParaXML(objeto, obtenhaParametrosPadroes(),"");
        assertFalse(result);
    }
     public void testConvertaObjetoParaXMLNull() {
        
        boolean result = UtilXML.convertaObjetoParaXML(objeto, null,"");
        assertFalse(result);
    }

    /**
     * Test of convertaObjetoParaXML method, of class UtilXML.
     */
    public void testConvertaObjetoParaXMLEEscrevaNoDisco() {
        String path = obtenhaPath("XMLEsperado.xml");
        boolean gerouArquivo = UtilXML.convertaObjetoParaXML(objeto,
                obtenhaParametrosPadroes(), path);
        assertEquals("O arquivo xml nao foi gerado a partir do objeto informado.",
                true, gerouArquivo);
    }

    /**
     * Test of convertaXMLEmObjeto method, of class UtilXML.
     */
    public void testConvertaXMLEmObjeto() {
        String path = obtenhaPath("XMLEsperado.xml");
        Object result = UtilXML.convertaXMLEmObjeto(path, obtenhaParametrosPadroes());
        assertEquals(objeto, result);
    }

    private Map<Class, String> obtenhaParametrosPadroes() {
        Map<Class, String> mapaDeAlias = new HashMap<Class, String>();
        mapaDeAlias.put(ObjetoXML.class, "nomeNoRaiz");

        return mapaDeAlias;
    }

    private String obtenhaXmlEsperado() {
        String path = obtenhaPath("XMLEsperado.xml");
        return UtilXML.obtenhaConteudoDoArquivo(path);
    }

    private String obtenhaPath(String nomeArquivo) {
        return "src/test/java/resources/" + nomeArquivo;
    }
}
