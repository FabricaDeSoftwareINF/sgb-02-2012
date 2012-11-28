/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Henrique
 */
@Entity
@Table(name = "EDITORA")
public class Editora extends AbstractEntityModel{
    private String nome;
    private Collection<Livro> livros;
}
