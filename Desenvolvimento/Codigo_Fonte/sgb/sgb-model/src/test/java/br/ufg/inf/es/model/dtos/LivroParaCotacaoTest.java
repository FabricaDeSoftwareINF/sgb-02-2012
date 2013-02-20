package br.ufg.inf.es.model.dtos;

import br.ufg.inf.es.model.Livro;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
        livroParaCotacao = new LivroParaCotacao(quantidadeVagas, parametroMec,
                quantidadeLivrosDisponiveis, quantidadeAComprar, livro);
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
}