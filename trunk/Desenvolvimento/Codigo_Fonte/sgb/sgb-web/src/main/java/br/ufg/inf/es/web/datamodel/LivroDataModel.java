package br.ufg.inf.es.web.datamodel;

import br.ufg.inf.es.model.Livro;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import br.ufg.inf.es.integracao.LivroService;
import java.util.Collection;

/**
 *
 * @author vinicius
 */
public class LivroDataModel extends LazyDataModel<Livro> {

    private Collection<Livro> datasource;
    private LivroService service;

    public LivroDataModel(LivroService service) {
        this.service = service;
        this.datasource = service.list();
    }

    @Override
    public Livro getRowData(String rowKey) {
        for (Livro livro : datasource) {
            if (livro.getTitulo().equals(rowKey)) {
                return livro;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Livro livro) {
        return livro.getTitulo();
    }

    @Override
    public List<Livro> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        List<Livro> data = new ArrayList<Livro>();

        //filter  
        for (Livro livro : datasource) {
            boolean match = true;

            for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                try {
                    String filterProperty = it.next();
                    String filterValue = filters.get(filterProperty);
                    String fieldValue = String.valueOf(livro.getClass().getField(filterProperty).get(livro));

                    if (filterValue == null || fieldValue.startsWith(filterValue)) {
                        match = true;
                    } else {
                        match = false;
                        break;
                    }
                } catch (Exception e) {
                    match = false;
                }
            }
            
            if (match) {
                data.add(livro);
            }
        }

        //sort  
        if (sortField != null) {
            Collections.sort(data, new CarLazySorter(sortField, sortOrder));
        }

        //rowCount  
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate  
        if (dataSize > pageSize) {
            try {
                data = data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                data = data.subList(first, first + (dataSize % pageSize));
            }
        }
        
        for (Livro livro : data) {
            livro.setBibliografias(service.getDAO().getBibliografia(livro.getId()));
        }
        
        return data;
    }
}