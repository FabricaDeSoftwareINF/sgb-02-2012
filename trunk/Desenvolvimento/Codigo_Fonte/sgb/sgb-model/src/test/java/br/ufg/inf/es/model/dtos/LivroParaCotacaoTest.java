package br.ufg.inf.es.model.dtos;

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
    private Integer quantidadeLivrosFaltando = 5;
    private String nomeLivro = "A";
    private String isbn = "isbn";

    @Before
    public void setUp() {
        livroParaCotacao = new LivroParaCotacao(quantidadeVagas, parametroMec,
                quantidadeLivrosDisponiveis, quantidadeLivrosFaltando, nomeLivro, isbn);
    }

    /**
     * Test of getIsbn method, of class LivroParaCotacao.
     */
    @Test
    public void testGetIsbn() {
        assertEquals(isbn, livroParaCotacao.getIsbn());
    }

    /**
     * Test of getNomeLivro method, of class LivroParaCotacao.
     */
    @Test
    public void testGetNomeLivro() {
        String result = livroParaCotacao.getNomeLivro();
        assertEquals(nomeLivro, result);
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
     * Test of getQuantidadeLivrosFaltando method, of class LivroParaCotacao.
     */
    @Test
    public void testGetQuantidadeLivrosFaltando() {
        Integer result = livroParaCotacao.getQuantidadeLivrosFaltando();
        assertEquals(quantidadeLivrosFaltando, result);

    }

    /**
     * Test of getQuantidadeVagas method, of class LivroParaCotacao.
     */
    @Test
    public void testGetQuantidadeVagas() {
        Integer result = livroParaCotacao.getQuantidadeVagas();
        assertEquals(quantidadeVagas, result);
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