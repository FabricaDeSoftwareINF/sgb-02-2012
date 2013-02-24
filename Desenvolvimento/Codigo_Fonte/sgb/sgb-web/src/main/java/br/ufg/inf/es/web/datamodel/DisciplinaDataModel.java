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
public class DisciplinaDataModel extends ListDataModel<Disciplina>
        implements SelectableDataModel<Disciplina>, Serializable {

    /**
     * Construtor padrao
     */
    public DisciplinaDataModel() {
    }

    /**
     * Construtor que recebe a lista de objetos
     *
     * @param data
     */
    public DisciplinaDataModel(List<Disciplina> data) {
        super(data);
    }

    /**
     * Obtem o objeto com o id correspondente
     *
     * @param rowKey id do objeto desejado
     * @return objeto com o id
     */
    @Override
    public Disciplina getRowData(String rowKey) {
        List<Disciplina> disciplinas = (List<Disciplina>) getWrappedData();

        for (Disciplina disciplina : disciplinas) {

            if (String.valueOf(disciplina.getId()).equals(rowKey)) {

                return disciplina;
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
    public Object getRowKey(Disciplina disciplina) {
        return disciplina.getId();
    }
}
