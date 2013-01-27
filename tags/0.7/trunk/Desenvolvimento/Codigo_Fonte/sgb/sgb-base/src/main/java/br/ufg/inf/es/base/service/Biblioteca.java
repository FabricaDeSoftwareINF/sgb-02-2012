package br.ufg.inf.es.base.service;

import java.io.Serializable;
import java.util.Collection;

/**
 * Interface de comunicacao com os dados da biblioteca
 *
 * @author Victor Ribeiro de Carvalho
 */
public interface Biblioteca extends Serializable {

    /**
     * Obtem os livros da biblioteca.
     * 
     * @return Retorna todos os livros da biblioteca 
     */
    Collection obtenhaLivros();
    
    /**
     * Obtem os livros da biblioteca que possuem o nome informado.
     * 
     * @param nomeLivro
     * @return Retorna todos os livros da biblioteca que possuem o nome ou parte
     * dele igual ao parametro informado
     */
    Collection obtenhaLivros(String nomeLivro);
    
    /**
     * Obtem a quantidade existente dos livros da biblioteca que possuem o isbn 
     * informado.
     * 
     * @param isbn
     * @return Quantidade de livros existentes na biblioteca que possuem o isbn 
     * informado.
     */
    int obtenhaQuantidadeExistente(String isbn);
}
