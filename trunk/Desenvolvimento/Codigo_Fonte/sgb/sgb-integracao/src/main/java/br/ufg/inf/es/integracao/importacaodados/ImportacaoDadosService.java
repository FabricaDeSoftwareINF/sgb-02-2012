package br.ufg.inf.es.integracao.importacaodados;

import br.ufg.inf.es.model.Curso;
import br.ufg.inf.es.model.Disciplina;
import br.ufg.inf.es.model.Livro;

/**
 * Interface de comunicação com Serviços de Importação
 * @author Vinícius Gonçalves
 */
public interface ImportacaoDadosService {
    
    /**
     * 
     * @param curso 
     */
    public void importarCurso(Curso curso);
    
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

    public void importarDisciplinas(Disciplina disciplina) ;
    
    /**
     * 
     */
    public void importarDisciplinas();
    
    /**
     * 
     */
    public void importarLivros(Livro livro) ;
    
    /**
     * 
     */
    public void importarLivros();  
}
