package br.ufg.inf.es.integracao.biblioteca;

import br.ufg.inf.es.base.service.Biblioteca;
import br.ufg.inf.es.base.util.UtilXML;
import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;
import java.util.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Servico que disponibiliza as informacoes de livros da biblioteca a partir de
 * um arquivo xml com dados gerados aleatoriamente.
 *
 * @author Victor Ribeiro de Carvalho
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class BibliotecaServiceMock implements Biblioteca {

    /**
     * Obtem os livros da biblioteca.
     *
     * @return Retorna todos os livros da biblioteca
     */
    public Collection<LivroBiblioteca> obtenhaLivros() {

        return (Collection<LivroBiblioteca>) UtilXML.convertaXMLEmObjeto(
                obtenhaPathLivrosXML(), obtenhaMapaAliasXML());
    }

    /**
     * Obtem os livros da biblioteca que possuem o nome informado.
     *
     * @param nomeLivro
     * @return Retorna todos os livros da biblioteca que possuem o nome ou parte
     * dele igual ao parametro informado
     */
    public Collection<LivroBiblioteca> obtenhaLivros(String nomeLivro) {
        Collection<LivroBiblioteca> livros = this.obtenhaLivros();
        Collection<LivroBiblioteca> matches = new ArrayList<LivroBiblioteca>();

        for (LivroBiblioteca livro : livros) {
            if (livro.getNome().equals(nomeLivro)) {
                matches.add(livro);
            }
        }

        return matches;
    }

    /**
     * Obtem a quantidade existente dos livros da biblioteca que possuem o isbn
     * informado.
     *
     * @param isbn
     * @return Quantidade de livros existentes na biblioteca que possuem o isbn
     * informado.
     */
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
