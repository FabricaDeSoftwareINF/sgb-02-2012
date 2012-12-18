package br.ufg.inf.es.base.util;

import java.io.File;
import junit.framework.TestCase;

/**
 *
 * @author Victor Ribeiro de Carvalho
 */
public class UtilFileTest extends TestCase {

    /**
     * Test of obtenhaConteudoDoArquivo method, of class UtilFile.
     */
    public void testObtenhaConteudoDoArquivo() {
        String path = obtenhaPath("ArquivoParaTeste.xml");
        String result = UtilFile.obtenhaConteudoDoArquivo(path);
        assertEquals("O conteúdo lido do arquivo está diferente do esperado",
                obtenhaConteudoEsperado(), result);
    }

    /**
     * Test of crieArquivo method, of class UtilFile.
     */
    public void testCrieArquivo() {
        String nomeArquivo = "ARQUIVO_GERADO.txt";
        String pathArquivo = obtenhaPath(nomeArquivo);
        File result = UtilFile.crieArquivo(pathArquivo);
        boolean arquivoGerado = result.exists();
        result.delete();
        assertTrue(arquivoGerado);
    }

    private String obtenhaPath(String nomeArquivo) {
        return "src/test/java/resources/" + nomeArquivo;
    }

    private String obtenhaConteudoEsperado() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<teste>\n    Arquivo utilizado em testes\n" + "</teste>";
    }
}
