package br.ufg.inf.es.integracao.exportacaodados;

import br.ufg.inf.es.model.Livro;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vinicius
 */
public class MarcParserTest {
    
    private static MarcParser marcParser;
    
    public MarcParserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        marcParser = new MarcParser();
    }

    /**
     * Test of livroToMarc method, of class MarcParser.
     */
    @Test
    public void testLivroToMarc() {
        String titulo = "Livro Teste";
        Livro livro = new Livro();
        livro.setTitulo(titulo);
        String livroMarc = marcParser.livroToMarc(livro);
        assertTrue(livroMarc.contains(titulo));
    }
    
}
