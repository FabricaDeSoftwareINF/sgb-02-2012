package br.ufg.inf.es.integracao.biblioteca;

import br.ufg.inf.es.base.util.UtilXML;
import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;
import java.util.*;
import junit.framework.TestCase;

/**
 * Teste do mock dos dados da biblioteca atraves de arquivos xml
 * 
 * @author Victor Ribeiro de Carvalho
 */
public class BibliotecaServiceMockTest extends TestCase {

    private BibliotecaServiceMock bibliotecaServiceMock;
    private String isbnBase = "1234567";

    public BibliotecaServiceMockTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        bibliotecaServiceMock = new BibliotecaServiceMock();
    }

    /**
     * Test of obtenhaLivros method, of class BibliotecaServiceMock.
     */
    public void testObtenhaLivros() {
        Collection<LivroBiblioteca> result = bibliotecaServiceMock.obtenhaLivros();
        int qtdeEsperada = 20;
        assertEquals("A quantidade de livros lidos no xml esta diferente do esperado",
                result.size(), qtdeEsperada);
    }

    /**
     * Test of obtenhaLivros method, of class BibliotecaServiceMock.
     */
    public void testObtenhaLivrosPorNome() {
        Collection<LivroBiblioteca> result = bibliotecaServiceMock.obtenhaLivros("Livro 1");
        assertEquals("Deveria ter retornado somente um livro", 1, result.size());
    }

    /**
     * Test of obtenhaQuantidadeExistente method, of class
     * BibliotecaServiceMock.
     */
    public void testObtenhaQuantidadeExistente() {

        int result = bibliotecaServiceMock.obtenhaQuantidadeExistente(isbnBase + 9);
        assertEquals("A quantidade deveria ser de nove livro", 9, result);

        result = bibliotecaServiceMock.obtenhaQuantidadeExistente(isbnBase + 0);
        assertEquals("A quantidade deveria ser de zero livros", 0, result);

        result = bibliotecaServiceMock.obtenhaQuantidadeExistente(isbnBase + 10);
        assertEquals("A quantidade deveria der de dez livros", 10, result);
    }

    /**
     * Teste criado para gerar o arquivo xml com os livros da biblioteca, para
     * que o desenvolvimento possa continuar sem termos acesso ao banco.
     */
    public void testCrieArquivoComLivros() {
        List<LivroBiblioteca> livros = obtenhaListaDeLivros();
        Map<Class, String> mapaAliasXML = new HashMap<Class, String>();
        mapaAliasXML.put(List.class, "livros");
        mapaAliasXML.put(LivroBiblioteca.class, "livro");

        String path = obtenhaPath("LivrosBiblioteca.xml");

        boolean gerouXML = UtilXML.convertaObjetoParaXML(livros, mapaAliasXML, path);
        assertEquals("O arquivo xml com os livros da biblioteca deveriam ter sido gerados",
                true, gerouXML);
    }

    private List<LivroBiblioteca> obtenhaListaDeLivros() {
        List<LivroBiblioteca> livros = new ArrayList<LivroBiblioteca>();
        for (int i = 0; i < 20; i++) {
            livros.add(obtenhaLivro(new Long(i), "Livro " + i, isbnBase + i, i));
        }
        return livros;
    }

    private String obtenhaPath(String nomeArquivo) {
        return "src/test/java/resources/" + nomeArquivo;
    }

    private LivroBiblioteca obtenhaLivro(Long id, String nome, String isbn, Integer quantidade) {
        LivroBiblioteca livro = new LivroBiblioteca();
        livro.setId(id);
        livro.setNome(nome);
        livro.setIsbn(isbn);
        livro.setQuantidade(quantidade);
        livro.setAno("2012");
        livro.setEdicao(1);
        livro.setEditora("Editora UFG");
        livro.setAutor("ES_Man");
        return livro;
    }
}