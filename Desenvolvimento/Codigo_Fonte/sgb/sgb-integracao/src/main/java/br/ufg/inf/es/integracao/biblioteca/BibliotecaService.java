package br.ufg.inf.es.integracao.biblioteca;

import br.ufg.inf.es.base.service.Biblioteca;
import br.ufg.inf.es.model.biblioteca.LivroBiblioteca;
import br.ufg.inf.es.persistencia.biblioteca.LivrosBibliotecaDAO;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.NotFoundException;

/**
 *
 * @author Igor
 */
//@Component
//@Scope(BeanDefinition.SCOPE_SINGLETON)
public class BibliotecaService implements Biblioteca {

    /**
     * Obter uma cole&ccedil;&atilde;o de livros do banco.
     * Carrega todos itens do banco.
     * @return Cole&ccedil;&atilde;o de <code>LivroBiblioteca</code>
     */
    public Collection<LivroBiblioteca> obtenhaLivros() {
        LivrosBibliotecaDAO livrosBibliotecaDAO = new LivrosBibliotecaDAO();
        try {
            return (Collection<LivroBiblioteca>) livrosBibliotecaDAO.getLivroBibliotecaAll();
        } catch (NotFoundException ex) {
            Logger.getLogger(BibliotecaService.class.getName()).log(Level.SEVERE, null, ex);
            return (Collection<LivroBiblioteca>) new ArrayList<LivroBiblioteca>();
        } catch (SQLException ex) {
            Logger.getLogger(BibliotecaService.class.getName()).log(Level.SEVERE, null, ex);
            return (Collection<LivroBiblioteca>) new ArrayList<LivroBiblioteca>();
        }
    }

    /**
     * Obter uma cole&ccedil;&atilde;o de livros do banco.
     * Carrega itens do banco filtrando pelo nome do livro.
     * @return Cole&ccedil;&atilde;o de <code>LivroBiblioteca</code>
     * @param nomeLivro
     * @return 
     */
    public Collection<LivroBiblioteca> obtenhaLivros(String nomeLivro) {
        LivrosBibliotecaDAO livrosBibliotecaDAO = new LivrosBibliotecaDAO();
        try {
            return (Collection<LivroBiblioteca>) livrosBibliotecaDAO.getLivrosBibliotecaTitulo(nomeLivro);
        } catch (NotFoundException ex) {
            Logger.getLogger(BibliotecaService.class.getName()).log(Level.SEVERE, null, ex);
            return (Collection<LivroBiblioteca>) new ArrayList<LivroBiblioteca>();
        } catch (SQLException ex) {
            Logger.getLogger(BibliotecaService.class.getName()).log(Level.SEVERE, null, ex);
            return (Collection<LivroBiblioteca>) new ArrayList<LivroBiblioteca>();
        }
    }

    /**
     * Obter uma cole&ccedil;&atilde;o de livros do banco.
     * Carrega itens do banco filtrando pelo <code>nome do livro</code> e <code>isbn</code>
     * @return Cole&ccedil;&atilde;o de <code>LivroBiblioteca</code>
     * @param nomeLivro Nome do livro 
     * @param isbn
     * @return Cole&ccedil;&atilde;o de <code>LivroBiblioteca</code>
     * @throws NotFoundException 
     */
    public Collection<LivroBiblioteca> obtenhaLivros(String nomeLivro, String isbn) throws NotFoundException {
        LivrosBibliotecaDAO livrosBibliotecaDAO = new LivrosBibliotecaDAO();
        try {
            return (Collection<LivroBiblioteca>) livrosBibliotecaDAO.getLivrosBibliotecaTitulo(nomeLivro, isbn);

        } catch (SQLException ex) {
            Logger.getLogger(BibliotecaService.class.getName()).log(Level.SEVERE, null, ex);
            return (Collection<LivroBiblioteca>) new ArrayList<LivroBiblioteca>();
        }
    }

    /**
     * Obter a quantidade de livros existentes no banco utilizando o <code>isbn</code>
     * no filtro.
     * @param isbn
     * @return Quantidade de livros contidos no banco.
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
}
