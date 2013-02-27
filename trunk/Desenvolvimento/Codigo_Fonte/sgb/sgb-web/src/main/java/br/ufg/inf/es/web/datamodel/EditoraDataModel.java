/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.AutorDTO;
import br.ufg.inf.es.model.Editora;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Cassio Augusto Silva de Freitas
 */
public class EditoraDataModel extends ListDataModel<Editora> implements SelectableDataModel<Editora> , Serializable {

    public EditoraDataModel(List<Editora> list) {
        super(list);
    }

    
    @Override
    public Editora getRowData(String t) {

        List<Editora> autores = (List<Editora>) getWrappedData();

        for (Editora autor : autores) {

            if (String.valueOf(autor.getId()).equals(t)) {
                return autor;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Editora editora) {
        return editora.getId();
    }
    
}
