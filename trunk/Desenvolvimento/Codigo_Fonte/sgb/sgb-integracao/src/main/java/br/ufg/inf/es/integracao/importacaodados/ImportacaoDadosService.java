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
    public void importarBibliografia(Curso curso);
    
    /**
     * 
     * @param nome 
     */
    public void importarCurso(String nome);
    
    /**
     * 
     */
    public void importarCursos();
    
    /**
     * 
     * @param Disciplina 
     */
    public void importarLivros(Disciplina Disciplina) ;
    
    /**
     * 
     */
    public void importarLivros();
    
    /**
     * 
     */
    public void importarEditoras();
    
    /**
     * 
     */
    public void importarAutores();
    
}
