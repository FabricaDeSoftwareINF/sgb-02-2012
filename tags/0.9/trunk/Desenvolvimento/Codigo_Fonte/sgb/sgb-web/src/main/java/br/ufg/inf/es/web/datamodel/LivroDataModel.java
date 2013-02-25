package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.Livro;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author vinicius
 */
public class LivroDataModel extends ListDataModel<Livro>
        implements SelectableDataModel<Livro>, Serializable {

    /**
     * Construtor padrao
     */
    public LivroDataModel() {
    }

    /**
     * Construtor que recebe a lista de objetos
     *
     * @param data
     */
    public LivroDataModel(List<Livro> data) {
        super(data);
    }

    /**
     * Método responsável por obter os dados da linha da Tabela.
     *
     * @param rowKey chave de identificação do objeto
     * @return Objeto da linha da tabela
     */
    @Override
    public Livro getRowData(String rowKey) {
        List<Livro> livros = (List<Livro>) getWrappedData();
        Livro livroSelecionado = null;

        for (Livro livro : livros) {
            if (String.valueOf(livro.getId()).equals(rowKey)) {
                livroSelecionado = livro;
            }
        }
        return livroSelecionado;
    }

    /**
     * obtem o id do objeto
     *
     * @param autor
     * @return
     */
    @Override
    public Object getRowKey(Livro car) {
        return car.getId();
    }
}