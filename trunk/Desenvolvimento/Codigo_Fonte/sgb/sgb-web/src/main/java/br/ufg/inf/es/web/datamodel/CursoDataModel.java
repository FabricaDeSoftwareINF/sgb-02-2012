package br.ufg.inf.es.web.datamodel;

import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;
import br.ufg.inf.es.model.Curso;

/**
 * Classe responsável por moldar uma coleção de Cursos para uma datatable com seleção
 * @author Allan Vieira Ribeiro
 */
public class CursoDataModel extends ListDataModel<Curso> implements SelectableDataModel<Curso>, Serializable{
    
    /**
     * Construtor CursoDataModel
     */
    public CursoDataModel() {  
    }  
  
    /**
     * Construtor CursoDataModel
     */
    public CursoDataModel(List<Curso> data) {  
        
        super(data);  
    }  
      
    /**
     * Obtém os dados da Coluna com a determinda chave
     * 
     * @author Allan Vieira Ribeiro
     * 
     * @param rowKey Chave da coluna     * 
     * @return Curso armazenado em determinada posição da tabela
     */
    @Override  
    public Curso getRowData(String rowKey) {  
          
        List<Curso> cursos = (List<Curso>) getWrappedData();  
          
        for(Curso curso : cursos) {  
            
            if(String.valueOf(curso.getId()).equals(rowKey)) {
                
                return curso;  
            }
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Curso curso) {  
        
        return curso.getId();  
    }  
    
}
