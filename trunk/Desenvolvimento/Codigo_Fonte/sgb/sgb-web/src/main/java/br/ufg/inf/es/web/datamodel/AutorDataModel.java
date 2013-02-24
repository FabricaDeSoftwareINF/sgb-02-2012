package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.AutorDTO;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 * Classe responsável por moldar uma coleção de Autores para uma tabela
 * selecionáve do Primefaces
 *
 * @author Cassio Augusto Silva de Freitas
 */
public class AutorDataModel extends ListDataModel<AutorDTO>
        implements SelectableDataModel<AutorDTO>, Serializable {

    /**
     * Construtor padrao
     */
    public AutorDataModel() {
    }

    /**
     * Construtor que recebe a lista de objetos
     *
     * @param data
     */
    public AutorDataModel(List<AutorDTO> data) {
        super(data);
    }

    /**
     * Obtem o objeto com o id correspondente
     *
     * @param rowKey id do objeto desejado
     * @return objeto com o id
     */
    @Override
    public AutorDTO getRowData(String rowKey) {
        List<AutorDTO> autores = (List<AutorDTO>) getWrappedData();

        for (AutorDTO autor : autores) {

            if (String.valueOf(autor.getId()).equals(rowKey)) {
                return autor;
            }
        }

        return null;
    }

    /**
     * obtem o id do objeto
     *
     * @param autor
     * @return
     */
    @Override
    public Object getRowKey(AutorDTO autor) {
        return autor.getId();
    }
}