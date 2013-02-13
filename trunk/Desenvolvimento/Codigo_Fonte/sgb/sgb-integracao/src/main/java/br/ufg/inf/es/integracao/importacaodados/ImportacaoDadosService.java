package br.ufg.inf.es.integracao.importacaodados;

import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;

/**
 * Interface de comunicação com Serviços de Importação
 * @author Vinícius Gonçalves
 */
public interface ImportacaoDadosService {
    
    /**
     * 
     * @param curso 
     */
    void importarBibliografia(Curso curso);
    
    /**
     * 
     * @param nome 
     */
    void importarCurso(String nome);
    
    /**
     * 
     */
    void importarCursos();
    
    /**
     * 
     * @param Disciplina 
     */
    void importarLivros(Disciplina disciplina) ;
    
    /**
     * 
     */
    void importarLivros();
    
    /**
     * 
     */
    void importarEditoras();
    
    /**
     * 
     */
    void importarAutores();
    
}
