package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.base.util.UtilObjeto;
import br.ufg.inf.es.model.Autor;
import br.ufg.inf.es.model.Livro;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author vinicius
 */
public class LivroDTO extends Livro {

    public LivroDTO(Livro livro) {
        this.setAno(livro.getAno());
        this.setAutores(livro.getAutores());
        this.setBibliografias(livro.getBibliografias());
        this.setEdicao(livro.getEdicao());
        this.setEditora(livro.getEditora());
        this.setEstrangeiro(livro.isEstrangeiro());
        this.setId(livro.getId());
        this.setIsbn10(livro.getIsbn10());
        this.setIsbn13(livro.getIsbn13());
        this.setListaCompras(livro.getListaCompras());
        this.setTitulo(livro.getTitulo());
    }

    
    
    public static Collection<LivroDTO> asLivroDTO(Collection<Livro> livros) {
        Collection<LivroDTO> livrosDTO = new ArrayList<LivroDTO>();
        for (Livro livro : livros) {
            livrosDTO.add(new LivroDTO(livro));
        }
        return livrosDTO;
    }
    
}