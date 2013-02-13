/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.Disciplina;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 * Classe responsável por moldar uma coleção de Disciplina para uma tabela
 * selecionável do Primefaces
 *
 * @author Cassio Augusto Silva de Freitas
 */
public class DisciplinaDataModel extends ListDataModel<Disciplina> implements SelectableDataModel<Disciplina>, Serializable {

    public DisciplinaDataModel() {
    }

    public DisciplinaDataModel(List<Disciplina> data) {
        super(data);
    }

    @Override
    public Disciplina getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  

        List<Disciplina> disciplinas = (List<Disciplina>) getWrappedData();

        for (Disciplina disciplina : disciplinas) {

            if (String.valueOf(disciplina.getId()).equals(rowKey)) {

                return disciplina;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Disciplina disciplina) {
        return disciplina.getId();
    }
}
