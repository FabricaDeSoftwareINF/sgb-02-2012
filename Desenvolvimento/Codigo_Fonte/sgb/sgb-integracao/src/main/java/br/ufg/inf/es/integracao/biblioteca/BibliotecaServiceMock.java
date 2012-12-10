package br.ufg.inf.es.integracao.biblioteca;

import br.ufg.inf.es.base.service.Biblioteca;
import br.ufg.inf.es.base.util.UtilXML;
import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;
import java.util.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Servico que disponibiliza as informacoes de livros da biblioteca a partir de
 * um arquivo xml com dados gerados aleatoriamente.
 *
 * @author Victor Ribeiro de Carvalho
 */
@Component
@Scope(value = "singleton")
public class BibliotecaServiceMock implements Biblioteca {

    public Collection<LivroBiblioteca> obtenhaLivros() {

        return (Collection<LivroBiblioteca>) UtilXML.convertaXMLEmObjeto(
                obtenhaPathLivrosXML(), obtenhaMapaAliasXML());
    }

    public Collection<LivroBiblioteca> obtenhaLivros(String nomeLivro) {
        Collection<LivroBiblioteca> livros = this.obtenhaLivros();
        Collection<LivroBiblioteca> matches = new ArrayList<LivroBiblioteca>();

        for (LivroBiblioteca livro : livros) {
            if (livro.getNome() == nomeLivro) {
                matches.add(livro);
            }
        }

        return matches;
    }

    public int obtenhaQuantidadeExistente(String isbn) {
        Collection<LivroBiblioteca> livros = this.obtenhaLivros();
        int quantidade = 0;
        for (LivroBiblioteca livro : livros) {
            if (livro.getIsbn().equals(isbn)) {
                quantidade += livro.getQuantidade();
            }
        }

        return quantidade;
    }

    private Map<Class, String> obtenhaMapaAliasXML() {
        Map<Class, String> mapaAliasXML = new HashMap<Class, String>();
        mapaAliasXML.put(List.class, "livros");
        mapaAliasXML.put(LivroBiblioteca.class, "livro");
        return mapaAliasXML;
    }

    private String obtenhaPathLivrosXML() {
        return this.getClass().getResource("/biblioteca/LivrosBiblioteca.xml").getPath();
    }
}
