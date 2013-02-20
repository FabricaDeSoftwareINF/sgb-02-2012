package br.ufg.inf.es.base.util.cripto;

import java.io.File;
import java.io.IOException;
import junit.framework.TestCase;
import org.junit.Ignore;

/**
 *
 * @author igor
 */
public class CriptoGenericTest extends TestCase {

    public void testCriptografa() {
        String text = "texto criptografado";
        CriptoGeneric cript = new CriptoGeneric();
        byte[] cipher = cript.criptografa(text);
        String textCiph = new String(cipher);
        assertNotSame(text, textCiph);
    }

    public void testCriptografaTextosDistintos() {
        String text = "texto criptografado";
        CriptoGeneric cript = new CriptoGeneric();
        byte[] cipherText = cript.criptografa(text);
        String textCiph = new String(cipherText);

        String textDif = "texto diferente";
        byte[] cipherTextDif = cript.criptografa(textDif);
        String textCiphDif = new String(cipherTextDif);
        assertNotSame(textCiph, textCiphDif);
    }

    public void testDecriptografa() {
        String text = "texto criptografado";
        CriptoGeneric cript = new CriptoGeneric();
        byte[] cipher = cript.criptografa(text);
        byte[] decrip = cript.decriptografa(cipher);
        String textDecrip = new String(decrip);
        assertEquals(text, textDecrip);
    }

    public void testDecriptografaTextosDistintos() {
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

    public void testCriptografaSemChave() throws IOException {
        File file = new File("sgb.dat");
        file.delete();

        String text = "texto criptografado";
        CriptoGeneric cript = new CriptoGeneric();
        cript.setArquivoChaves("arquivoInexistente.bat");
        byte[] cipher = cript.criptografa(text);
        String textCiph = new String(cipher);
        assertNotSame(text, textCiph);
        file = new File("sgb.bat");
        file.createNewFile();
    }
     public void testCriptografaComExceptionSemCifra() throws IOException {
        File file = new File("sgb.dat");
        file.delete();

        String text = "texto criptografado";
        CriptoGeneric cript = new CriptoGeneric("semCifra","sgb.dat");
        
        cript.setArquivoChaves("arquivoInexistente.bat");
        byte[] cipher = cript.criptografa(text);
        assertNull(cipher);
    }
     /**
      * Esse Teste captura um erro no sistema, no qual ele gera as chaves 
      * mas nao salva em arquivo e mesmo assim ele criptografa os dados. Desse
      * modo as chaves estao apenas em memoria e nao em disco, e isso eh um pro-
      * blema porque sem as chaves na memoria nao sera possivel descriptografar 
      * o dados.
      * @throws IOException 
      */
     public void testCriptografaComExceptionSemArquivoChaves() throws IOException, InterruptedException {
        File file = new File("sgb.dat");
        file.delete();

        String text = "texto criptografado";
        CriptoGeneric cript = new CriptoGeneric("RSA","sgb.dat");
        
        file.delete();
       
        byte[] cipher = cript.criptografa(text);
        assertNotNull(cipher);
        
    }
    public void testArquivoChaves(){
        CriptoGeneric cript = new CriptoGeneric("RSA","sgb.dat");
        assertNotNull(cript.getArquivoChaves());
        cript.setArquivoChaves("a.txt");
        assertEquals("a.txt",cript.getArquivoChaves());
    }
}
