package br.ufg.inf.es.model.dtos;

import br.ufg.inf.es.model.Livro;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Alunoinf_2, Victor Carvalho
 */
public class LivroParaCotacaoTest {

    private LivroParaCotacao livroParaCotacao;
    private Integer quantidadeVagas = 50;
    private Integer parametroMec = 5;
    private Integer quantidadeLivrosDisponiveis = 5;
    private Integer quantidadeAComprar = 5;
    private Livro livro;

    @Before
    public void setUp() {
        livro = new Livro();
        livroParaCotacao = new LivroParaCotacao();
        livroParaCotacao.setQuantidadeAComprar(quantidadeAComprar);
        livroParaCotacao.setParametroMec(parametroMec);
        livroParaCotacao.setQuantidadeLivrosDisponiveis(quantidadeLivrosDisponiveis);
        livroParaCotacao.setLivro(livro);
        livroParaCotacao.setQuantidadeExigida(quantidadeVagas);
    }

    /**
     * Test of getLivro method, of class LivroParaCotacao.
     */
    @Test
    public void testGetLivro() {
        assertEquals(livro, livroParaCotacao.getLivro());
    }

    /**
     * Test of getParametroMec method, of class LivroParaCotacao.
     */
    @Test
    public void testGetParametroMec() {
        Integer result = livroParaCotacao.getParametroMec();
        assertEquals(parametroMec, result);
    }

    /**
     * Test of getQuantidadeLivrosDisponiveis method, of class LivroParaCotacao.
     */
    @Test
    public void testGetQuantidadeLivrosDisponiveis() {
        Integer result = livroParaCotacao.getQuantidadeLivrosDisponiveis();
        assertEquals(quantidadeLivrosDisponiveis, result);
    }

    /**
     * Test of getQuantidadeExigida method, of class LivroParaCotacao.
     */
    @Test
    public void testGetQuantidadeExigida() {
        Integer result = livroParaCotacao.getQuantidadeExigida();
        assertEquals(quantidadeVagas, result);
    }

    @Test
    public void testGetQuantidadeFaltando(){
        assertTrue(livroParaCotacao.getQuantidadeLivrosFaltando()>0);
    }
    /**
     * Test of getQuantidadeAComprar method, of class LivroParaCotacao.
     */
    @Test
    public void testGetQuantidadeAComprar() {
        Integer result = livroParaCotacao.getQuantidadeAComprar();
        assertEquals(quantidadeAComprar, result);
    }

    /**
     * Test of default constructor, of class LivroParaCotacao.
     */
    @Test
    public void testNewInstance() {
        livroParaCotacao = new LivroParaCotacao();
        assertNotNull(livroParaCotacao);
    }

    /**
     * Test of hashCode method, of class LivroParaCotacao.
     */
    @Test
    public void testHashCode() {
        int result = livroParaCotacao.hashCode ();
        assertTrue(result != 0);

    }

    @Test
    public void testHashCodeNull() {
        LivroParaCotacao instance = new LivroParaCotacao();
        int result = instance.hashCode();
        assertTrue(result !=0);

    }

    /**
     * Test of equals method, of class LivroParaCotacao.
     */
    @Test
    public void testEqualsTrue() {
        boolean expResult = true;
        boolean result = livroParaCotacao.equals(livroParaCotacao);
        assertEquals(expResult, result);
    }
    @Test
    public void testEqualsFalse() {
        boolean expResult = false;
        boolean result = livroParaCotacao.equals(new LivroParaCotacao());
        assertEquals(expResult, result);
    }

    @Test
    public void testEqualsTudoNull() {
        LivroParaCotacao instance = new LivroParaCotacao();
        boolean expResult = true;
        boolean result = instance.equals(instance);
        assertEquals(expResult, result);

    }
}