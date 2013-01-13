package br.ufg.inf.es.integracao.importacaodados;

import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;

/**
 *
 * @author Vinícius
 */
public interface ImportacaoDadosService {
    
    public void importarBibliografia(Curso curso);
    
    public void importarCurso(String nome);
    
    public void importarCursos();
    
    public void importarLivros(Disciplina Disciplina) ;
    
    public void importarLivros();
    
    public void importarEditoras();
    
    public void importarAutores();
    
}
